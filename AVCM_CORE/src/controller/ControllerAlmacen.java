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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Almacen;

/**
 *
 * @author diegg
 */
public class ControllerAlmacen {
    
    public int insert(Almacen a) throws Exception{
        String sql = "INSERT INTO almacen (nombre, domicilio, estatus) "
                    + "VALUES (?, ?, ?)";
        
        //Aquí guardaremos el ID que se generará
        int idGenerado = -1;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la
        //inserción en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserción del registro:
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        //En este objeto guardamos el resultado de la consulta, la cual
        //nos devolverá los ID's que se generaron. En este caso, solo se
        //generará un ID:
        ResultSet rs = null;
        
        pstmt.setString(1, a.getNombre());
        pstmt.setString(2, a.getDomicilio());               
        pstmt.setInt(3, 1);                       
        //Ejecutamos la consutla:
        pstmt.executeUpdate();
        
        //Le pedimos al PreparedStatement los valores de las claves generadas,
        //que en este caso, solo es un valor:
        rs = pstmt.getGeneratedKeys();               
        
        if(rs.next()){
            idGenerado = rs.getInt(1);
            a.setId(idGenerado);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        return idGenerado;
    }
    
    public void update(Almacen a) throws Exception{
        String sql = "UPDATE almacen SET nombre = ?, domicilio = ? "
                    + "WHERE  idAlmacen = ?";
               
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la
        //inserción en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserción del registro:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //En este objeto guardamos el resultado de la consulta, la cual
        //nos devolverá los ID's que se generaron. En este caso, solo se
        //generará un ID:
      
        pstmt.setString(1, a.getNombre());
        pstmt.setString(2, a.getDomicilio());   
        pstmt.setInt(3, a.getId());
                          
        //Ejecutamos la consutla:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:      
        pstmt.close();
        connMySQL.cerrar();                
    }
    
    public void delete(int idAlmacen) throws Exception{
        String sql = "UPDATE almacen SET estatus = 0 WHERE idAlmacen = ?";
               
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la
        //inserción en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserción del registro:
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        //En este objeto guardamos el resultado de la consulta, la cual
        //nos devolverá los ID's que se generaron. En este caso, solo se
        //generará un ID:
        
        
        pstmt.setInt(1, idAlmacen);         
                          
        //Ejecutamos la consutla:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
       
        pstmt.close();
        connMySQL.cerrar();                
    }
    
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