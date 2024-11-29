package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Cliente;

import java.util.List;

public interface IServiceCliente {
    int Crear(Cliente objeto);

    List<Cliente> Listar();

    Cliente ListarPorId(long id);

    int Modificar(Cliente cliente);

    int Eliminar(long id);
    List<Cliente> obtenerTodosLosClientes();
}
