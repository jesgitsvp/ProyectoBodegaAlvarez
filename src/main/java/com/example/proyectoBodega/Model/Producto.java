package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {
    private int idProducto;
    private int idCategoria;
    private int idProveedor;
    private Boolean estado;
    private String nombre;
    private String descripcion;
    private double precio;
    private double costo;
    private int stock;
    private Date fechaRegistro;
    private Date fechaEliminacion;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    }
