/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firecod.avcm.rest;

import controller.ControllerLogin;
import flexjson.JSONSerializer;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Cliente;
import model.Vendedor;
/**
 *
 * @author Vanessa
 */
@Path("/")
public class RESTLogin extends Application{
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("usuario") @DefaultValue("") String usuario,
                          @FormParam("contrasenia") @DefaultValue("") String password)
    {
        ControllerLogin cl = new ControllerLogin();
        String out = null;
        Object o = null;
        Vendedor v = null;
        Cliente c = null;
        JSONSerializer jss = new JSONSerializer();
        try{
            o = cl.login(usuario, password);
            if(o != null){
                if(o instanceof Vendedor){
                    v = (Vendedor) o;
                    out = jss.serialize(v);
                }else{
                    c = (Cliente) o;
                    out = jss.serialize(c);
                }
            } else {
                out = "{\"error\":\"Datos de crendencial incorrectos.\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
