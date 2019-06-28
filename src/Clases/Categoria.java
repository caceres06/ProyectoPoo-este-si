/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Mabel
 */
public class Categoria {
   public Conexion cb; 
    
    int id_categoria;
    String nombre;

    public Categoria(Conexion cb) {
        this.cb = cb;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    
    
    
 public boolean create(Movimiento g) throws SQLException {
        Connection conn = cb.getConnection();
        String query = "INSERT INTO Movimiento VALUES (?)";
        try{
            PreparedStatement pstm = conn.prepareStatement(query);
           
            pstm.setString(1,nombre);
            if(pstm.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } finally{
            conn.close();
        }
        return false;
    }
}
