package com.booknet.apis.authentication.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class AppRole {
    @Id
    private String _id;

    private EAppRole name;

    public AppRole() {

    }

    public AppRole(EAppRole name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public EAppRole getName() {
        return name;
    }

    public void setName(EAppRole name) {
        this.name = name;
    }
}
