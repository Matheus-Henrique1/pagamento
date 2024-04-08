package com.inter.controller;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.service.FaturaService;
import com.inter.utils.Utils;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@Controller("/fatura")
public class FaturaController {

    private final FaturaService faturaService;

    @Patch(value = "/fechar-fatura/{idFaturaAberta}/{contaCorrente}")
    public void fecharFatura(@PathVariable Long idFaturaAberta, @PathVariable Long contaCorrente) {
        faturaService.fecharFatura(idFaturaAberta, contaCorrente);
    }

    @Get
    public ResponseEntity<RetornoPadraoDTO> buscarFaturas() {
        try {
            return new ResponseEntity<>(faturaService.buscarFaturas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Utils.retornoPadrao(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
