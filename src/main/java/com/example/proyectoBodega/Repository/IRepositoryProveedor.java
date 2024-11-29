package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Proveedor;

import java.util.List;

public interface IRepositoryProveedor {
    List<Proveedor> Listar();

    Proveedor ListarPorId(long id);

    int Crear(Proveedor objeto);

    int Modificar(Proveedor proveedor);

    int Eliminar(long id);
    List<Proveedor> obtenerTodosLosProveedores() ;

}
