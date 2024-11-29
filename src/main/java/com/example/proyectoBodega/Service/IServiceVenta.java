package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Proveedor;
import com.example.proyectoBodega.Model.Venta;

import java.util.List;

public interface IServiceVenta {
    int Crear(Venta venta);

    List<Venta> Listar();

    Venta ListarPorId(long id);

    int Modificar(Venta venta);

    int Eliminar(long id);
    List<Venta> obtenerTodasLasVentas();
}
