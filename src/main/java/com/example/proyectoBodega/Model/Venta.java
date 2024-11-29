package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta implements Serializable {
    private int idVenta;
    private int idProducto;
    private int idCliente;
    private int idEmpleado;
    private Boolean estado;
    private int cantidad;
    private Date fechaVenta;
    private String metodoPago;
    private Date fechaRegistro;
    private Date fechaEliminacion;
    //public int getIdVenta() {return  idVenta;}

    //public void setIdVenta(int idVenta) {this.idVenta = idVenta;}
}
