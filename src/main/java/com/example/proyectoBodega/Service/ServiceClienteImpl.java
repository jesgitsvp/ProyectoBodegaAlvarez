package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Cliente;
import com.example.proyectoBodega.Repository.IRepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceClienteImpl implements IServiceCliente {
    @Autowired
    private IRepositoryCliente repositoryCliente;

    @Override
    public int Crear(Cliente objeto) {
        return repositoryCliente.Crear(objeto);
    }

    @Override
    public List<Cliente> Listar() {
        return repositoryCliente.Listar();
    }

    @Override
    public Cliente ListarPorId(long id) {
        return repositoryCliente.ListarPorId(id);
    }

    @Override
    public int Modificar(Cliente cliente) {
        return repositoryCliente.Modificar(cliente);
    }

    @Override
    public int Eliminar(long id) {
        return repositoryCliente.Eliminar(id);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes()  {return repositoryCliente.obtenerTodosLosClientes();
    }
}