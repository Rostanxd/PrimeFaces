package com.mitocode.bean;

import com.mitocode.dao.PersonaDAO;
import com.mitocode.model.Persona;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped

public class PersonaBean {
    private Persona persona = new Persona();
    private List<Persona> lstPersona;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public void setLstPersona(List<Persona> lstPersona) {
        this.lstPersona = lstPersona;
    }

    public List<Persona> getLstPersona() {
        return lstPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    private boolean isPostBack(){
        boolean r;
        r = FacesContext.getCurrentInstance().isPostback();
        return r;
    }

    public void listar(String valor) throws Exception {
        PersonaDAO dao;
        try{
            if(valor.equals("F")) {
                if (isPostBack() == false) {
                    dao = new PersonaDAO();
                    lstPersona = dao.listar();
                }
            }else{
                dao = new PersonaDAO();
                lstPersona = dao.listar();
            }
        }catch(Exception e){
            throw e;
        }
    }

    public void leerId(Persona per) throws Exception {
        PersonaDAO dao;
        Persona temp;
        try{
            dao = new PersonaDAO();
            temp = dao.leerId(per);
            if (temp != null){
                this.persona = temp;
                this.accion = "Modificar";
            }
        }catch(Exception e){
            throw e;
        }
    }

    public void operar() throws Exception {
        switch (accion){
            case "Registrar":
                this.registrar();
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }

    private void registrar() throws Exception {
        PersonaDAO dao;
        try{
            dao = new PersonaDAO();
            dao.registrar(persona);
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }

    private void modificar() throws Exception {
        PersonaDAO dao;
        try{
            dao = new PersonaDAO();
            dao.modificar(persona);
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }

    public void eliminar(Persona per) throws Exception {
        PersonaDAO dao;
        try{
            dao = new PersonaDAO();
            dao.eliminar(per);
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }

    public void limpiar(){
        this.persona.setCodigo(0);
        this.persona.setNombre("");
        this.persona.setSexo("");
    }
}
