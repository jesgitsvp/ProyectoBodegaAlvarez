package com.example.proyectoBodega.Controller;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Cliente;
import com.example.proyectoBodega.Model.Producto;
import com.example.proyectoBodega.Repository.IRepositoryCliente;
import com.example.proyectoBodega.Service.IServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private IServiceCliente serviceCliente;

    @GetMapping("/listado")
    public String MostrarListadoClientes(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        // Puedes modificar el nombre del template según sea necesario
        return ("Cliente");
    }

    @PostMapping("/crear")
    public String RegistrarCliente(@ModelAttribute("cliente") Cliente cliente) {
        // Suponiendo que tienes un servicio llamado serviceCliente para manejar las operaciones con clientes
        serviceCliente.Crear(cliente);
        return ("redirect:/cliente/listar");
    }
    @GetMapping("/listar")
    public String listarClientes(Model model) {
        List<Cliente> clientes = serviceCliente.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "ClienteLista"; // Asumiendo que tienes una vista llamada "ListaCategorias"
    }
    @GetMapping("/editar/{idCliente}")
    public String mostrarFormularioEdicion(@PathVariable("idCliente") Long idCliente, Model model) {
        Cliente cliente = serviceCliente.ListarPorId(idCliente);
        model.addAttribute("cliente", cliente);
        return "EditarCliente";
    }

    @PostMapping("/editar/{idCliente}")
    public String editarCliente(@PathVariable String idCliente, @ModelAttribute Cliente cliente) {
        cliente.setIdCliente(cliente.getIdCliente());
        serviceCliente.Modificar(cliente);
        return "redirect:/cliente/listar";
    }


    @GetMapping("/eliminar/{idCliente}")
    public String mostrarFormularioEliminacionCliente(@PathVariable Long idCliente, Model model) {
        Cliente cliente = serviceCliente.ListarPorId(idCliente); // Obtiene el producto por su ID
        model.addAttribute("cliente", cliente);
        return "EliminarCliente";
    }


    @PostMapping("/eliminar/{idCliente}")
    public String eliminarCliente(@PathVariable("idCliente") Long idCliente) {
        serviceCliente.Eliminar(idCliente);
        return "redirect:/cliente/listar";
    }

}
