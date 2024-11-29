package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Producto;

import java.util.List;

public interface IServiceProducto {
    int Crear(Producto producto);

    List<Producto> Listar();

    Producto ListarPorId(long id);


    int Modificar(Producto producto);

    int Eliminar(long id);

    List<Producto> obtenerTodosLosProductos();
    List<Categoria> obtenerTodasLasCategorias();





}
