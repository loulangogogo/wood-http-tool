package io.github.loulangogogo.wood.http.exception;

import io.github.loulangogogo.water.exception.BaseException;

/*********************************************************
 ** 请求异常
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
public class WoodRequestException extends BaseException {
    /**
     * 构造一个无参的请求异常
     */
    public WoodRequestException() {
        super();
    }

    /**
     * 构造一个带消息的请求异常
     *
     * @param message 异常消息
     */
    public WoodRequestException(String message) {
        super(message);
    }

    /**
     * 构造一个带消息和原因的请求异常
     *
     * @param message 异常消息
     * @param cause   异常原因
     */
    public WoodRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造一个带原因的请求异常
     *
     * @param cause 异常原因
     */
    public WoodRequestException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个完整的请求异常
     *
     * @param message            异常消息
     * @param cause              异常原因
     * @param enableSuppression  是否启用抑制
     * @param writableStackTrace 是否可写堆栈跟踪
     */
    protected WoodRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
