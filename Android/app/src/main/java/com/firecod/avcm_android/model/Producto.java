package com.firecod.avcm_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producto {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("marca")
    @Expose
    private String marca;
    @SerializedName("precio")
    @Expose
    private Float precio;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("estatus")
    @Expose
    private int estatus;
    @SerializedName("almacen")
    @Expose
    private Almacen almacen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
}
