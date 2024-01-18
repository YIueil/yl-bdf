package cc.yiueil.controller;

import cc.yiueil.api.ImageResource;
import cc.yiueil.api.impl.SmmsImageBedImpl;
import cc.yiueil.general.RestUrl;
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
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + "/resource")
public class ResourceController implements LoggedController{
    @PostMapping(value="/image/upload")
    public String imageUpload(@RequestParam("file") MultipartFile multipartFile){
        ImageResource imageResource = new SmmsImageBedImpl();
        try {
            imageResource.upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename() + ".jpg" , multipartFile.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success();
    }
}
