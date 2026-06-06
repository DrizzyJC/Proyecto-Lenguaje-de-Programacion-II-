package pe.falabella.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import pe.falabella.model.Producto;
import pe.falabella.repository.CategoriaRepository;
import pe.falabella.repository.ProductoRepository;
import pe.falabella.repository.ProveedorRepository;

@Controller
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;

    public ProductoController(ProductoRepository productoRepository,
                              CategoriaRepository categoriaRepository,
                              ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @GetMapping("/productos")
    public String listarProductos(Model model, HttpSession session) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("productos", productoRepository.listarProductosConDatos());
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("proveedores", proveedorRepository.findAll());
        model.addAttribute("nombreUsuario", session.getAttribute("nombreUsuario"));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));

        return "productos/listar";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/eliminar")
    public String eliminarProducto(Integer id) {
        try {
            productoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            return "redirect:/productos?error=No se puede eliminar el producto porque está asociado al inventario";
        }

        return "redirect:/productos";
    }
}