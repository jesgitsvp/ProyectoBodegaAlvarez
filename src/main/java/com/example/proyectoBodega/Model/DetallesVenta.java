package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesVenta implements Serializable {
    private int idDetallesVenta;
    private int idProducto;
    private int idVenta;
    private Boolean estado;
    private int cantidad;
    private double precio;
    private Date fechaRegistro;
    private Date fechaEliminacion;
}
