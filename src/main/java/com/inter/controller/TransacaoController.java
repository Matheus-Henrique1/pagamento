package com.inter.controller;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.dto.TransacaoDTO;
import com.inter.exception.NumeroParcelasException;
import com.inter.service.TransacaoService;
import com.inter.utils.Utils;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.time.DateTimeException;

@Validated
@RequiredArgsConstructor
@Controller("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Post
    public ResponseEntity<RetornoPadraoDTO> criarTransacao(@Body @Valid TransacaoDTO transacaoDTO) {
        try {
            return new ResponseEntity<>(transacaoService.criarTransacao(transacaoDTO), HttpStatus.CREATED);
        } catch (DateTimeException | NumeroParcelasException e) {
            return new ResponseEntity<>(Utils.retornoPadrao(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(Utils.retornoPadrao(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Get(value = "/buscar-por-mes-e-conta/{mes}/{contaCorrente}")
    public ResponseEntity<RetornoPadraoDTO> buscaTransacoesPorMesEContaCorrente(@PathVariable Long mes, @PathVariable Long contaCorrente) {
        try {
            return new ResponseEntity<>(transacaoService.buscaTransacoesPorMesEContaCorrente(mes, contaCorrente), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Utils.retornoPadrao(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
