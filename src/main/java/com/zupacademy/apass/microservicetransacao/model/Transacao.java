package com.zupacademy.apass.microservicetransacao.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String identificador;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Estabelecimento estabelecimento;

    @NotNull
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @NotNull
    private LocalDateTime efetivadaEm;

    /**
     * Construtor para JPA. Não utilize.
     */
    @Deprecated
    protected Transacao(){
    }

    /**
     * Construtor com dados obrigatórios.
     *
     * @param identificador
     * @param valor
     * @param estabelecimento
     * @param cartao
     * @param efetivadaEm
     */
    public Transacao(@NotBlank String identificador,
                     @NotNull @Positive BigDecimal valor,
                     @NotNull Estabelecimento estabelecimento,
                     @NotNull Cartao cartao,
                     @NotNull LocalDateTime efetivadaEm) {

        Assert.hasText(identificador, "Não pode criar transação com identificador nulo ou vazio!");
        Assert.notNull(valor, "Valor da transação não pode ser nulo!");
        Assert.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "Valor da transação precisa ser > 0.0!");
        Assert.notNull(estabelecimento, "Estabelecimento da transação não pode ser nulo!");
        Assert.notNull(cartao, "Cartão da transação não pode ser nulo!");
        Assert.notNull(efetivadaEm, "Data/Hora da transação efetivada não pode ser nulo!");

        this.identificador = identificador;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return identificador.equals(transacao.identificador) &&
                valor.equals(transacao.valor) &&
                estabelecimento.equals(transacao.estabelecimento) &&
                cartao.equals(transacao.cartao) &&
                efetivadaEm.equals(transacao.efetivadaEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, valor, estabelecimento, cartao, efetivadaEm);
    }
}
