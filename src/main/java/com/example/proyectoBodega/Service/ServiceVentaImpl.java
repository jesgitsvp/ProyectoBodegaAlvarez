package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Venta;
import com.example.proyectoBodega.Repository.IRepositoryVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceVentaImpl implements IServiceVenta {
    @Autowired
    private IRepositoryVenta repositoryVenta;

    @Override
    public int Crear(Venta venta) {
        return repositoryVenta.Crear(venta);
    }

    @Override
    public List<Venta> Listar() {
        return repositoryVenta.Listar();
    }

    @Override
    public Venta ListarPorId(long id) {
        return repositoryVenta.ListarPorId(id);
    }

    @Override
    public int Modificar(Venta venta) {
        return repositoryVenta.Modificar(venta);
    }

    @Override
    public int Eliminar(long id) {
        return repositoryVenta.Eliminar(id);
    }

    @Override
    public List<Venta> obtenerTodasLasVentas() {return repositoryVenta.obtenerTodasLasVentas();
    }
}
