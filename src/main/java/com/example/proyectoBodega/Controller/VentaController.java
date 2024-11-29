package com.example.proyectoBodega.Controller;

import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Model.Venta;
import com.example.proyectoBodega.Repository.IRepositoryVenta;
import com.example.proyectoBodega.Service.IServiceVenta;
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
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private IServiceVenta serviceVenta;

    @GetMapping("/listado")
    public String MostrarListadoVentas(Model model) {
        Venta venta = new Venta();
        model.addAttribute("venta", venta);
        return ("Venta");
    }

    @PostMapping("/crearVenta")
    public String RegistrarVenta(@ModelAttribute("venta") Venta venta) {
        serviceVenta.Crear(venta);
        return ("redirect:/venta/listar");
    }
    @GetMapping("/listar")
    public String listarVentas(Model model) {
        List<Venta> ventas = serviceVenta.obtenerTodasLasVentas();
        model.addAttribute("ventas", ventas);
        return "VentaLista"; // Asumiendo que tienes una vista llamada "ListaCategorias"
    }
}
