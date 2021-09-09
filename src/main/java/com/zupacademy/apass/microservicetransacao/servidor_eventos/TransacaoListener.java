package com.zupacademy.apass.microservicetransacao.servidor_eventos;

import com.zupacademy.apass.microservicetransacao.repository.CartaoRepository;
import com.zupacademy.apass.microservicetransacao.repository.EstabelecimentoRepository;
import com.zupacademy.apass.microservicetransacao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class TransacaoListener {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @KafkaListener(topics = "${spring.kafka.topics.transacao}")
    @Transactional
    public void ourvirEventos(TransacaoReceive transacaoReceive) {
        final var novaTransacao = transacaoReceive.converte(this.cartaoRepository, this.estabelecimentoRepository);
        this.transacaoRepository.save(novaTransacao);
    }
}
