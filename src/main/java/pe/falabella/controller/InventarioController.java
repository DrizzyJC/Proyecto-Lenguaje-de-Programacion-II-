package pe.falabella.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import pe.falabella.dto.InventarioDTO;
import pe.falabella.service.InventarioService;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }
    @GetMapping
    public String listarInventario(@RequestParam(required = false) String filtro,
                                   Model model,
                                   HttpSession session) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        List<InventarioDTO> inventarios = inventarioService.listarInventario(filtro);

        long stockBajo = inventarios.stream()
                .filter(i -> !i.getEstado().equals("Óptimo"))
                .count();

        int totalStock = inventarios.stream()
                .mapToInt(i -> i.getStockActual() == null ? 0 : i.getStockActual())
                .sum();

        long almacenesActivos = inventarios.stream()
                .map(InventarioDTO::getNombreAlmacen)
                .distinct()
                .count();

        model.addAttribute("inventarios", inventarios);
        model.addAttribute("filtro", filtro);
        model.addAttribute("totalRegistros", inventarios.size());
        model.addAttribute("stockBajo", stockBajo);
        model.addAttribute("totalStock", totalStock);
        model.addAttribute("almacenesActivos", almacenesActivos);

        model.addAttribute("nombreUsuario", session.getAttribute("nombreUsuario"));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));

        return "inventario/listar";
    }
}