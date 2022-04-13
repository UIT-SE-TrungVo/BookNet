package com.booknet.api.sample_module.payload.request;

import com.booknet.api.sample_module.model.SampleModel;

public class SampleUpdateRequest extends SampleModel {
    public SampleUpdateRequest(String _id, Integer number, String text) {
        super(_id, number, text);
    }
}
