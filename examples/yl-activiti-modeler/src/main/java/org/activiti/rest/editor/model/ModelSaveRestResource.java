/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.rest.editor.model;

import cc.yiueil.util.IoUtils;
import cc.yiueil.util.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Tijs Rademakers
 */
@RestController
public class ModelSaveRestResource implements ModelDataJsonConstants {

    protected static final Logger LOGGER = LoggerFactory.getLogger(org.activiti.rest.editor.model.ModelSaveRestResource.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/model/{modelId}/save", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) {
        try {

            Model model = repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

            modelJson.put(MODEL_NAME, values.getFirst("name"));
            modelJson.put(MODEL_DESCRIPTION, values.getFirst("description"));
            model.setMetaInfo(modelJson.toString());
            model.setName(values.getFirst("name"));

            repositoryService.saveModel(model);

            repositoryService.addModelEditorSource(model.getId(), values.getFirst("json_xml").getBytes("utf-8"));

            InputStream svgStream = new ByteArrayInputStream(values.getFirst("svg_xml").getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();

        } catch (Exception e) {
            LOGGER.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
    }

    @RequestMapping(value = "/model/{modelId}/deploy", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deployModel(@PathVariable String modelId) {
        Model modelData = repositoryService.getModel(modelId);
        ObjectNode modelNode;
        try {
            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());
            modelNode = (ObjectNode) new ObjectMapper().readTree(modelEditorSource);
            byte[] bpmnBytes;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment()
                    .name(modelData.getName())
                    .addString(processName, new String(bpmnBytes, StandardCharsets.UTF_8))
                    .deploy();
            modelData.setDeploymentId(deployment.getId());
            repositoryService.saveModel(modelData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出模型
     * 获取到模型二进制数据
     * @param modelId 模型id
     */
    @RequestMapping(value = "/model/{modelId}/export", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void exportModel(@PathVariable String modelId,
                            @RequestParam(value = "contentType", required = false) String contentType,
                            HttpServletResponse response) {
        Model model = repositoryService.getModel(modelId);
        byte[] modelEditorSource = repositoryService.getModelEditorSource(model.getId());
        String fileName = model.getName() + ".bpmn20.xml";
        try (ServletOutputStream outputStream = response.getOutputStream()){
            // 通过ObjectMapper读取二进制文件
            JsonNode jsonNode = new ObjectMapper().readTree(modelEditorSource);
            BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();
            BpmnXMLConverter bpmnXmlConverter = new BpmnXMLConverter();
            BpmnModel bpmnModel = bpmnJsonConverter.convertToBpmnModel(jsonNode);
            // 从bpmnModel中获取主要的process部分
            byte[] bytes = bpmnXmlConverter.convertToXML(bpmnModel, "UTF-8");
            IoUtils.write(bytes, outputStream);
            //转码
            try {
                if (contentType == null || contentType.length() == 0
                        || "download".equalsIgnoreCase(contentType)
                        || "application/x-msdownload".equalsIgnoreCase(contentType)) {
                    response.setContentType("application/x-msdownload");
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + new String(fileName.getBytes(StandardCharsets.UTF_8),
                            "iso8859-1") + "\"");
                } else {
                    response.setContentType(contentType);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入模型
     * @param modelId 模型id
     * @param file 模型文件
     * @param charset 编码 默认UTF-8
     * @return 流程xml字符串
     */
    @RequestMapping(value = "/model/{modelId}/import", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String importModel(@PathVariable String modelId,
                            @RequestParam(value = "file") MultipartFile file,
                            @RequestParam(value = "charset", defaultValue = "UTF-8") String charset
    ) {
        BpmnXMLConverter converter = new BpmnXMLConverter();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader;
        ObjectNode modelNode = null;
        try {
            reader = factory.createXMLStreamReader(file.getInputStream());
            //将xml文件转换成BpmnModel
            BpmnModel bpmnModel = converter.convertToBpmnModel(reader);
            Model model = repositoryService.getModel(modelId);
            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            modelJson.put(MODEL_NAME, model.getName());
            modelJson.put(MODEL_DESCRIPTION, "");
            model.setMetaInfo(modelJson.toString());
            model.setName(model.getName());
            repositoryService.saveModel(model);
            modelNode = new BpmnJsonConverter().convertToJson(bpmnModel);
            repositoryService.addModelEditorSource(modelId, modelNode.toString().getBytes(charset));
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
        return ObjectUtils.defaultIfNull(modelNode, "").toString();
    }
}
