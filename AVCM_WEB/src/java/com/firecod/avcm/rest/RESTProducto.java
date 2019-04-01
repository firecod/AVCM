
package com.firecod.avcm.rest;

import controller.ControllerProducto;
import flexjson.JSON;
import flexjson.JSONDeserializer;
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
public class RESTProducto extends Application{
    @POST
    @Path("insertProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("marca")@DefaultValue("") String marca,
                           @FormParam("precio")@DefaultValue("0") float precio,
                           @FormParam("categoria")@DefaultValue("") String categoria,
                           @FormParam("idAlmacen")@DefaultValue("1") int almacen)
    {        
        ControllerProducto cp= new ControllerProducto();        
        String out = null;
        Producto p = new Producto();
        JSONSerializer jss = new JSONSerializer();
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
               out = jss.serialize(p);                            
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }   
    
    @POST
    @Path("updateProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("marca")@DefaultValue("") String marca,
                           @FormParam("precio")@DefaultValue("0") float precio,
                           @FormParam("categoria")@DefaultValue("") String categoria,
                           @FormParam("idAlmacen")@DefaultValue("1") int almacen,
                           @FormParam("idProducto")@DefaultValue("0") int idProducto)
    {        
        ControllerProducto cp= new ControllerProducto();        
        String out = null;
        Producto p = new Producto();
        JSONSerializer jss = new JSONSerializer();
        Almacen a = new Almacen();    
        try {

            p.setEstatus(1);
            p.setMarca(marca);
            p.setNombre(nombre);                        
            a.setId(almacen);
            p.setAlmacen(a);
            p.setCategoria(categoria);
            p.setPrecio(precio);
            p.setId(idProducto);
            cp.update(p);
            if(p.getId() > 0)
               out = jss.serialize(p);                            
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    
    @GET
    @Path("getAllProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("estatus")@DefaultValue("1")int estatus){
        ControllerProducto ca= new ControllerProducto();
        
        List<Producto> productos =null;
        JSONSerializer jss= new JSONSerializer();
        String out= null;
        
        try {
            productos = ca.getAll("", estatus);
            out = jss.serialize(productos);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }

    @GET
    @Path("deleteProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("idProducto")@DefaultValue("0")int idProducto){
        ControllerProducto ca= new ControllerProducto();                
        String out= null;
        Producto p = new Producto();
        try {
            ca.delete(idProducto);
            out = "{\"response:\":\"Eliminado Correctamente.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
}
