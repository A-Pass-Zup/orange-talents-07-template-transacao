package com.zupacademy.apass.microservicetransacao.servidor_eventos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.apass.microservicetransacao.model.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CartaoReceive {

    @NotBlank
    private String id;

    @NotBlank
    @Email
    private String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CartaoReceive(@JsonProperty("id") @NotBlank String id,
                         @JsonProperty("email") @NotBlank @Email String email) {
        this.id = id;
        this.email = email;
    }

    public Cartao converte() {
        return new Cartao(this.id, this.email);
    }
}
