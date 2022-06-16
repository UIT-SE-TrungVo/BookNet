package com.booknet.api.profile.model;

public class ProfileSimplifiedModel {
    String id;
    String urlImage;
    String name;
    String alias;

    public ProfileSimplifiedModel(String id, String urlImage, String name, String alias) {
        this.id = id;
        this.urlImage = urlImage;
        this.name = name;
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public static ProfileSimplifiedModel getSimplified(ProfileModel profileModel) {
        return new ProfileSimplifiedModel(
                profileModel.get_id()
                , profileModel.getUrlImage()
                , profileModel.getName()
                , profileModel.getUsername()
        );
    }
}
