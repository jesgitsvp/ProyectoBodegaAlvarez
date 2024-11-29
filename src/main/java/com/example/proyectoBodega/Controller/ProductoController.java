package com.example.proyectoBodega.Controller;

import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Model.Producto;
import com.example.proyectoBodega.Model.Categoria; // Importa la clase Categoria
import com.example.proyectoBodega.Model.Proveedor;
import com.example.proyectoBodega.Repository.IRepositoryCategoria;
import com.example.proyectoBodega.Repository.IRepositoryProveedor;
import com.example.proyectoBodega.Service.IServiceProducto;
import com.example.proyectoBodega.Service.IServiceCategoria;
import com.example.proyectoBodega.Service.IServiceProveedor;
import com.example.proyectoBodega.Service.ServiceCategoriaImpl;
import com.sun.jdi.IntegerValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private IServiceProducto serviceProducto;

    @Autowired
    private IServiceCategoria serviceCategoria;

    @Autowired
    private IServiceProveedor serviceProveedor;

    @GetMapping("/listado")
    public String mostrarListadoProductos(Model model) {
        Producto producto = new Producto();
        List<Categoria> categorias = serviceCategoria.obtenerTodasLasCategorias();
        List<Proveedor> proveedores = serviceProveedor.obtenerTodosLosProveedores();
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", producto);
        return "Producto";
    }

    @PostMapping("/crearProducto")
    public String registrarProducto(@ModelAttribute("producto") Producto producto) {
        serviceProducto.Crear(producto);
        return "redirect:/producto/listar";
    }

    @GetMapping("/listar")
    public String mostrarFormularioCreacionProducto(Model model) {
        List<Producto> productos = serviceProducto.obtenerTodosLosProductos();
        List<Categoria> categorias = serviceCategoria.obtenerTodasLasCategorias();
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        return "ProductoLista";
    }

    @GetMapping("/editar/{idProducto}")
    public String mostrarFormularioEdicion(@PathVariable("idProducto") Long idProducto, Model model) {
        Producto producto = serviceProducto.ListarPorId(idProducto);
        model.addAttribute("producto", producto);
        return "EditarProducto";
    }

    @PostMapping("/editar/{idProducto}")
    public String editarProducto(@PathVariable String idProducto, @ModelAttribute Producto producto) {
        producto.setIdProducto(producto.getIdProducto());
        serviceProducto.Modificar(producto);
        return "redirect:/producto/listar";
    }


    @GetMapping("/eliminar/{idProducto}")
    public String mostrarFormularioEliminacionProducto(@PathVariable Long idProducto, Model model) {
        Producto producto = serviceProducto.ListarPorId(idProducto); // Obtiene el producto por su ID
        model.addAttribute("producto", producto);
        return "EliminarProducto";
    }


    @PostMapping("/eliminar/{idProducto}")
    public String eliminarProducto(@PathVariable("idProducto") Long idProducto) {
        serviceProducto.Eliminar(idProducto);
        return "redirect:/producto/listar";
    }


}


