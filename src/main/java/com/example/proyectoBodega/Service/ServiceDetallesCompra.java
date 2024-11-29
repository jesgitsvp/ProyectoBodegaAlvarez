package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.DetallesCompra;
import com.example.proyectoBodega.Repository.IRepositoryDetallesCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDetallesCompra implements IServiceDetallesCompra {
    @Autowired
    private IRepositoryDetallesCompra repositoryDetallesCompra;

    @Override
    public int Crear(DetallesCompra detalleCompra) {
        return repositoryDetallesCompra.Crear(detalleCompra);
    }

    @Override
    public List<DetallesCompra> Listar() {
        return repositoryDetallesCompra.Listar();
    }

    @Override
    public DetallesCompra ListarPorId(long id) {
        return repositoryDetallesCompra.ListarPorId(id);
    }

    @Override
    public int Modificar(DetallesCompra detalleCompra) {
        return repositoryDetallesCompra.Modificar(detalleCompra);
    }

    @Override
    public int Eliminar(long id) {
        return repositoryDetallesCompra.Eliminar(id);
    }
}
