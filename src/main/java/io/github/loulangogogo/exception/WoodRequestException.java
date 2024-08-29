package io.github.loulangogogo.exception;

import io.github.loulangogogo.water.exception.BaseException;

/*********************************************************
 ** 请求异常
 **
 ** @author loulan
 ** @since 8
 *********************************************************/
public class WoodRequestException extends BaseException {
    public WoodRequestException() {
        super();
    }

    public WoodRequestException(String message) {
        super(message);
    }

    public WoodRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public WoodRequestException(Throwable cause) {
        super(cause);
    }

    protected WoodRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
