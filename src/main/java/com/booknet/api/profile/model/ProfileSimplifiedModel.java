package com.booknet.api.profile.model;

public class ProfileSimplifiedModel {
    String _id;
    String urlImage;
    String name;
    String alias;

    public ProfileSimplifiedModel(String _id, String urlImage, String name, String alias) {
        this._id = _id;
        this.urlImage = urlImage;
        this.name = name;
        this.alias = alias;
    }

    public String get_id() {
        return _id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getName() {
        return name;
    }

    public static ProfileSimplifiedModel getSimplified(ProfileModel profileModel) {
        return new ProfileSimplifiedModel(
                profileModel.get_id()
                , profileModel.getUrlImage()
                , profileModel.getName()
                , profileModel.getAlias()
        );
    }
}
