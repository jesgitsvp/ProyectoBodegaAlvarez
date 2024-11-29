package com.example.proyectoBodega.Controller;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Service.IServiceCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private IServiceCategoria serviceCategoria;

    @GetMapping("/listado")
    public String mostrarFormulario(Model model) {
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "Categoria";
    }

    @PostMapping("/crear")
    public String registrarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        serviceCategoria.Crear(categoria);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/listar")
    public String listarCategorias(Model model) {
        List<Categoria> categorias = serviceCategoria.obtenerTodasLasCategorias();
        model.addAttribute("categorias", categorias);
        return "CategoriaLista"; // Asumiendo que tienes una vista llamada "ListaCategorias"
    }
    @GetMapping("/editar/{idCategoria}")
    public String mostrarFormularioEdicion(@PathVariable("idCategoria") Long idCategoria, Model model) {
        Categoria categoria = serviceCategoria.ListarPorId(idCategoria);
        model.addAttribute("categoria", categoria);
        return "EditarCategoria";
    }

    @PostMapping("/editar/{idCategoria}")
    public String editarCategoria(@PathVariable String idCategoria, @ModelAttribute Categoria categoria) {
        categoria.setIdCategoria(categoria.getIdCategoria());
        serviceCategoria.Modificar(categoria);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String mostrarFormularioEliminacionCategoria(@PathVariable Long idCategoria, Model model) {
        Categoria categoria = serviceCategoria.ListarPorId(idCategoria); // Obtiene el producto por su ID
        model.addAttribute("categoria", categoria);
        return "EliminarCategoria";
    }


    @PostMapping("/eliminar/{idCategoria}")
    public String eliminarCategoria(@PathVariable("idCategoria") Long idCategoria) {
        serviceCategoria.Eliminar(idCategoria);
        return "redirect:/categoria/listar";
    }
}
