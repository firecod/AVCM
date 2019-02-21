/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Version: 1
 * @author: Diego Humberto Castro Castro
 * Fecha: 21 - 02 -2019
 * Descripción: Modelado de la clase Producto
 */
public class Producto {
    private int id;
    private String nombre;
    private String marca;
    private Float precioUso;
    private String categoria;
    private String estatus;
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

    public Float getPrecioUso() {
        return precioUso;
    }

    public void setPrecioUso(Float precioUso) {
        this.precioUso = precioUso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
    
    
}
