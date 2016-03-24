package com.mitocode.dao;

import com.mitocode.model.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 22/03/2016.
 */
public class ProductoDAO extends DAO{
    public void registrar(Producto per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("" +
                    "INSERT INTO Producto (nombre, precio) VALUES(?,?)");
            st.setString(1,per.getNombre());
            st.setDouble(2,per.getPrecio());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally {
            this.desconectar();
        }
    }

    public List<Producto> listar() throws Exception {
        List<Producto> lista;
        ResultSet rs = null;
        try{
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("" +
                    "SELECT * FROM Producto");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while(rs.next()){
                Producto per = new Producto();
                per.setCodigo(rs.getInt("codigo"));
                per.setNombre(rs.getString("nombre"));
                per.setPrecio(rs.getDouble("precio"));

                lista.add(per);

            }
        }catch(Exception e){
            throw e;
        }finally {
            this.desconectar();
        }
        return lista;
    }


    public Producto leerId(Producto per) throws Exception {
        Producto pers = null;
        ResultSet rs = null;
        try{
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("SELECT * FROM Producto WHERE codigo = ?");
            st.setInt(1, per.getCodigo());
            rs = st.executeQuery();
            while(rs.next()){
                pers = new Producto();
                pers.setCodigo(rs.getInt("codigo"));
                pers.setNombre(rs.getString("nombre"));
                pers.setPrecio(rs.getDouble("precio"));
            }
        }catch(Exception e){
            throw e;
        }
        return pers;
    }

    public void modificar(Producto per) throws Exception {
        try{
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("UPDATE Producto SET " +
                    "nombre = ?, precio = ? " +
                    "WHERE codigo = ?");
            st.setString(1, per.getNombre());
            st.setDouble(2, per.getPrecio());
            st.setInt(3, per.getCodigo());

            st.executeUpdate();

        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    public void eliminar(Producto per) throws Exception {
        try{
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("DELETE FROM Producto WHERE codigo = ?");
            st.setInt(1, per.getCodigo());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }


}
