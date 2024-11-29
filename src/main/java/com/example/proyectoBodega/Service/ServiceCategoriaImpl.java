package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Repository.IRepositoryCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ServiceCategoriaImpl implements IServiceCategoria {
    @Autowired
    private IRepositoryCategoria repositoryCategoria;

    @Override
    public int Crear(Categoria categoria) {
        return repositoryCategoria.Crear(categoria);
    }

    @Override
    public List<Categoria> Listar() {
        return repositoryCategoria.Listar();
    }

    @Override
    public Categoria ListarPorId(long id) {
        return repositoryCategoria.ListarPorId(id);
    }

    @Override
    public int Modificar(Categoria categoria) {
        return repositoryCategoria.Modificar(categoria);
    }

    @Override
    public int Eliminar(long id) {
        return repositoryCategoria.Eliminar(id);
    }
    @Override
    public List<Categoria> obtenerTodasLasCategorias()  {return repositoryCategoria.obtenerTodasLasCategorias();
    }

}
