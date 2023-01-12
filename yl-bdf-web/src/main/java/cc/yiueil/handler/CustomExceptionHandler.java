package cc.yiueil.handler;

import cc.yiueil.vo.ResultVo;
import cc.yiueil.exception.BusinessException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo 自定义异常处理
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResultVo globalExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultVo.error("服务异常", e);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResultVo runtimeException(RuntimeException e) {
        e.printStackTrace();
        return ResultVo.error("未知运行时异常", e);
    }

    @ExceptionHandler({BusinessException.class})
    public ResultVo businessExceptionHandler(BusinessException e) {
        e.printStackTrace();
        return ResultVo.error("未知业务功能异常", e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultVo methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> errorParams = new HashMap<>();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError objectError : allErrors) {
            errorParams.put(objectError.getObjectName(), objectError.getDefaultMessage());
        }
        return ResultVo.validate_fail(errorParams, "入参数校验失败");
    }
}