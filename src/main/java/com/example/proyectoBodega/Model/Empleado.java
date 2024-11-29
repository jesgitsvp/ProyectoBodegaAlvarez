package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado implements Serializable{
    private int idEmpleado;
    private Boolean estado;
    private String nombre;
    private String apellido;
    private double salario;
    private String telefono;
    private String DNI;
    private int tiempoDeTrabajo;
    private String cargo;
    private Date fechaRegistro;
    private Date fechaEliminacion;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}
