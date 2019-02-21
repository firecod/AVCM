/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Version: 2 
 * @author Adrian Israel Calcanas Ramírez and Diego Humberto Castro Castro
 * Fecha: 21 - 02 - 2019
 * Descripción: Modelado de clase Venta
 */

/** Revisar base de datos: tabla detalle de venta y tabla venta */
public class Venta {
    private String fechaVenta;
    private int idProducto;
    private int idVendedor;
    private int idAlmacen;
    private int idCliente;
    private float importe;
    private String facturaExpendida;
    private int numeroTicket;

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getFacturaExpendida() {
        return facturaExpendida;
    }

    public void setFacturaExpendida(String facturaExpendida) {
        this.facturaExpendida = facturaExpendida;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }
    
    
}
