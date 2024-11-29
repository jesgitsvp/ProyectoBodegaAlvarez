package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra implements Serializable {
    private int idCompra;
    private int idProducto;
    private int idProveedor;
    private int idEmpleado;
    private Boolean estado;
    private int stock;
    private double costo;
    private Date fechaCompra;
    private Date fechaRegistro;
    private Date fechaEliminacion;
}
