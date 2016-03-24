package com.mitocode.bean;

import com.mitocode.dao.ProductoDAO;
import com.mitocode.model.Producto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped

public class ProductoBean {
    private Producto Producto = new Producto();
    private List<Producto> lstProducto;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public void setLstProducto(List<Producto> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public List<Producto> getLstProducto() {
        return lstProducto;
    }

    public Producto getProducto() {
        return Producto;
    }

    public void setProducto(Producto producto) {
        this.Producto = producto;
    }

    private boolean isPostBack(){
        boolean r;
        r = FacesContext.getCurrentInstance().isPostback();
        return r;
    }

    public void listar(String valor) throws Exception {
        ProductoDAO dao;
        try{
            if(valor.equals("F")) {
                if (isPostBack() == false) {
                    dao = new ProductoDAO();
                    lstProducto = dao.listar();
                }
            }else{
                dao = new ProductoDAO();
                lstProducto = dao.listar();
            }
        }catch(Exception e){
            throw e;
        }
    }

    public void leerId(Producto per) throws Exception {
        ProductoDAO dao;
        Producto temp;
        try{
            dao = new ProductoDAO();
            temp = dao.leerId(per);
            if (temp != null){
                this.Producto = temp;
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
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            dao.registrar(Producto);
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }

    private void modificar() throws Exception {
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            dao.modificar(Producto);
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }

    public void eliminar(Producto per) throws Exception {
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            dao.eliminar(per);
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }

    public void limpiar(){
        this.Producto.setCodigo(0);
        this.Producto.setNombre("");
        this.Producto.setPrecio(0.0);
    }
}
