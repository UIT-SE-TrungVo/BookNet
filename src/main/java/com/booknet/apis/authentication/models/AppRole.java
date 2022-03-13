package com.booknet.apis.authentication.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class AppRole {

    @Id
    private ObjectId _id;

    private EAppRole role;

    public AppRole() {
    }

    public AppRole(EAppRole role) {
        this._id = ObjectId.get();
        this.role = role;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public EAppRole getRole() {
        return role;
    }

    public void setRole(EAppRole role) {
        this.role = role;
    }
}
