/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firecod.avcm.rest;

import controller.ControllerProducto;
import controller.ControllerVendedor;
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
import model.Persona;
import model.Producto;
import model.Usuario;
import model.Vendedor;

/**
 *
 * @author Israel
 */
@Path("/")
public class RESTVendedor extends Application{
    
    @POST
    @Path("insertVendedor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("primerApellido")@DefaultValue("") String primerApellido,
                           @FormParam("segundoApellido")@DefaultValue("") String segundoApellido,
                           @FormParam("rfc")@DefaultValue("") String rfc,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("telefono")@DefaultValue("") String telefono,
                           @FormParam("usuario")@DefaultValue("") String nombreUsuario,
                           @FormParam("contrasenia")@DefaultValue("") String contrasenia,
                           @FormParam("reputacion")@DefaultValue("") String reputacion,
                           @FormParam("almacen")@DefaultValue("") String almacen)
    {        
        ControllerVendedor cv = new ControllerVendedor();
        String out = null;
        Vendedor v = new Vendedor();
        Persona p = new Persona();
        Usuario u = new Usuario();
        JSONSerializer jss = new JSONSerializer();  
        try {
            p.setNombre(nombre);
            p.setApellidoPaterno(primerApellido);
            p.setApellidoMaterno(segundoApellido);
            p.setRfc(rfc);
            p.setDomicilio(domicilio);
            p.setTelefono(telefono);
            u.setNombreUsuario(nombreUsuario);
            u.setContrasenia(contrasenia);
            v.setPersona(p);
            v.setUsuario(u);
            v.setFotografíaVendedor("");
            v.setReputacion(Integer.parseInt(reputacion));
            v.setAlmacen(Integer.parseInt(almacen));
            cv.insert(v);
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
    @Path("updateVendedor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("primerApellido")@DefaultValue("") String primerApellido,
                           @FormParam("segundoApellido")@DefaultValue("") String segundoApellido,
                           @FormParam("rfc")@DefaultValue("") String rfc,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("telefono")@DefaultValue("") String telefono,
                           @FormParam("usuario")@DefaultValue("") String nombreUsuario,
                           @FormParam("contrasenia")@DefaultValue("") String contrasenia,
                           @FormParam("reputacion")@DefaultValue("") String reputacion,
                           @FormParam("almacen")@DefaultValue("") String almacen,
                           @FormParam("idVendedor") @DefaultValue("0") int idVendedor)
    {        
        ControllerVendedor cv = new ControllerVendedor();
        String out = null;
        Vendedor v = new Vendedor();
        Persona p = new Persona();
        Usuario u = new Usuario();
        JSONSerializer jss = new JSONSerializer();  
        try {
            p.setNombre(nombre);
            p.setApellidoPaterno(primerApellido);
            p.setApellidoMaterno(segundoApellido);
            p.setRfc(rfc);
            p.setDomicilio(domicilio);
            p.setTelefono(telefono);
            u.setNombreUsuario(nombreUsuario);
            u.setContrasenia(contrasenia);
            v.setId(idVendedor);
            v.setPersona(p);
            v.setUsuario(u);
            v.setFotografíaVendedor("");
            v.setReputacion(Integer.parseInt(reputacion));
            v.setAlmacen(Integer.parseInt(almacen));
            cv.update(v);
            if(v.getId() > 0)
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
    @Path("getAllVendedor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerVendedor cv= new ControllerVendedor();
        
        List<Vendedor> vendedores =null;
        JSONSerializer jss= new JSONSerializer();
        String out= null;
        
        try {
            vendedores = cv.getAll("", 1);
            out = jss.serialize(vendedores);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("deleteVendedor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("idVendedor")@DefaultValue("0")String idVendedor){
        ControllerVendedor cv= new ControllerVendedor();                
        String out= null;
        Vendedor v = new Vendedor();
        try {
            cv.delete(Integer.parseInt(idVendedor));
            out = "{\"response:\":\"Eliminado Correctamente.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
