package pe.falabella.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.falabella.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
}