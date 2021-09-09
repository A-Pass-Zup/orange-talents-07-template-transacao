package com.zupacademy.apass.microservicetransacao.servidor_eventos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.apass.microservicetransacao.model.Estabelecimento;

import javax.validation.constraints.NotBlank;

public class EstabelecimentoReceive {

    @NotBlank
    private String nome;

    @NotBlank
    private String cidade;

    @NotBlank
    private String endereco;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EstabelecimentoReceive(@JsonProperty("nome") @NotBlank String nome,
                                  @JsonProperty("cidade") @NotBlank String cidade,
                                  @JsonProperty("endereco") @NotBlank String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public Estabelecimento converte() {
            return new Estabelecimento(this.nome, this.cidade, this.endereco);
    }
}
