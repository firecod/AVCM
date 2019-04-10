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
import model.Producto;

/**
 * Version: 1
 * @author: Diego Humberto Castro Castro
 * Fecha: 27 - 02 -2019
 * Descripción: Controller para gestionar productos: insert, update, delete, getAll, findByWhatever
 */
public class ControllerProducto {
 
    
    public int insert(Producto p) throws Exception{
        
        String sql = "INSERT INTO producto (nombre, marca, precio, categoria, estatus, idAlmacen, foto) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
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
        
        pstmt.setString(1, p.getNombre());
        pstmt.setString(2, p.getMarca());
        pstmt.setFloat(3, p.getPrecio());
        pstmt.setString(4, p.getCategoria());
        pstmt.setInt(5, 1);        
        pstmt.setInt(6, p.getAlmacen().getId());
        pstmt.setString(7, p.getFoto());
        
        //Ejecutamos la consutla:
        pstmt.executeUpdate();
        
        //Le pedimos al PreparedStatement los valores de las claves generadas,
        //que en este caso, solo es un valor:
        rs = pstmt.getGeneratedKeys();               
        
        if(rs.next()){
            idGenerado = rs.getInt(1);
            p.setId(idGenerado);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el ID generado:
        return idGenerado;           
    }
    
    public void update(Producto p) throws Exception{
        //Definimos la consulta SQL que realiza la inserción del registro:
        String sql =    "UPDATE producto SET nombre = ?, marca = ?, precio = ?, categoria = ?, estatus = ?, idAlmacen = ? " + 
                        "WHERE idProducto = ?";        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos los valores de los parametros de la consulta, basados
        //en los signos de interrogacion:
        pstmt.setString(1, p.getNombre());
        pstmt.setString(2, p.getMarca());
        pstmt.setFloat(3, p.getPrecio());
        pstmt.setString(4, p.getCategoria());
        pstmt.setInt(5, p.getEstatus());
        pstmt.setInt(6, p.getAlmacen().getId());
        pstmt.setInt(7, p.getId());
        
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
       public void delete(int id) throws Exception{
        //Definimos la consulta SQL que realiza la inserción del registro:
        String sql =    "UPDATE producto SET estatus = 0 WHERE idProducto = ?";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos el valor del ID del Producto a dar de baja:       
        pstmt.setInt(1, id);
        
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
       
       public List<Producto> getAll(String filtro, int estatus) throws Exception{
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM vistaProducto WHERE estatus = ?";
        
        //Aquí guardaremos objetos de tipo Producto. Una lista es un "contenedor"
        //dinamico de objetos. En este caso, queremos un contenedor de 
        //"productos". En otras palabras, queremos un contenedor que dentro
        //"contenga" objetos de tipo Producto:
        List<Producto> productos = new ArrayList<>();
        
        //Una variable temporal para crear nuevas instancias de Producto:
        Producto p = null;
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
            p = new Producto();
            a = new Almacen();
            //Llenamos sus propiedades:
            p.setEstatus(rs.getInt("estatus"));
            p.setId(rs.getInt("idProducto"));
            p.setMarca(rs.getString("marca"));
            p.setNombre(rs.getString("nombreProducto"));
            p.setPrecio(rs.getFloat("precio"));
            p.setCategoria(rs.getString("categoria"));            
            a.setId(rs.getInt("idAlmacen"));
            a.setNombre(rs.getString("nombreAlmacen"));
            p.setAlmacen(a);
                        
            //Agregamos el producto a la lista:
            productos.add(p);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinámica con los productos generados al 
        //realizar la consulta en la Base de Datos.
        return productos;
    }
}
