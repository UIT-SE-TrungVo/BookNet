package com.booknet.base.payload;

import com.booknet.constants.ErrCode;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BaseResponse {
    @NotNull
    long errCode;

    Object data;

    public BaseResponse(Object data) {
        this.errCode = ErrCode.NONE;
        this.data = data;
    }

    public BaseResponse(long errCode, Object data) {
        this.errCode = errCode;
        this.data = data;
    }

    public boolean isError() {
        return errCode != ErrCode.NONE;
    }

    public long getErrCode() {
        return errCode;
    }

    public Object getData() {
        return data;
    }
}
