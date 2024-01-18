package cc.yiueil.lang.http;

/**
 * HttpHeader http头类型定义
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/1/18 16:32
 */
public interface HttpHeader {
    String CONTENT_TYPE = "Content-Type";
    String AUTHORIZATION = "Authorization";
    String COOKIE = "Cookie";
    // 其他请求头部常量定义

    String CONTENT_TYPE_DEFAULT_VALUE = "application/x-www-form-urlencoded";
    String CONTENT_TYPE_JSON_VALUE = "application/json";
    String CONTENT_TYPE_XML_VALUE = "application/xml";
    String CONTENT_TYPE_TEXT_VALUE = "text/plain";
    String CONTENT_TYPE_MULTIPART_VALUE = "multipart/form-data";
    // Content-Type 可选值常量定义
}