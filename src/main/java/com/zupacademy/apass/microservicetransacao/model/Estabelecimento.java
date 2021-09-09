package com.zupacademy.apass.microservicetransacao.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String cidade;

    @NotBlank
    @Column(nullable = false)
    private String endereco;

    /**
     * Construtor para JPA. Não utilize.
     */
    @Deprecated
    protected Estabelecimento(){}

    /**
     * Construtor com os dados obrigatórios.
     *
     * @param nome
     * @param cidade
     * @param endereco
     */
    public Estabelecimento(@NotBlank String nome,
                           @NotBlank String cidade,
                           @NotBlank String endereco) {

        Assert.hasText(nome, "Não pode criar Estabelecimento com nome nulo ou em branco!");
        Assert.hasText(cidade, "Não pode criar estabelecimento com cidade nula ou em branco!");
        Assert.hasText(endereco, "Não pode criar Estabelecimento com endereço nulo ou em branco!");

        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estabelecimento that = (Estabelecimento) o;
        return nome.equals(that.nome) && cidade.equals(that.cidade) && endereco.equals(that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cidade, endereco);
    }
}
