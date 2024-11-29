package com.example.proyectoBodega.Controller;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Model.Producto;
import com.example.proyectoBodega.Repository.IRepositoryEmpleado;
import com.example.proyectoBodega.Service.IServiceEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    private IServiceEmpleado serviceEmpleado;

    @GetMapping("/listado")
    public String MostrarListadoEmpleados(Model model) {
        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        return ("Empleado");
    }

    @PostMapping("/crearEmpleado")
    public String RegistrarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
        serviceEmpleado.Crear(empleado);
        return ("redirect:/empleado/listado");
    }
    @GetMapping("/listar")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = serviceEmpleado.obtenerTodosLosEmpleados();
        model.addAttribute("empleados", empleados);
        return "EmpleadoLista"; // Asumiendo que tienes una vista llamada "ListaCategorias"
    }
    @GetMapping("/editar/{idEmpleado}")
    public String mostrarFormularioEdicion(@PathVariable("idEmpleado") Long idEmpleado, Model model) {
        Empleado empleado = serviceEmpleado.ListarPorId(idEmpleado);
        model.addAttribute("empleado", empleado);
        return "EditarEmpleado";
    }

    @PostMapping("/editar/{idEmpleado}")
    public String editarEmpleado(@PathVariable String idEmpleado, @ModelAttribute Empleado empleado) {
        empleado.setIdEmpleado(empleado.getIdEmpleado());
        serviceEmpleado.Modificar(empleado);
        return "redirect:/empleado/listar";
    }

    @GetMapping("/eliminar/{idEmpleado}")
    public String mostrarFormularioEliminacionEmpleado(@PathVariable Long idEmpleado, Model model) {
        Empleado empleado = serviceEmpleado.ListarPorId(idEmpleado); // Obtiene el producto por su ID
        model.addAttribute("empleado", empleado);
        return "EliminarEmpleado";
    }


    @PostMapping("/eliminar/{idEmpleado}")
    public String eliminarEmpleado(@PathVariable("idEmpleado") Long idEmpleado) {
        serviceEmpleado.Eliminar(idEmpleado);
        return "redirect:/empleado/listar";
    }
}