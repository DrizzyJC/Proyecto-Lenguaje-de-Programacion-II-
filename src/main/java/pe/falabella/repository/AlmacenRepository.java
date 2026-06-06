package pe.falabella.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.falabella.model.Almacen;

public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
}