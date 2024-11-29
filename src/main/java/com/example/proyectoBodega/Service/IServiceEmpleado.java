package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Empleado;

import java.util.List;

public interface IServiceEmpleado {
    int Crear(Empleado empleado);

    List<Empleado> Listar();

    Empleado ListarPorId(long id);

    int Modificar(Empleado empleado);

    int Eliminar(long id);
    List<Empleado> obtenerTodosLosEmpleados();
}
