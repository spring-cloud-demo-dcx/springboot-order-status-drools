package cn.skuu.pojo.vo;

import cn.skuu.common.enums.CommonResponseEnum;
import lombok.Data;

/**
 * return
 *
 * @author dcx
 * @since 2022-08-13 03:29
 **/
@Data
public class ReturnVO<T> {
    private int code;
    private String message;
    private T data;

    public ReturnVO() {
    }

    public static <T> ReturnVO<T> build(int code, String message, T data) {
        return new ReturnVO<>(code, message, data);
    }

    public static <T> ReturnVO<T> ok(T data) {
        return new ReturnVO<>(data);
    }

    public static <T> ReturnVO<T> ok() {
        return new ReturnVO<>(null);
    }

    public static <T> ReturnVO<T> error(String message) {
        return new ReturnVO<>(CommonResponseEnum.ERROR.getCode(), message, null);
    }

    public static <T> ReturnVO<T> error(String message, T data) {
        return new ReturnVO<>(CommonResponseEnum.ERROR.getCode(), message, data);
    }

    public static <T> ReturnVO<T> error(T data) {
        return new ReturnVO<>(CommonResponseEnum.ERROR.getCode(), CommonResponseEnum.ERROR.getMessage(), data);
    }

    public ReturnVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ReturnVO(T data) {
        this.code = CommonResponseEnum.SUCCESS.getCode();
        this.message = CommonResponseEnum.SUCCESS.getMessage();
        this.data = data;
    }

}
