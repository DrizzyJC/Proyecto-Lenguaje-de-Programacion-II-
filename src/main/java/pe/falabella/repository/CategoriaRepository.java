package pe.falabella.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.falabella.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}