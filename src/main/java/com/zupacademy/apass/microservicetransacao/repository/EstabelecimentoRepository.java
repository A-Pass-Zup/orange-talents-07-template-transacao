package com.zupacademy.apass.microservicetransacao.repository;

import com.zupacademy.apass.microservicetransacao.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstabelecimentoRepository  extends JpaRepository<Estabelecimento, Long> {
    Optional<Estabelecimento> findByNomeAndCidadeAndEndereco(String nome, String cidade, String endereco);
}
