/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firecod.avcm.rest;

import controller.ControllerAlmacen;
import controller.ControllerProducto;
import flexjson.JSONSerializer;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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
public class RESTAlmacen extends Application{
    @GET
    @Path("getAllAlmacen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("estatus")@DefaultValue("1")int estatus){
        ControllerAlmacen ca= new ControllerAlmacen();
        
        List<Almacen> almacenes =null;
        JSONSerializer jss= new JSONSerializer();
        String out= null;
        
        try {
            almacenes = ca.getAll("", estatus);
            out = jss.serialize(almacenes);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
     
    @POST
    @Path("insertAlmacen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("domicilio")@DefaultValue("") String domicilio
                           )
    {        
        ControllerAlmacen ca = new ControllerAlmacen();        
        String out = null;
        Almacen a;        
        JSONSerializer jss = new JSONSerializer();         
        try {
            a = new Almacen();
            a.setEstatus(1);            
            a.setNombre(nombre);                        
            a.setDomicilio(domicilio);
            ca.insert(a);
            if(a.getId() > 0)
               out = jss.serialize(a);                            
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    
    
     @POST
    @Path("updateAlmacen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("idAlmacen")@DefaultValue("0") int idAlmacen
                           )
    {        
        ControllerAlmacen ca = new ControllerAlmacen();        
        String out = null;
        Almacen a;        
        JSONSerializer jss = new JSONSerializer();         
        try {
            a = new Almacen();
            a.setEstatus(1);            
            a.setNombre(nombre);                        
            a.setDomicilio(domicilio);
            ca.update(a);
            if(a.getId() > 0)
               out = jss.serialize(a);                            
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    
    
    
    
}

