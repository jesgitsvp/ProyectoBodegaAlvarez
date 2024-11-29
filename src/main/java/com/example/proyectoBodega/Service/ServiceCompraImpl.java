package com.example.proyectoBodega.Service;

import com.example.proyectoBodega.Model.*;
import com.example.proyectoBodega.Repository.IRepositoryCategoria;
import com.example.proyectoBodega.Repository.IRepositoryCompra;
import com.example.proyectoBodega.Repository.IRepositoryProducto;
import com.example.proyectoBodega.Repository.IRepositoryProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCompraImpl implements IServiceCompra {
    @Autowired
    private IRepositoryCompra repositoryCompra;
    @Autowired
    private IRepositoryProducto repositoryProducto;
    @Autowired
    private IRepositoryCategoria repositoryCategoria;

    @Autowired
    private IRepositoryProveedor repositoryProveedor;// Se agrega 'final' aquí

    // Constructor para inyectar los repositorios
    @Autowired
    public ServiceCompraImpl(IRepositoryCompra repositoryCompra, IRepositoryProducto repositoryProducto, IRepositoryCategoria repositoryCategoria, IRepositoryProveedor  repositoryProveedor) {
        this.repositoryCompra = repositoryCompra;
        this.repositoryProducto = repositoryProducto;
        this.repositoryCategoria = repositoryCategoria;
        this.repositoryProveedor = repositoryProveedor;
    }

    @Override
    public int Crear(Compra compra) {
        return repositoryCompra.Crear(compra);
    }

    @Override
    public List<Compra> Listar() {
        return repositoryCompra.Listar();
    }

    @Override
    public Compra ListarPorId(long id) {
        return repositoryCompra.ListarPorId(id);
    }

    @Override
    public int Modificar(Compra compra) {
        return repositoryCompra.Modificar(compra);
    }

    @Override
    public int Eliminar(long id) {
        return repositoryCompra.Eliminar(id);
    }

    @Override
    public List<Compra> obtenerTodasLasCompras()  {return repositoryCompra.obtenerTodasLasCompras();
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {return repositoryCategoria.obtenerTodasLasCategorias();
    }

    @Override
    public List<Proveedor> obtenerTodosLosProveedores() {return repositoryProveedor.obtenerTodosLosProveedores();
    }
    @Override
    public List<Producto> obtenerTodosLosProductos() {return repositoryProducto.obtenerTodosLosProductos();
    }

}
