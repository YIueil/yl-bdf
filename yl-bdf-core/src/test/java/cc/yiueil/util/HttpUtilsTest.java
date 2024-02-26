package cc.yiueil.util;

import cc.yiueil.enums.MediaTypeEnum;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

class HttpUtilsTest {
    private static OkHttpClient okHttpClient = null;

    @BeforeAll
    static void createInstance() {
        okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();
    }

    @Test
    void doGet() {
        String url = "www.baidu.com";
        HttpUrl httpUrl = Optional.ofNullable(HttpUrl.parse(url)).orElseThrow(() -> new RuntimeException("url解析异常"));
        httpUrl = httpUrl
                .newBuilder()
                .addQueryParameter("pageSize", "1")
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(httpUrl)
                .method("get", null)
                .build();
    }

    @Test
    void doPost() {
        Request.Builder builder = new Request.Builder();
        // 表单类型的MultipartBody构造 MultipartBody继承至RequestBody
        MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", "testfile1.png", RequestBody.create("", MediaType.parse(MediaTypeEnum.IMAGE_PNG.getValue())))
                .addFormDataPart("name", "testfile1")
                .build();
        Request request = builder.url("www.baidu.com")
                .method("post", multipartBody)
                .build();
    }

    @Test
    void testDoPost() throws IOException {
        String url = "www.baidu.com";
        // 构造url参数
        HttpUrl httpUrl = Optional.ofNullable(HttpUrl.parse(url))
                .orElseThrow(() -> new RuntimeException(""))
                .newBuilder()
                .addQueryParameter("username", "zhangsan")
                .addQueryParameter("password", "123456")
                .build();
        // 构造header
        Headers headers = new Headers.Builder()
                .add("token", "yl-token")
                .build();
        // 构造body
        RequestBody requestBody = RequestBody.create("{\"id\":1,\"name\":\"张三\"}", MediaType.parse(MediaTypeEnum.APPLICATION_JSON.getValue()));
        // 创建request对象
        Request request = new Request.Builder()
                .url(httpUrl)
                .headers(headers)
                .method("post", requestBody)
                .build();
        // 同步
        Response response = okHttpClient.newCall(request).execute();
        // 异步
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(ObjectUtils.defaultIfNull(request.body(), ""));
            }
        });
        if (response.isSuccessful()) {
            System.out.println(ObjectUtils.defaultIfNull(request.body(), ""));
        } else {
            System.out.println(response.code());
            System.out.println(response.message());
        }
    }
}