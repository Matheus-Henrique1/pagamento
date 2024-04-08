package com.inter.controller;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.service.FaturaService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@Controller("/fatura")
public class FaturaController {

    private final FaturaService faturaService;

    @Put
    public void fecharFatura() {
        faturaService.fecharFatura();
    }

    @Get
    public ResponseEntity<RetornoPadraoDTO> buscarFaturas() {
        return new ResponseEntity<>(faturaService.buscarFaturas(), HttpStatus.OK);
    }

}
