package com.firecod.avcm_android.clases;

public class ClienteVo {
    private String nombreCliente;
    private int numeroUnico;

    public ClienteVo(String nombreCliente, int numeroUnico) {
        this.nombreCliente = nombreCliente;
        this.numeroUnico = numeroUnico;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setNumeroUnico(int numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getNumeroUnico() {
        return numeroUnico;
    }
}
