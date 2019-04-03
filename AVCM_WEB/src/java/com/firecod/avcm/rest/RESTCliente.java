package com.firecod.avcm.rest;

import controller.ControllerCliente;
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
import model.Cliente;
import model.Persona;
import model.Usuario;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTCliente extends Application {
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("estatus") @DefaultValue("1") int estatus) {
        ControllerCliente cc = new ControllerCliente();
        List<Cliente> clientes = null;
        String out = null;
        JSONSerializer jss = new JSONSerializer();        
        try{
            clientes = cc.getAll("", estatus);
            out = jss.serialize(clientes);            
        }catch (Exception e){
            e.printStackTrace();
            out = "{\"error:\":\""+e.toString()+"\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
        
    }
    
    @POST
    @Path("insertCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(//Persona
                           @FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("apellidoPaterno")@DefaultValue("") String apellidoPaterno,
                           @FormParam("apellidoMaterno")@DefaultValue("") String apellidoMaterno,
                           @FormParam("genero")@DefaultValue("") String genero,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,                           
                           @FormParam("rfc")@DefaultValue("") String rfc,
                           //Cliente                           
                           @FormParam("correoElectronico")@DefaultValue("") String correoElectronico,
                           @FormParam("idPersona")@DefaultValue("0") int idPersona,
                           @FormParam("idUsuario")@DefaultValue("0") int idUsuario,
                           //Usuario                           
                           @FormParam("nombreUsuario")@DefaultValue("") String nombreUsuario,
                           @FormParam("contrasenia")@DefaultValue("") String contrasenia
                           ){
        ControllerCliente cc = new ControllerCliente();
        JSONSerializer jss= new JSONSerializer();
        String out = null;
        Cliente c; 
        Persona p;
        Usuario u;
        
        try{
            //Persona
           p = new Persona();
           p.setNombre(nombre);
           p.setApellidoMaterno(apellidoMaterno);
           p.setApellidoPaterno(apellidoPaterno);
           p.setDomicilio(domicilio);
           p.setId(idPersona);
           p.setRfc(rfc);
           //Cliente
           c = new Cliente();
           c.setCorreoElectronico(correoElectronico);
           c.setEstatus(1);
           //Usuario
           u = new Usuario();
           u.setNombreUsuario(nombreUsuario);
           u.setContrasenia(contrasenia);
           //Objetos
           c.setPersona(p);
           c.setUsuario(u);
           cc.insert(c);
           if(c.getId() > 0){
               out = jss.serialize(c);
           }else{
               out = "{\"error\":\"Movimiento no realizado.\"}";
           }
        }catch(Exception e){
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}