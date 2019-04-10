package com.firecod.avcm_android.components.TableView.model;

public class RowHeaderCliente {
    private String pData;

    public RowHeaderCliente(String pData){
        this.pData = pData;
    }

    public String getData(){
        return pData;
    }

    @Override
    public String toString() {
        return pData;

    }
}
