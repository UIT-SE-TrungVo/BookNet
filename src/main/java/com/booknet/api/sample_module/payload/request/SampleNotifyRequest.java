package com.booknet.api.sample_module.payload.request;

import javax.validation.constraints.NotNull;

public class SampleNotifyRequest {
    @NotNull
    Integer number;

    public SampleNotifyRequest() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
