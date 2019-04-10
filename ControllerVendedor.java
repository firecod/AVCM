/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DB.ConexionMySQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Persona;
import model.Usuario;
import model.Vendedor;

/**
 *
 * @author Vanessa
 */
public class ControllerVendedor {
    
    public int insert(Vendedor v) throws Exception{
        String sql = "{CALL registroVendedor (?, ?, ?, ?, ?, ?," //Datos de Persona
                                          + "?, ?,"             //Datos de Usuario
                                          + "?, ?, ?,"                //Datos de Cliente
                                          + "?, ?, ?, ?)}";    //Datos de Salida
        
        //Aquí guardaremos el ID que se generará
        int idPersonaGenerado = -1;
        int idUsuarioGenerado = -1;
        int idClienteGenerado = -1;
        String numeroUnicoGenerado = "";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la
        //inserción en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserción del registro:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //En este objeto guardamos el resultado de la consulta, la cual
        //nos devolverá los ID's que se generaron. En este caso, solo se
        //generará un ID:
        ResultSet rs = null;
        
        cstmt.setString(1, v.getPersona().getNombre());
        cstmt.setString(2, v.getPersona().getApellidoPaterno());
        cstmt.setString(3, v.getPersona().getApellidoMaterno());
        cstmt.setString(4, v.getPersona().getRfc());
        cstmt.setString(5, v.getPersona().getDomicilio());
        cstmt.setString(6, v.getPersona().getTelefono());
        
        cstmt.setString(7, v.getUsuario().getNombreUsuario());
        cstmt.setString(8, v.getUsuario().getContrasenia());
        
        cstmt.setString(9, "");
        cstmt.setInt(10, v.getReputacion());
        cstmt.setInt(11, v.getAlmacen());
        
        cstmt.registerOutParameter(12, Types.INTEGER);
        cstmt.registerOutParameter(13, Types.INTEGER);
        cstmt.registerOutParameter(14, Types.INTEGER);
        cstmt.registerOutParameter(15, Types.VARCHAR);
        
        //Ejecutamos la consutla:
        cstmt.executeUpdate();
        
        //Recuperamos los ID's generados:
        idPersonaGenerado = cstmt.getInt(12);
        idUsuarioGenerado = cstmt.getInt(13);
        idClienteGenerado = cstmt.getInt(14);
        numeroUnicoGenerado = cstmt.getString(15);          
        
        //Los guardamos en el objeto Cliente que nos pasaron como parámetro:
        v.getPersona().setId(idPersonaGenerado);
        v.getUsuario().setId(idUsuarioGenerado);
        v.setId(idClienteGenerado);
        v.setNumeroVendedor(numeroUnicoGenerado);
        
        //Cerramos los objetos de Base de Datos:
        cstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el ID de Empleado generado:
        return idClienteGenerado;
    }
    
    public void update(Vendedor v) throws Exception{
        String sql = "{CALL actualizarVendedor (?, ?, ?, ?, ?, ?, " //Datos de Persona
                                          + "?, ?, "             //Datos de Usuario
                                          + "?, ?, ?, ?)}";    //Datos de Salida
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la
        //inserción en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserción del registro:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //En este objeto guardamos el resultado de la consulta, la cual
        //nos devolverá los ID's que se generaron. En este caso, solo se
        //generará un ID:
        ResultSet rs = null;
        
        cstmt.setString(1, v.getPersona().getNombre());
        cstmt.setString(2, v.getPersona().getApellidoPaterno());
        cstmt.setString(3, v.getPersona().getApellidoMaterno());
        cstmt.setString(4, v.getPersona().getRfc());
        cstmt.setString(5, v.getPersona().getDomicilio());
        cstmt.setString(6, v.getPersona().getTelefono());
        
        cstmt.setString(7, v.getUsuario().getNombreUsuario());
        cstmt.setString(8, v.getUsuario().getContrasenia());
        
        cstmt.setInt(9, v.getId());
        cstmt.setString(10, "");
        cstmt.setInt(11, v.getReputacion());
        cstmt.setInt(12, v.getAlmacen());
        
        //Ejecutamos la consutla:
        cstmt.executeUpdate();
                
        //Cerramos los objetos de Base de Datos:
        cstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el ID de Empleado generado:
        
    }
    
    public List<Vendedor> getAll(String filtro, int estatus) throws Exception
    {
        //La consulta SQL a ejecutar:
        String sql = "SELECT * FROM v_vendedores";
        
        //La lista dinámica donde guardaremos objetos de tipo Empleado
        //por cada registro que devuelva la BD:
        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        
        //Una variable temporal para crear nuevos objetos de tipo Persona:
        Persona p = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Usuario:
        Usuario u = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Empleado:
        Vendedor v = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // Establecemos filtro de busqueda  
        pstmt.setInt(1, estatus);
        
        //Aquí guardaremos los resultados de la consulta:
        ResultSet rs = pstmt.executeQuery();
        
        
        //Iteramos el conjunto de registros devuelto por la BD.
        //"Si hay un siguiente registro, nos movemos":
        while (rs.next())
        {
            //Creamos un nuevo objeto de tipo Persona:
            p = new Persona();
            
            //Llenamos sus datos:
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setDomicilio(rs.getString("domicilio"));
            p.setNombre(rs.getString("nombre"));
            p.setRfc(rs.getString("rfc"));
            p.setTelefono(rs.getString("telefono"));
            
            //Creamos un nuevo objeto de tipo Usuario:
            u = new Usuario();
            u.setContrasenia(rs.getString("contrasenia"));
            u.setNombreUsuario(rs.getString("nombreUsuario"));
            
            //Creamos un nuevo objeto de tipo Empleado:
            v = new Vendedor();
            
            //Establecemos sus datos personales:
            
            v.setId(rs.getInt("idVendedor"));
            v.setPersona(p);
            v.setUsuario(u);
            v.setReputacion(rs.getInt("reputacion"));
            v.setAlmacen(rs.getInt("almacen"));
            
         
            
            //Establecemos su persona:
            v.setPersona(p);
            
            //Establecemos su Usuario:
            v.setUsuario(u);
            
            //Agregamos el objeto de tipo Empleado a la lista dinámica:
            vendedores.add(v);
       
        }
        //Cerramos los objetos de BD:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinámica con objetos de tipo Empleado dentro:
        return vendedores;
    }
    
        public void delete(int id) throws Exception
    {
        String sql = "UPDATE vendedor SET estatus = 0 WHERE idVendedor = ?";
        //delete cliente where idcliente = ?;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos el valor del ID de la Sucursal a dar de baja:       
        pstmt.setInt(1, id);
        //(1,id)
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
}
