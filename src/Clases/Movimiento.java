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
public class Movimiento {
    public Conexion cb;
        private int id;
        private int idCate;
        private int idCuenta;
        private int idOpe;
        private float monto;
        private String fecha;
        private String Usuario;
        private String cuenta;
        private String categoria;
        private String operacion;

    public Movimiento() {
    }
        

    public Movimiento(int idCate, int idCuenta, int idOpe, float monto, String fecha) {
        this.idCate = idCate;
        this.idCuenta = idCuenta;
        this.idOpe = idOpe;
        this.monto = monto;
        this.fecha = fecha;
    }
        
    public Movimiento(Conexion cb) {
        this.cb = cb;
    }
        
        
    public boolean create(Movimiento g) throws SQLException {
        Connection conn = cb.getConnection();
        String query = "INSERT INTO Movimiento VALUES (?,?,?,?,to_date(?,YYYY-MM-DD)";
        try{
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(2, idCate);
            pstm.setInt(3, idCuenta);
            pstm.setInt(4, idOpe);
            pstm.setFloat(5, monto);
            pstm.setString(6,fecha);
           
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

    public boolean delete(int id) {
        boolean flag = false;
        Connection conn = cb.getConnection();
        String query = "DELETE FROM Movimiento WHERE id = ?";
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            int rows = stm.executeUpdate();
            if(rows!=0) flag = true;
            conn.close();
        }catch(SQLException ex){
            System.out.println("Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return flag;
        
    }

    public boolean update(int id,Movimiento mov) {
     boolean flag = false;
        Connection conn = cb.getConnection();
        String query = "UPDATE movimiento SET id_categoria= ?, id_cuenta= ?, id_operacion = ?,monto=?, fecha=?  WHERE idMovimiento = ?";
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            
            stm.setInt(2, mov.getIdCate());
            stm.setInt(3, mov.getIdCuenta());
            stm.setInt(4, mov.getIdOpe());
            stm.setFloat(5, mov.getMonto());
            stm.setString(6, mov.getFecha());
            
           if(stm.executeUpdate()!=0) flag = true; 
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return flag;
    }

    public ArrayList<Movimiento> Read() {
        Connection conn = cb.getConnection();
        ArrayList<Movimiento> mov = new ArrayList();
        String query = "SELECT m.id,c.nombre,cu.nombre,o.nombre, m.monto,m.fecha\n" +
        "FROM movimiento m, categoria c, operacion o, usuario u WHERE m.idcategoria=c.id_cateoria"
                + "and m.id_cuenta=cu.idcuenta and m.id_operacion=o.idoperacion";
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Movimiento movi = new Movimiento();
                movi.setId(rs.getInt("id"));
                movi.setCategoria(rs.getString("nombre"));
                movi.setCuenta(rs.getString("nombre"));
                movi.setOperacion(rs.getString("nombre"));
                movi.setMonto(rs.getFloat("monto"));
                movi.setFecha(rs.getString("fecha"));
                mov.add(movi);
            }
            //conn.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return mov;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCate() {
        return idCate;
    }

    public void setIdCate(int idCate) {
        this.idCate = idCate;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdOpe() {
        return idOpe;
    }

    public void setIdOpe(int idOpe) {
        this.idOpe = idOpe;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
}
