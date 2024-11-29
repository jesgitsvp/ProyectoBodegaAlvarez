package com.example.proyectoBodega.Controller;

import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Model.Producto;
import com.example.proyectoBodega.Model.Proveedor;
import com.example.proyectoBodega.Repository.IRepositoryProveedor;
import com.example.proyectoBodega.Service.IServiceProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    private IServiceProveedor serviceProveedor;

    @GetMapping("/listado")
    public String MostrarListadoProveedores(Model model) {
        Proveedor proveedor = new Proveedor();
        model.addAttribute("proveedor", proveedor);
        return ("Proveedor");
    }

    @PostMapping("/crearProveedor")
    public String RegistrarProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
        serviceProveedor.Crear(proveedor);
        return ("redirect:/proveedor/listado");
    }
    @GetMapping("/listar")
    public String listarProveedores(Model model) {
        List<Proveedor> proveedores = serviceProveedor.obtenerTodosLosProveedores();
        model.addAttribute("proveedores", proveedores);
        return "ProveedorLista"; // Asumiendo que tienes una vista llamada "ListaCategorias"
    }
    @GetMapping("/editar/{idProveedor}")
    public String mostrarFormularioEdicion(@PathVariable("idProveedor") Long idProveedor, Model model) {
        Proveedor proveedor = serviceProveedor.ListarPorId(idProveedor);
        model.addAttribute("proveedor", proveedor);
        return "EditarProveedor";
    }

    @PostMapping("/editar/{idProveedor}")
    public String editarProveedor(@PathVariable String idProveedor, @ModelAttribute Proveedor proveedor) {
        proveedor.setIdProveedor(proveedor.getIdProveedor());
        serviceProveedor.Modificar(proveedor);
        return "redirect:/proveedor/listar";
    }


    @GetMapping("/eliminar/{idProveedor}")
    public String mostrarFormularioEliminacionProveedor(@PathVariable Long idProveedor, Model model) {
        Proveedor proveedor = serviceProveedor.ListarPorId(idProveedor); // Obtiene el producto por su ID
        model.addAttribute("proveedor", proveedor);
        return "EliminarProveedor";
    }


    @PostMapping("/eliminar/{idProveedor}")
    public String eliminarProveedor(@PathVariable("idProveedor") Long idProveedor) {
        serviceProveedor.Eliminar(idProveedor);
        return "redirect:/proveedor/listar";
    }
}

