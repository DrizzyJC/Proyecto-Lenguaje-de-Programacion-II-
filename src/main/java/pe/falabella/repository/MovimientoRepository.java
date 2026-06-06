package pe.falabella.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.falabella.dto.MovimientoRecienteDTO;
import pe.falabella.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    long countByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query("""
           SELECT new pe.falabella.dto.MovimientoRecienteDTO(
                p.nombre,
                m.tipoMovimiento,
                m.cantidad,
                m.fecha
           )
           FROM Movimiento m, Producto p
           WHERE m.idProducto = p.idProducto
           ORDER BY m.fecha DESC
           """)
    List<MovimientoRecienteDTO> listarMovimientosRecientes(Pageable pageable);
}