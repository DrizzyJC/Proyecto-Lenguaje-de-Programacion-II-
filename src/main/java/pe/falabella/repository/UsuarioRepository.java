package pe.falabella.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.falabella.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
}