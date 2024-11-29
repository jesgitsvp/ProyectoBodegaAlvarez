package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.DetallesVenta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceDetallesVenta {
    int Crear(DetallesVenta detallesVenta);

    List<DetallesVenta> Listar();

    DetallesVenta ListarPorId(long id);

    int Modificar(DetallesVenta detallesVenta);

    int Eliminar(long id);
}
