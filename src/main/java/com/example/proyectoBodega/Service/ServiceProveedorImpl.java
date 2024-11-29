package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Model.Proveedor;
import com.example.proyectoBodega.Repository.IRepositoryProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProveedorImpl implements IServiceProveedor {
    @Autowired
    private IRepositoryProveedor repositoryProveedor;

    public ServiceProveedorImpl(IRepositoryProveedor repositoryProveedor) {
        this.repositoryProveedor = repositoryProveedor;
    }
    @Override
    public int Crear(Proveedor proveedor) {
        return repositoryProveedor.Crear(proveedor);
    }

    @Override
    public List<Proveedor> Listar() {
        return repositoryProveedor.Listar();
    }

    @Override
    public Proveedor ListarPorId(long id) {
        return repositoryProveedor.ListarPorId(id);
    }

    @Override
    public int Modificar(Proveedor proveedor) {
        return repositoryProveedor.Modificar(proveedor);
    }

    @Override
    public int Eliminar(long id) {
        return repositoryProveedor.Eliminar(id);
    }
    @Override
    public List<Proveedor> obtenerTodosLosProveedores()  { return repositoryProveedor.obtenerTodosLosProveedores();
    }
}
