package cc.yiueil.exception;

/**
 * VerifyFailException
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/20 1:14
 */
public class VerifyFailException extends RuntimeException {
    private static final long serialVersionUID = -8384903735347523285L;

    public VerifyFailException() {
        super();
    }

    public VerifyFailException(String message) {
        super(message);
    }

    public VerifyFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyFailException(Throwable cause) {
        super(cause);
    }

    protected VerifyFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
