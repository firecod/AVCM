package com.firecod.avcm_android.clases;

public class ProductoVo {
    private String id;
    private String nombre;
    private String marca;

    public ProductoVo(){

    }

    public ProductoVo(String id, String nombre, String marca) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

}
