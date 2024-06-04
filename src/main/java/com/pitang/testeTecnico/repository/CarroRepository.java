package com.pitang.testeTecnico.repository;

import com.pitang.testeTecnico.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    ArrayList<Carro> findAllByUsuarioId(Long usuarioId);

    Optional<Carro> findByIdAndUsuarioId(Long carroId, Long userId);

    boolean existsByLicensePlate(String licensePlate);
}
