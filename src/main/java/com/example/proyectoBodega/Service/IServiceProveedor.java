package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Model.Producto;
import com.example.proyectoBodega.Model.Proveedor;

import java.util.List;

public interface IServiceProveedor {
    int Crear(Proveedor proveedor);

    List<Proveedor> Listar();

    Proveedor ListarPorId(long id);

    int Modificar(Proveedor proveedor);

    int Eliminar(long id);

    List<Proveedor> obtenerTodosLosProveedores();

}
