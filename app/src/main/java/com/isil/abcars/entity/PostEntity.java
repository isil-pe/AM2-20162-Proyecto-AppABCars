package com.isil.abcars.entity;

/**
 * Created by MarcoTejeda on 16/11/16.
 */
public class PostEntity {

    private int id;
    private String desc;
    private double precio;

    public PostEntity(double precio, String desc) {
        this.precio = precio;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
