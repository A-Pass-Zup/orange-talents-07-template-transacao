package com.zupacademy.apass.microservicetransacao.servidor_eventos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.apass.microservicetransacao.model.Cartao;
import com.zupacademy.apass.microservicetransacao.model.Transacao;
import com.zupacademy.apass.microservicetransacao.repository.CartaoRepository;
import com.zupacademy.apass.microservicetransacao.repository.EstabelecimentoRepository;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoReceive {

    @NotBlank
    private String id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    private EstabelecimentoReceive estabelecimento;

    @NotNull
    private CartaoReceive cartao;

    @NotNull
    private LocalDateTime efetivadaEm;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TransacaoReceive(@JsonProperty("id") @NotBlank String id,
                            @JsonProperty("valor") @NotNull @Positive BigDecimal valor,
                            @JsonProperty("estabelecimento") @NotNull EstabelecimentoReceive estabelecimento,
                            @JsonProperty("cartao") @NotNull CartaoReceive cartao,
                            @JsonProperty("efetivadaEm") @NotNull LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public Transacao converte(CartaoRepository cartaoRepository, EstabelecimentoRepository estabelecimentoRepository) {

        var cartao = this.cartao.converte(cartaoRepository);

        var estabelecimento = this.estabelecimento.converte(estabelecimentoRepository);

        return new Transacao(this.id,
                this.valor,
                estabelecimento,
                cartao,
                this.efetivadaEm);

    }
}
