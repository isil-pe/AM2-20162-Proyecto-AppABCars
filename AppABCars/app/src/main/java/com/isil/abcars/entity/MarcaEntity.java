package com.isil.abcars.entity;

import java.io.Serializable;

/**
 * Created by FranciscoParedes on 23/11/16.
 */

public class MarcaEntity implements Serializable {

    private String marca;
    private String objectId;
    private String token;

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
}
