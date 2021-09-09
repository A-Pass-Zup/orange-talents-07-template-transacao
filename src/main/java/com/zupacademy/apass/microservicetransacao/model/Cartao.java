package com.zupacademy.apass.microservicetransacao.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String identificador;

    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;

    /**
     * Construtor para JPA. Não utilize.
     */
    @Deprecated
    protected Cartao(){
    }

    /**
     * Construtor com dados obrigatórios.
     *
     * @param identificador
     * @param email
     */
    public Cartao(@NotBlank String identificador, @NotBlank @Email String email) {

        Assert.hasText(identificador, "Não pode criar cartão com identificador nulo ou vazio!");
        Assert.hasText(email, "Não pode criar cartão com e-mail nulo ou vazio!");

        this.identificador = identificador;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return identificador.equals(cartao.identificador) && email.equals(cartao.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, email);
    }
}
