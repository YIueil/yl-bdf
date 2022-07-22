package cn.yiueil.exception;

/**
 * Author:YIueil
 * Date:2022/7/22 23:06
 * Description: 分页异常类
 */
public class PageException extends RuntimeException{
    public PageException() {
    }

    public PageException(String message) {
        super(message);
    }

    public PageException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageException(Throwable cause) {
        super(cause);
    }

    public PageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
