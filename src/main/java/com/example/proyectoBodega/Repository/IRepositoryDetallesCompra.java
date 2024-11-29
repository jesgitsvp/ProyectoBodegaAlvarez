package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.DetallesCompra;

import java.util.List;

public interface IRepositoryDetallesCompra {
    List<DetallesCompra> Listar();

    DetallesCompra ListarPorId(long id);

    int Crear(DetallesCompra objeto);

    int Modificar(DetallesCompra objeto);

    int Eliminar(long id);
}
