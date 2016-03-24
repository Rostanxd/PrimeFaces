package com.mitocode.dao;

import com.mitocode.model.Venta;
import com.mitocode.model.Venta_det;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by HP on 24/03/2016.
 */
public class VentaDAO extends DAO{
    public void registrar(Venta venta, List<Venta_det> lista) throws Exception {
        try {
            this.conectar();
            this.getCon().setAutoCommit(false);

            PreparedStatement st = this.getCon().prepareStatement("" +
                    "INSERT INTO Venta (fecha,codPersona,monto) VALUES(?,?,?)");
            st.setDate(1,venta.getFecha());
            st.setInt(2,venta.getPersona().getCodigo());
            st.setDouble(3,venta.getMonto());
            st.executeUpdate();
            st.close();

            int codVenta = 0;
            PreparedStatement st3 = this.getCon().prepareStatement("" +
                    "SELECT LAST_INSERT_ID() FROM venta limit 1");
            ResultSet rs;
            rs = st3.executeQuery();
            while (rs.next()){
                codVenta = rs.getInt(1);
            }
            rs.close();

            int linea = 0;
            for (Venta_det det : lista) {
                linea += 1;
                PreparedStatement st2 = this.getCon().prepareStatement("" +
                        "INSERT INTO venta_det (linea,codVenta,codProducto,cantidad) VALUES(?,?,?,?)");
                st2.setInt(1, linea);
                st2.setInt(2, codVenta);
                st2.setInt(3, det.getProducto().getCodigo());
                st2.setInt(4, det.getCantidad());
                st2.executeUpdate();
                st2.close();
                System.out.println(linea);
            }

            this.getCon().commit();
        }catch(Exception e){
            this.getCon().rollback();
            throw e;
        }finally {
            this.desconectar();
        }
    }
}
