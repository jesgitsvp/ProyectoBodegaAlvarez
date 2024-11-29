package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Proveedor;
import com.example.proyectoBodega.Model.Venta;

import java.util.List;

public interface IRepositoryVenta {
    List<Venta> Listar();

    Venta ListarPorId(long id);

    int Crear(Venta objeto);

    int Modificar(Venta objeto);

    int Eliminar(long id);
    List<Venta> obtenerTodasLasVentas();
}
