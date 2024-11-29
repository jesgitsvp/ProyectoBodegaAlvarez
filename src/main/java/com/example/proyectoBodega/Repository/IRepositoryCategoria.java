package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Producto;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface IRepositoryCategoria {
    List<Categoria> Listar();

    Categoria ListarPorId(long id);

    int Crear(Categoria objeto);

    int Modificar(Categoria categoria);

    int Eliminar(long id);
    List<Categoria> obtenerTodasLasCategorias();




}
