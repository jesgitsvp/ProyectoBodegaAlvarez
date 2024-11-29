package com.example.proyectoBodega.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria implements Serializable {
    private int idCategoria;
    private boolean estado;
    private String nombre;
    private Date fechaRegistro;
    private Date fechaEliminacion;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
