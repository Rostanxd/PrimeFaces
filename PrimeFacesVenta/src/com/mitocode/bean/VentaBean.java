package com.mitocode.bean;

import com.mitocode.dao.VentaDAO;
import com.mitocode.model.Producto;
import com.mitocode.model.Venta;
import com.mitocode.model.Venta_det;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 23/03/2016.
 */
@ManagedBean
@ViewScoped
public class VentaBean {
    private Venta venta = new Venta();
    private Producto producto = new Producto();
    private int cantidad = 0;
    private List<Venta_det> lista = new ArrayList<>();

    public List<Venta_det> getLista() {
        return lista;
    }

    public void setLista(List<Venta_det> lista) {
        this.lista = lista;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public void agregar(){
        Venta_det det = new Venta_det();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        this.lista.add(det);
    }

    public void registrar() throws Exception {
        VentaDAO dao;
        double monto = 0;
        try{
            for (Venta_det det : lista){
                monto += det.getProducto().getPrecio()*det.getCantidad();
            }
            venta.setMonto(monto);

            dao = new VentaDAO();
            dao.registrar(venta,lista);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Ingeso de Venta","Exito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Ingeso de Venta","Error"));
            throw e;
        }finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

}
