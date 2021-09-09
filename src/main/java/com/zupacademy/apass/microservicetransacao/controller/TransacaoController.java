package com.zupacademy.apass.microservicetransacao.controller;

import com.zupacademy.apass.microservicetransacao.repository.CartaoRepository;
import com.zupacademy.apass.microservicetransacao.repository.TransacaoRepository;
import com.zupacademy.apass.microservicetransacao.response.TransacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @GetMapping("/{identificadorCartao}/ultimas")
    public ResponseEntity<?> ultimasTransacoes(@PathVariable @NotBlank String identificadorCartao,
                                               @AuthenticationPrincipal Jwt principal) {

        final var possivelCartao = this.cartaoRepository.findByIdentificador(identificadorCartao);

        if(possivelCartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final var cartao = possivelCartao.get();

        if(!cartao.getEmail().equals(principal.getClaimAsString("email"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok()
                .body(this.transacaoRepository
                        .findFirst10ByCartaoOrderByEfetivadaEmDesc(cartao)
                        .stream().map(TransacaoResponse::new));

    }
}
