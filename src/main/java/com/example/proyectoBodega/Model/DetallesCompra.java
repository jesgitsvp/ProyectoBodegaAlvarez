package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesCompra implements Serializable {
    private int idDetallesCompra;
    private int idProducto;
    private int idCompra;
    private boolean estado;
    private int cantidad;
    private double precio;
    private Date fechaRegistro;
    private Date fechaEliminacion;
}
