package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Producto;

import com.example.proyectoBodega.Repository.IRepositoryCategoria;
import com.example.proyectoBodega.Repository.IRepositoryProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceProductoImpl implements IServiceProducto {
    @Autowired
    private  IRepositoryProducto repositoryProducto;
    @Autowired
    private  IRepositoryCategoria repositoryCategoria;



    // Constructor para inyectar los repositorios
    @Autowired
    public ServiceProductoImpl(IRepositoryProducto repositoryProducto, IRepositoryCategoria repositoryCategoria) {
        this.repositoryProducto = repositoryProducto;
        this.repositoryCategoria = repositoryCategoria;

    }

    @Override
    public int Crear(Producto producto) {return repositoryProducto.Crear(producto);
    }

    @Override
    public List<Producto> Listar() {return repositoryProducto.Listar();
    }

    @Override
    public Producto ListarPorId(long id) {return repositoryProducto.ListarPorId(id);
    }


    @Override
    public int Modificar(Producto producto) { return repositoryProducto.Modificar(producto);
    }


    @Override
    public int Eliminar(long id) {return repositoryProducto.Eliminar(id);
    }
    @Override
    public List<Producto> obtenerTodosLosProductos() {return repositoryProducto.obtenerTodosLosProductos();
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {return repositoryCategoria.obtenerTodasLasCategorias();
    }


}
