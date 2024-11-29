package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {
    private int idCliente;
    private Boolean estado;
    private String nombre;
    private String direccion;
    private String telefono;
    private Date fechaRegistro;
    private Date fechaEliminacion;


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
