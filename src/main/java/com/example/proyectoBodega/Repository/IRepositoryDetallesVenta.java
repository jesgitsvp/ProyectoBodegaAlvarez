package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.DetallesVenta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryDetallesVenta {
    List<DetallesVenta> Listar();

    DetallesVenta ListarPorId(long id);

    int Crear(DetallesVenta objeto);

    int Modificar(DetallesVenta objeto);

    int Eliminar(long id);
}
