package com.zupacademy.apass.microservicetransacao.response;

import com.zupacademy.apass.microservicetransacao.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    @NotBlank
    private String id;

    @NotBlank
    private BigDecimal valor;

    @NotBlank
    private String nomeEstabelecimento;

    @NotNull
    private LocalDateTime efetivadaEm;

    /**
     *
     * @param transacao
     */
    public TransacaoResponse(@NotNull Transacao transacao) {
        this.id = transacao.getIdentificador();
        this.valor = transacao.getValor();
        this.nomeEstabelecimento = transacao.getNomeEstabelecimento();
        this.efetivadaEm = transacao.getEfetivadaEm();
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
