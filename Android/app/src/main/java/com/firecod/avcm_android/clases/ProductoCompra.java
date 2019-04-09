package com.firecod.avcm_android.clases;

public class ProductoCompra {

    private String mId;
    private String mNombre;
    private String mMarca;
    private String mFoto;

    public ProductoCompra(){

    }

    public ProductoCompra(String id, String nombre, String marca, String foto) {
        this.mId = id;
        this.mNombre = nombre;
        this.mMarca = marca;
        this.mFoto = foto;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public void setNombre(String nombre) {
        this.mNombre = nombre;
    }

    public void setMarca(String marca) {
        this.mMarca = marca;
    }

    public void setFoto(String foto) {
        this.mFoto = foto;
    }

    public String getId() {
        return mId;
    }

    public String getNombre() {
        return mNombre;
    }

    public String getMarca() {
        return mMarca;
    }

    public String getFoto() {
        return mFoto;
    }

    @Override
    public String toString() {
        return "ProductoCompra{" +
                "ID='" + mId + '\'' +
                ", Nombre='" + mNombre + '\'' +
                ", Marca='" + mMarca + '\'' +
                ", Foto='" + mFoto + '\'' +
                '}';
    }
}
