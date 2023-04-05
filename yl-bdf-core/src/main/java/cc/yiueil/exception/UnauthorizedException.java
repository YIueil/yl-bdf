package cc.yiueil.exception;

/**
 * Author:YIueil
 * Date:2023/4/5 23:23
 * Description: 未授权访问异常
 */
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
