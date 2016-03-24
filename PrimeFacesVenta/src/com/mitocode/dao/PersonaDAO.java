package com.mitocode.dao;

import com.mitocode.model.Persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 22/03/2016.
 */
public class PersonaDAO extends DAO{
    public void registrar(Persona per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("" +
                    "INSERT INTO Persona (nombre, sexo) VALUES(?,?)");
            st.setString(1,per.getNombre());
            st.setString(2,per.getSexo());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally {
            this.desconectar();
        }
    }

    public List<Persona> listar() throws Exception {
        List<Persona> lista;
        ResultSet rs = null;
        try{
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("" +
                    "SELECT * FROM Persona");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while(rs.next()){
                Persona per = new Persona();
                per.setCodigo(rs.getInt("codigo"));
                per.setNombre(rs.getString("nombre"));
                per.setSexo(rs.getString("sexo"));

                lista.add(per);

            }
        }catch(Exception e){
            throw e;
        }finally {
            this.desconectar();
        }
        return lista;
    }


    public Persona leerId(Persona per) throws Exception {
        Persona pers = null;
        ResultSet rs = null;
        try{
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("SELECT * FROM Persona WHERE codigo = ?");
            st.setInt(1, per.getCodigo());
            rs = st.executeQuery();
            while(rs.next()){
                pers = new Persona();
                pers.setCodigo(rs.getInt("codigo"));
                pers.setNombre(rs.getString("nombre"));
                pers.setSexo(rs.getString("sexo"));
            }
        }catch(Exception e){
            throw e;
        }
        return pers;
    }

    public void modificar(Persona per) throws Exception {
        try{
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("UPDATE persona SET " +
                    "nombre = ?, sexo = ? " +
                    "WHERE codigo = ?");
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.setInt(3, per.getCodigo());

            st.executeUpdate();

        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    public void eliminar(Persona per) throws Exception {
        try{
            this.conectar();
            PreparedStatement st = this.getCon().prepareStatement("DELETE FROM persona WHERE codigo = ?");
            st.setInt(1, per.getCodigo());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }


}
