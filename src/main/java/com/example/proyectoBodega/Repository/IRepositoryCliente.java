package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Cliente;

import java.util.List;

public interface IRepositoryCliente {
    List<Cliente> Listar();

    Cliente ListarPorId(long id);

    int Crear(Cliente objeto);

    int Modificar(Cliente cliente);

    int Eliminar(long id);
    List<Cliente> obtenerTodosLosClientes() ;
}
