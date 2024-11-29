package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor implements Serializable {
    private int idProveedor;
    private Boolean estado;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String ruc;
    private Date fechaRegistro;
    private Date fechaEliminacion;
    public int getIdProveedor() {
        return  idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
}
