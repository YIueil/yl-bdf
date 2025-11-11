package cc.yiueil.instance;

/**
 * VerifyCodeUseType 验证码用途
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 22:49
 */
public interface VerifyCodeUseType {
    /**
     * 获取使用类型
     * @return 使用类型
     */
    String getUseType();

    /**
     * 设置使用类型
     * @param useType 使用类型
     */
    void setUseType(String useType);
}
