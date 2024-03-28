package cc.yiueil.controller;

import cc.yiueil.api.ImageResource;
import cc.yiueil.api.impl.SmmsImageBedImpl;
import cc.yiueil.entity.result.UploadResult;
import cc.yiueil.general.RestUrl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * FileUploadController 文件上传控制器
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/1/18 16:46
 */
@Api(value = "文件上传控制器")
@Slf4j
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + "/resource")
public class ResourceController implements LoggedController{
    @PostMapping(value="/image/upload")
    public String imageUpload(@RequestParam("file") MultipartFile multipartFile){
        ImageResource imageResource = new SmmsImageBedImpl();
        try {
            UploadResult uploadResult = imageResource.upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType());
            return success(uploadResult);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
