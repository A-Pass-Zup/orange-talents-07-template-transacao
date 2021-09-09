package com.zupacademy.apass.microservicetransacao.repository;

import com.zupacademy.apass.microservicetransacao.model.Cartao;
import com.zupacademy.apass.microservicetransacao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findFirst10ByCartaoOrderByEfetivadaEmDesc(Cartao cartao);
}
