package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Repository.IRepositoryEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEmpleadoImpl implements IServiceEmpleado {
    @Autowired
    private IRepositoryEmpleado repositoryEmpleado;

    @Override
    public int Crear(Empleado empleado) {
        return repositoryEmpleado.Crear(empleado);
    }

    @Override
    public List<Empleado> Listar() {
        return repositoryEmpleado.Listar();
    }

    @Override
    public Empleado ListarPorId(long id) {
        return repositoryEmpleado.ListarPorId(id);
    }

    @Override
    public int Modificar(Empleado empleado) {
        return repositoryEmpleado.Modificar(empleado);
    }

    @Override
    public int Eliminar(long id) {
        return repositoryEmpleado.Eliminar(id);
    }
    @Override
    public List<Empleado> obtenerTodosLosEmpleados()  { return repositoryEmpleado.obtenerTodosLosEmpleados();
    }
}
