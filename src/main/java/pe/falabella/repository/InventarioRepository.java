package pe.falabella.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.falabella.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    @Query("""
           SELECT i FROM Inventario i
           JOIN FETCH i.producto p
           JOIN FETCH i.almacen a
           ORDER BY p.nombre ASC
           """)
    List<Inventario> listarInventarioCompleto();

    @Query("""
           SELECT i FROM Inventario i
           JOIN FETCH i.producto p
           JOIN FETCH i.almacen a
           WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
              OR LOWER(a.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
              OR LOWER(a.ubicacion) LIKE LOWER(CONCAT('%', :filtro, '%'))
           ORDER BY p.nombre ASC
           """)
    List<Inventario> buscarInventario(String filtro);
    
    @Query("""
    	       SELECT COALESCE(SUM(i.stockActual), 0)
    	       FROM Inventario i
    	       """)
    	Long obtenerInventarioTotal();

    	@Query("""
    	       SELECT COUNT(i)
    	       FROM Inventario i
    	       WHERE i.stockActual <= i.producto.stockMinimo
    	       """)
    	Long contarAlertasStock();
}