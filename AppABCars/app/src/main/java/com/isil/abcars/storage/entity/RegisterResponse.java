package com.isil.abcars.storage.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MarcoTejeda on 20/11/16.
 */
public class RegisterResponse {

    @SerializedName("___class")
    private String type;

    @SerializedName("user-token")
    private String token;

    private String name;

    private String last_name;

    private String email;

    private String objectId;

    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
