package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.*;

import java.util.List;

public interface IRepositoryCompra {
    List<Compra> Listar();

    Compra ListarPorId(long id);

    int Crear(Compra objeto);

    int Modificar(Compra objeto);

    int Eliminar(long id);
    List<Compra> obtenerTodasLasCompras() ;
    List<Proveedor> obtenerTodosLosProveedores();
    List<Categoria> obtenerTodasLasCategorias();
    List<Producto> obtenerTodosLosProductos();
}
