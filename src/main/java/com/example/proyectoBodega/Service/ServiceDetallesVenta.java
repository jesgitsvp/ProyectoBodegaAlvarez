package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.DetallesVenta;
import com.example.proyectoBodega.Repository.IRepositoryDetallesVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceDetallesVenta implements  IServiceDetallesVenta{
    @Autowired
    private IRepositoryDetallesVenta repositoryDetallesVenta;

    @Override
    public int Crear(DetallesVenta detallesVenta) {
        return repositoryDetallesVenta.Crear(detallesVenta);
    }

    @Override
    public List<DetallesVenta> Listar() {
        return repositoryDetallesVenta.Listar();
    }

    @Override
    public DetallesVenta ListarPorId(long id) {
        return repositoryDetallesVenta.ListarPorId(id);
    }

    @Override
    public int Modificar(DetallesVenta detallesVenta) {
        return repositoryDetallesVenta.Modificar(detallesVenta);
    }

    @Override
    public int Eliminar(long id) {
        return repositoryDetallesVenta.Eliminar(id);
    }
}
