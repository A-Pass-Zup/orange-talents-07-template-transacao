package com.zupacademy.apass.microservicetransacao.servidor_eventos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.apass.microservicetransacao.model.Estabelecimento;
import com.zupacademy.apass.microservicetransacao.repository.EstabelecimentoRepository;

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
        this.nome = nome.trim();
        this.cidade = cidade.trim();
        this.endereco = endereco.trim();
    }

    public Estabelecimento converte(EstabelecimentoRepository estabelecimentoRepository) {
        final var estabelecimentoExistente = estabelecimentoRepository
                .findByNomeAndCidadeAndEndereco(this.nome, this.cidade, this.endereco);

        if(estabelecimentoExistente.isPresent()) {
            return estabelecimentoExistente.get();
        }

        return new Estabelecimento(this.nome, this.cidade, this.endereco);
    }
}
