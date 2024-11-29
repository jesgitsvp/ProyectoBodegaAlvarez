package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface IServiceCategoria {
    int Crear(Categoria categoria);

    List<Categoria> Listar();

    Categoria ListarPorId(long id);

    int Modificar(Categoria categoria);

    int Eliminar(long id);

    List<Categoria> obtenerTodasLasCategorias();
}

