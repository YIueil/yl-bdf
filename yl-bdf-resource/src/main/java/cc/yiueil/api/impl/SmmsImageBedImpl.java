package cc.yiueil.api.impl;

import cc.yiueil.api.ImageResource;
import cc.yiueil.entity.result.DownloadResult;
import cc.yiueil.entity.result.UploadResult;
import cc.yiueil.enums.SmmsEnum;
import cc.yiueil.exception.FileUploadException;
import cc.yiueil.lang.http.HttpHeader;
import cc.yiueil.lang.http.HttpMethod;
import cc.yiueil.url.SmmsUrl;
import cc.yiueil.util.IoUtils;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * SmmsImageBedImpl 聚合SM.MS实现
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/1/18 14:33
 */
public class SmmsImageBedImpl implements ImageResource {

    @Override
    public UploadResult upload(InputStream inputStream, String fileName, String fileType, boolean watermark) throws FileUploadException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body;
        try {
            byte[] bytes = IoUtils.toByteArray(inputStream);
            body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart(SmmsEnum.smfile.name(), fileName, RequestBody.create(bytes, MediaType.parse("application/octet-stream")))
                    .addFormDataPart(SmmsEnum.format.name(), "json")
                    .build();
            Request request = new Request.Builder()
                    .url(SmmsUrl.SMMS_URL)
                    .method(HttpMethod.POST, body)
                    .addHeader(HttpHeader.CONTENT_TYPE, HttpHeader.CONTENT_TYPE_MULTIPART_VALUE)
                    .addHeader(HttpHeader.USERAGENT, HttpHeader.USERAGENT_DEFAULT)
                    // todo 优化为从配置中心获取
                    .addHeader(HttpHeader.AUTHORIZATION, "TEyA2TCcm4x7l0P4cxHFPp8PajEl7w76")
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody == null) {
                        throw new FileUploadException();
                    }
                    System.out.println(responseBody.string());
                    // 处理响应结果
                } else {
                    int errorCode = response.code();
                    String errorMsg = response.message();
                    System.out.println(errorCode);
                    System.out.println(errorMsg);
                    // 处理错误信息
                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException();
        }
    }

    @Override
    public DownloadResult download() {
        return null;
    }

    @Override
    public void watermark(InputStream inputStream) {

    }
}
