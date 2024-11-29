package com.example.proyectoBodega.Controller;

import com.example.proyectoBodega.Model.*;
import com.example.proyectoBodega.Repository.IRepositoryCategoria;
import com.example.proyectoBodega.Repository.IRepositoryProveedor;
import com.example.proyectoBodega.Repository.IRepositoryCompra;
import com.example.proyectoBodega.Repository.RepositoryCompraImpl;
import com.example.proyectoBodega.Service.IServiceCategoria;
import com.example.proyectoBodega.Service.IServiceCompra;
import com.example.proyectoBodega.Service.IServiceProducto;
import com.example.proyectoBodega.Service.IServiceProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private IServiceCompra serviceCompra;
    @Autowired
    private IServiceProveedor serviceProveedor;
    @Autowired
    private IServiceProducto serviceProducto;

    @Autowired
    private IServiceCategoria serviceCategoria;

    @GetMapping("/listado")
    public String MostrarListadoCompras(Model model) {
        Compra compra = new Compra();

        List<Producto> productos = serviceProducto.obtenerTodosLosProductos();
        //List<Proveedor> proveedors = serviceProveedor.obtenerTodosLosProveedores();
        model.addAttribute("productos", productos);
        //model.addAttribute("proveedores", proveedors);
        model.addAttribute("compra", compra);

        return ("Compra");
    }


    @PostMapping("/crearCompra")
    public String RegistrarCompra(@ModelAttribute("compra") Compra compra) {
        serviceCompra.Crear(compra);
        return ("redirect:/compra/listar");
    }
    @GetMapping("/listar")
    public String listarCompras(Model model) {
        List<Compra> compras = serviceCompra.obtenerTodasLasCompras();
        model.addAttribute("compras", compras);
        return "Compra"; // Asumiendo que tienes una vista llamada "ListaCategorias"
    }

}
