package com.pitang.testeTecnico.repository;

import com.pitang.testeTecnico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByLogin(String login);

    Usuario findByLogin(String login);

    Usuario findByEmail(String email);

}
