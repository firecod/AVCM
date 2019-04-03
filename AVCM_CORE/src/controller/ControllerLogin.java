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
import java.sql.SQLException;
import model.Cliente;
import model.Persona;
import model.Usuario;
import model.Vendedor;

/**
 *
 * @author Vanessa
 */
public class ControllerLogin {
    
    public Object login(String usuario, String password) throws Exception{
        
        String sql1 = "SELECT * FROM v_vendedor " +
                      "WHERE nombreUsuario = ? AND contrasenia = ? AND estatus = 1";
        String sql2 = "SELECT * FROM v_clientes " +
                      "WHERE nombreUsuario = ? AND contrasenia = ? AND estatus = 1";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.abrir();
        
        PreparedStatement pstmt = conn.prepareStatement(sql1);
        
        ResultSet rs = null;
        
        Cliente c = null;
        Vendedor v = null;
        Persona p = null;
        Usuario u = null;
        
        pstmt.setString(1, usuario);
        pstmt.setString(2, password);
        
        rs = pstmt.executeQuery();
        if(rs.next())
        {
            v = new Vendedor();
            p = new Persona();
            u = new Usuario();
            
            p.setId(rs.getInt("idPersona"));
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setNombre(rs.getString("nombre"));
            p.setDomicilio(rs.getString("domicilio"));
            p.setRfc(rs.getString("rfc"));
            
            u.setId(rs.getInt("idUsuario"));
            u.setRol(rs.getString("rol"));
            u.setNombreUsuario(rs.getString("nombreUsuario"));
            u.setContrasenia(rs.getString("contrasenia"));
            
            v.setId(rs.getInt("idVendedor"));
            v.setNumeroVendedor(rs.getString("numeroVendedor"));
            v.setReputacion(rs.getInt("reputacion"));
            v.setFotografíaVendedor(rs.getString("fotografiaVendedor"));
            v.setEstatus(rs.getInt("estatus"));
            
            v.setPersona(p);
            v.setUsuario(u);
            
        }
        rs.close();
        pstmt.close();
        
        if(v != null){
            connMySQL.cerrar();
            return v;
        }
        
        pstmt = conn.prepareStatement(sql2);
        pstmt.setString(1, usuario);
        pstmt.setString(2, password);
        rs = pstmt.executeQuery();
        
        if(rs.next()){
            c = new Cliente();
            p = new Persona();
            u = new Usuario();
            
            p.setId(rs.getInt("idPersona"));
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setNombre(rs.getString("nombre"));
            p.setDomicilio(rs.getString("domicilio"));
            p.setRfc(rs.getString("rfc"));
            
            u.setId(rs.getInt("idUsuario"));
            u.setRol(rs.getString("rol"));
            u.setNombreUsuario(rs.getString("nombreUsuario"));
            u.setContrasenia(rs.getString("contrasenia"));
        
            c.setId(rs.getInt("idCliente"));
            c.setNumeroCliente(rs.getString("numeroCliente"));
            c.setCorreoElectronico(rs.getString("correoElectronico"));
            c.setEstatus(rs.getInt("estatus"));
            
            c.setPersona(p);
            c.setUsuario(u);
            
            rs.close();
            pstmt.close();
            connMySQL.cerrar();
            
            return c;
        }
        
        return null;
    }
    
}
