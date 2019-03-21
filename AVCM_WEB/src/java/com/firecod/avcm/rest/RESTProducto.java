/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firecod.avcm.rest;

import controller.ControllerProducto;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Almacen;
import model.Producto;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTProducto extends Application{
    @POST
    @Path("insertProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@QueryParam("nombre")@DefaultValue("") String nombre,
                           @QueryParam("marca")@DefaultValue("") String marca,
                           @QueryParam("precio")@DefaultValue("0") float precio,
                           @QueryParam("categoria")@DefaultValue("") String categoria,
                           @QueryParam("idAlmacen")@DefaultValue("1") int almacen)
    {        
        ControllerProducto cp= new ControllerProducto();        
        String out = null;
        Producto p = new Producto();
        Almacen a = new Almacen();    
        try {
            
            p.setEstatus(1);
            p.setMarca(marca);
            p.setNombre(nombre);                        
            a.setId(almacen);
            p.setAlmacen(a);
            p.setCategoria(categoria);
            p.setPrecio(precio);
            cp.insert(p);
            if(p.getId() > 0)
                out = "{\"result\":" + p.getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    

}
