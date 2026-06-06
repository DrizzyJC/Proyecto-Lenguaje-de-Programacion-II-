package pe.falabella.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import pe.falabella.dto.MovimientoRecienteDTO;
import pe.falabella.repository.AlmacenRepository;
import pe.falabella.repository.InventarioRepository;
import pe.falabella.repository.MovimientoRepository;

@Controller
public class DashboardController {

    private final InventarioRepository inventarioRepository;
    private final AlmacenRepository almacenRepository;
    private final MovimientoRepository movimientoRepository;

    public DashboardController(InventarioRepository inventarioRepository,
                               AlmacenRepository almacenRepository,
                               MovimientoRepository movimientoRepository) {
        this.inventarioRepository = inventarioRepository;
        this.almacenRepository = almacenRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model, HttpSession session) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioDia = hoy.atStartOfDay();
        LocalDateTime finDia = hoy.plusDays(1).atStartOfDay();

        Long inventarioTotal = inventarioRepository.obtenerInventarioTotal();
        Long almacenesActivos = almacenRepository.count();
        Long alertasStock = inventarioRepository.contarAlertasStock();
        Long movimientosHoy = movimientoRepository.countByFechaBetween(inicioDia, finDia);

        List<MovimientoRecienteDTO> movimientosRecientes =
                movimientoRepository.listarMovimientosRecientes(PageRequest.of(0, 5));

        model.addAttribute("nombreUsuario", session.getAttribute("nombreUsuario"));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));

        model.addAttribute("inventarioTotal", inventarioTotal);
        model.addAttribute("almacenesActivos", almacenesActivos);
        model.addAttribute("alertasStock", alertasStock);
        model.addAttribute("movimientosHoy", movimientosHoy);
        model.addAttribute("movimientosRecientes", movimientosRecientes);

        return "dashboard";
    }
}