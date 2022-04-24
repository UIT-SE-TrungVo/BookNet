package com.booknet.api.sample_module.model;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "samples")
public class SampleModel {
    @Id
    String _id;

    @NotNull
    Integer number;

    @NotEmpty
    String text;

    public SampleModel() {
    }

    public SampleModel(String _id, Integer number, String text) {
        this._id = _id;
        this.number = number;
        this.text = text;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
