package com.zupacademy.apass.microservicetransacao.repository;

import com.zupacademy.apass.microservicetransacao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByIdentificador(String identificador);
}
