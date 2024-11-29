package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.*;

import java.util.List;

public interface IServiceCompra {
    int Crear(Compra compra);

    List<Compra> Listar();

    Compra ListarPorId(long id);

    int Modificar(Compra compra);

    int Eliminar(long id);
    List<Compra> obtenerTodasLasCompras();
    List<Producto> obtenerTodosLosProductos();
    List<Categoria> obtenerTodasLasCategorias();

    List<Proveedor> obtenerTodosLosProveedores();
}
