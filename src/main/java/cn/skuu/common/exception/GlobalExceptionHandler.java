package cn.skuu.common.exception;

import cn.skuu.common.enums.CommonResponseEnum;
import cn.skuu.pojo.vo.ReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 捕获全局异常
 *
 * @author dcx
 * @date 2022/9/25 13:10
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ReturnVO bizExceptionHandler(BizException e) {
        log.error("业务异常:", e);
        return ReturnVO.build(e.getCode(), e.getMessage(), null);
    }

    /**
     * 数据错误
     */
    @ExceptionHandler(value = DataAccessException.class)
    @ResponseBody
    public ReturnVO dataAccessExceptionHandler(DataAccessException e) {
        log.error("数据异常:", e);
        return ReturnVO.build(CommonResponseEnum.DATA_ERROR.getCode(), CommonResponseEnum.DATA_ERROR.getMessage(), null);
    }

    /**
     * 数据错误
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public ReturnVO duplicateKeyExceptionHandler(DuplicateKeyException e) {
        log.error("数据异常:", e);
        return ReturnVO.build(CommonResponseEnum.DATA_ERROR.getCode(), CommonResponseEnum.DATA_ERROR.getMessage(), null);
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ReturnVO methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数验证异常:", e);
        return ReturnVO.build(CommonResponseEnum.PARAMETER_ERROR.getCode(), e.getBindingResult().getFieldError().getDefaultMessage(), null);
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnVO exceptionHandler(Exception e) {
        log.error("未知异常:", e);
        return ReturnVO.build(CommonResponseEnum.INTERNAL_SERVER_ERROR.getCode(), CommonResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

}
