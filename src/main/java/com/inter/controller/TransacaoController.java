package com.inter.controller;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.dto.TransacaoDTO;
import com.inter.service.TransacaoService;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@Controller("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Post
    public ResponseEntity<RetornoPadraoDTO> criarTransacao(@Body @Valid TransacaoDTO transacaoDTO) {
        return new ResponseEntity<>(transacaoService.criarTransacao(transacaoDTO), HttpStatus.CREATED);

    }

    @Get(value = "/buscar-por-mes-e-conta/{mes}/{contaCorrente}")
    public ResponseEntity<RetornoPadraoDTO> buscaTransacoesPorMesEContaCorrente(@PathVariable Long mes, @PathVariable Long contaCorrente) {

        return new ResponseEntity<>(transacaoService.buscaTransacoesPorMesEContaCorrente(mes, contaCorrente), HttpStatus.OK);
    }

}
