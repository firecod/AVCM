package com.firecod.avcm_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vendedor {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("numeroVendedor")
    @Expose
    private String numeroVendedor;

    @SerializedName("fotografiaVendedor")
    @Expose
    private String fotografíaVendedor;

    @SerializedName("reputacion")
    @Expose
    private int reputacion;

    @SerializedName("estatus")
    @Expose
    private int estatus;

    @SerializedName("persona")
    @Expose
    private Persona persona;

    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    @SerializedName("almacen")
    @Expose
    private Almacen almacen;

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroVendedor() {
        return numeroVendedor;
    }

    public void setNumeroVendedor(String numeroVendedor) {
        this.numeroVendedor = numeroVendedor;
    }

    public String getFotografíaVendedor() {
        return fotografíaVendedor;
    }

    public void setFotografíaVendedor(String fotografíaVendedor) {
        this.fotografíaVendedor = fotografíaVendedor;
    }

    public int getReputacion() {
        return reputacion;
    }

    public void setReputacion(int reputacion) {
        this.reputacion = reputacion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
