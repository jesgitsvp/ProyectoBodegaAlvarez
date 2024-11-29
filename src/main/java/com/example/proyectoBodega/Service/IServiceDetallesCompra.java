package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.DetallesCompra;

import java.util.List;

public interface IServiceDetallesCompra {
    int Crear(DetallesCompra detallesCompra);

    List<DetallesCompra> Listar();

    DetallesCompra ListarPorId(long id);

    int Modificar(DetallesCompra detallesCompra);

    int Eliminar(long id);
}
