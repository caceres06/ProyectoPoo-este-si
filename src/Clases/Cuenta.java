/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mabel
 */
public class Cuenta {

    public Conexion cb;
    int id_cuenta;
    String nombre;
    String tipo;
    float total;
    private int id_usuario;

    public Cuenta(Conexion cb) {
        this.cb = cb;
    }

    public Cuenta() {
    }

    public Cuenta(String nombre, String tipo, float total, int id_usuario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.total = total;
        this.id_usuario = id_usuario;
    }

    

 public boolean create(Movimiento g) throws SQLException {
        Connection conn = cb.getConnection();
        String query = "INSERT INTO Cuenta VALUES (?,?,?,?)";
        try{
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(2,nombre);
            pstm.setString(3,tipo);
            pstm.setFloat(4, total);
            pstm.setInt(5, id_usuario);
            
           
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

    public boolean delete(int id_cuenta) {
        boolean flag = false;
        Connection conn = cb.getConnection();
        String query = "DELETE FROM Movimiento WHERE id = ?";
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id_cuenta);
            int rows = stm.executeUpdate();
            if(rows!=0) flag = true;
            conn.close();
        }catch(SQLException ex){
            System.out.println("Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return flag;
        
    }

    public ArrayList<Cuenta> Read() {
        Connection conn = cb.getConnection();
        ArrayList<Cuenta> c = new ArrayList();
        String query = "SELEC *FROM CUENTA";
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Cuenta cuenta = new Cuenta();
                cuenta.setId_cuenta(rs.getInt("idCuenta"));
                cuenta.setNombre(rs.getString("nombre"));
                cuenta.setTipo(rs.getString("tipo"));
                cuenta.setTotal(rs.getFloat("total"));
                cuenta.setId_usuario(rs.getInt("id_usuario"));
                c.add(cuenta);
            }
            //conn.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return c;
    }







    public Conexion getCb() {
        return cb;
    }

    public void setCb(Conexion cb) {
        this.cb = cb;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean crear(Cuenta g) {
        return false;
        
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
}