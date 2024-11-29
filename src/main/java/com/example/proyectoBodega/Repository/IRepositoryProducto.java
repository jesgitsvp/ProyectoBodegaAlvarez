package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Producto;
import com.example.proyectoBodega.Model.Proveedor;

import java.util.List;

public interface IRepositoryProducto {
    List<Producto> Listar();

 Producto ListarPorId(long id);


    int Crear(Producto objeto);

    int Modificar(Producto producto);

    int Eliminar(long id);

    List<Categoria> obtenerTodasLasCategorias();
    List<Producto> obtenerTodosLosProductos();


}
