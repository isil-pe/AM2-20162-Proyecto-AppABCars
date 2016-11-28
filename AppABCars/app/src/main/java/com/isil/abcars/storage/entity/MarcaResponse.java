package com.isil.abcars.storage.entity;

import com.google.gson.annotations.SerializedName;
import com.isil.abcars.entity.MarcaEntity;

import java.util.List;

/**
 * Created by FranciscoParedes on 8/06/16.
 */
public class MarcaResponse {

    @SerializedName("___class")
    private String type;

    @SerializedName("user-token")
    private String token;

    private String message;
    private String marca;
    private String objectId;

    private int offset;
    private List<MarcaEntity> data;

    private Object nextPage;
    private int totalObjects;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }

    public Object getNextPage() {
        return nextPage;
    }

    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MarcaEntity> getData() {
        return data;
    }

    public void setData(List<MarcaEntity> data) {
        this.data = data;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
