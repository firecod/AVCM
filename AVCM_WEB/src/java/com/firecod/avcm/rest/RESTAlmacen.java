/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firecod.avcm.rest;

import controller.ControllerAlmacen;
import flexjson.JSONSerializer;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Almacen;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTAlmacen extends Application{
    @GET
    @Path("getAllAlmacen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerAlmacen ca= new ControllerAlmacen();
        
        List<Almacen> almacenes =null;
        JSONSerializer jss= new JSONSerializer();
        String out= null;
        
        try {
            almacenes = ca.getAll("", 1);
            out=jss.serialize(almacenes);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
}
