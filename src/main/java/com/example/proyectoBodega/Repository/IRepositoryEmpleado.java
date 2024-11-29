package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Empleado;

import java.util.List;

public interface IRepositoryEmpleado {
    List<Empleado> Listar();

    Empleado ListarPorId(long id);

    int Crear(Empleado objeto);

    int Modificar(Empleado empleado);

    int Eliminar(long id);
    List<Empleado> obtenerTodosLosEmpleados() ;
}
