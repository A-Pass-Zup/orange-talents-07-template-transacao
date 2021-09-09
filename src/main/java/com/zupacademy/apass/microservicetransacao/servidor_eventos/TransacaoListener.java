package com.zupacademy.apass.microservicetransacao.servidor_eventos;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class TransacaoListener {

    @PersistenceContext
    private EntityManager entityManager;

    @KafkaListener(topics = "${spring.kafka.topics.transacao}")
    @Transactional
    public void ourvirEventos(TransacaoReceive transacaoReceive) {
        final var novaTransacao = transacaoReceive.converte(this.entityManager);
        this.entityManager.persist(novaTransacao);
    }
}
