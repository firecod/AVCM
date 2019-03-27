package controller;

import DB.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author diegg
 */
public class ControllerCliente {
    
    public int insert(Cliente c) throws Exception{
        String sql = "INSERT INTO cliente (nombre, domicilio, estatus) "
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
        
        //pstmt.setString(1, a.getNombre());
        //pstmt.setString(2, a.getDomicilio());               
        pstmt.setInt(3, 1);                       
        //Ejecutamos la consutla:
        pstmt.executeUpdate();
        
        //Le pedimos al PreparedStatement los valores de las claves generadas,
        //que en este caso, solo es un valor:
        rs = pstmt.getGeneratedKeys();               
        
        if(rs.next()){
            idGenerado = rs.getInt(1);
            c.setId(idGenerado);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        return idGenerado;
    }
    
    public List getAll(String estatus) throws Exception{
        List<Cliente> clientes = new ArrayList<>();
        
        return clientes;
    }
}