package com.inter.controller;

import com.inter.dto.ClienteDTO;
import com.inter.dto.RetornoPadraoDTO;
import com.inter.service.ClienteService;
import com.inter.service.FaturaService;
import com.inter.utils.Utils;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@Controller("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final FaturaService faturaService;


    @Post
    public ResponseEntity<RetornoPadraoDTO> cadastrar(@Body @Valid ClienteDTO clienteDTO) {
        try {
            return new ResponseEntity<>(clienteService.cadastrar(clienteDTO), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Utils.retornoPadrao(e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(Utils.retornoPadrao(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

