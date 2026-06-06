package pe.falabella.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.falabella.dto.InventarioDTO;
import pe.falabella.model.Inventario;
import pe.falabella.repository.InventarioRepository;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<InventarioDTO> listarInventario(String filtro) {
        List<Inventario> inventarios;

        if (filtro == null || filtro.trim().isEmpty()) {
            inventarios = inventarioRepository.listarInventarioCompleto();
        } else {
            inventarios = inventarioRepository.buscarInventario(filtro.trim());
        }

        return inventarios.stream()
                .map(this::convertirDTO)
                .toList();
    }

    private InventarioDTO convertirDTO(Inventario inventario) {
        Integer stockActual = inventario.getStockActual();
        Integer stockMinimo = inventario.getProducto().getStockMinimo();

        String estado = calcularEstado(stockActual, stockMinimo);

        return new InventarioDTO(
                inventario.getIdInventario(),
                inventario.getProducto().getNombre(),
                inventario.getAlmacen().getNombre(),
                inventario.getAlmacen().getUbicacion(),
                stockActual,
                stockMinimo,
                estado,
                inventario.getFechaActualizacion()
        );
    }

    private String calcularEstado(Integer stockActual, Integer stockMinimo) {
        if (stockActual == null || stockMinimo == null) {
            return "Sin datos";
        }

        if (stockActual < stockMinimo) {
            return "Stock Crítico";
        }

        if (stockActual <= stockMinimo * 1.5) {
            return "Por Agotarse";
        }

        return "Óptimo";
    }
}