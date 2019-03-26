/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DB.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Almacen;

/**
 *
 * @author diegg
 */
public class ControllerAlmacen {
     public List<Almacen> getAll(String filtro, int estatus) throws Exception{
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM almacen WHERE estatus = ?";        
        //Aquí guardaremos objetos de tipo Producto. Una lista es un "contenedor"
        //dinamico de objetos. En este caso, queremos un contenedor de 
        //"productos". En otras palabras, queremos un contenedor que dentro
        //"contenga" objetos de tipo Producto:
        List<Almacen> almacenes = new ArrayList<>();        
        //Una variable temporal para crear nuevas instancias de Producto:
        Almacen a = null;        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //consulta de productos:
        PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, estatus);
        //Aquí guardaremos el resultado de la consulta:
        ResultSet rs = pstmt.executeQuery();
        
        //Recorremos el ResultSet, comenzando por el primer registro:
        while (rs.next()){
            //Creamos una nueva instancia de Producto:
            a = new Almacen();
            
            //Llenamos sus propiedades:
            a.setDomicilio(rs.getString("domicilio"));
            a.setEstatus(rs.getInt("estatus"));
            a.setId(rs.getInt("idAlmacen"));
            a.setNombre(rs.getString("nombre"));
           
            
            //Agregamos el producto a la lista:
            almacenes.add(a);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinámica con los productos generados al 
        //realizar la consulta en la Base de Datos.
        return almacenes;
    }
}
