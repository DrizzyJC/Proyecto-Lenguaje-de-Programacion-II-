package pe.falabella.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.falabella.dto.ProductoListadoDTO;
import pe.falabella.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("""
           SELECT new pe.falabella.dto.ProductoListadoDTO(
                p.idProducto,
                p.nombre,
                p.descripcion,
                p.idCategoria,
                c.nombreCategoria,
                p.idProveedor,
                pr.razonSocial,
                p.precio,
                p.stockMinimo
           )
           FROM Producto p, Categoria c, Proveedor pr
           WHERE p.idCategoria = c.idCategoria
             AND p.idProveedor = pr.idProveedor
           ORDER BY p.idProducto ASC
           """)
    List<ProductoListadoDTO> listarProductosConDatos();
}