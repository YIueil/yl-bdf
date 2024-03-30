package cc.yiueil.constant;

/**
 * OrupSupportConfig orup中支持的配置项
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/31 5:45
 */
public interface OrupSupportConfig extends SupportConfig{
    /**
     * jwt超时时间 单位秒
     */
    String JWT_EXPIRE_SECONDS = "jwt.expire.seconds";
    /**
     * 应用通用测试token
     */
    String APPLICATION_PUBLIC_TOKEN = "application.public.token";
}
