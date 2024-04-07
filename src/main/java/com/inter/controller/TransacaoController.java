package com.inter.controller;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.dto.TransacaoDTO;
import com.inter.service.TransacaoService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Validated
@RequiredArgsConstructor
@Controller("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Post
    public ResponseEntity<RetornoPadraoDTO> criarTransacao(@Body TransacaoDTO transacaoDTO) {
        return new ResponseEntity<>(transacaoService.criarTransacao(transacaoDTO), HttpStatus.CREATED);

    }

}
