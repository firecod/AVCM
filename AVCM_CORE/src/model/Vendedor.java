/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Version: 2
 * @author: Mónica del Rosario Díaz Arredondo y Castro Diego
 * Fecha: 16 - 02 -2019
 * Descripción: Modelado de la clase Vendedors
 */
public class Vendedor {
    private int id;
    private String numeroVendedor;
    private String fotografíaVendedor;
    private int reputacion;
    private int estatus;
    private Persona persona;

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

   
    
    
}
