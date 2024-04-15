package com.inter.service;

import com.inter.dto.ClienteDTO;
import com.inter.dto.RetornoPadraoDTO;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@MicronautTest
public class ClienteServiceTest {

    @Inject
    private ClienteService clienteService;

    @Test
    public void cadastrarClienteSucesso() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Cliente de Teste");
        clienteDTO.setCpf("13546813245");
        clienteDTO.setDatNascimento(LocalDate.now());
        RetornoPadraoDTO retorno = clienteService.cadastrar(clienteDTO);
        assertFalse(Objects.isNull(retorno));
        assertEquals(100000000L, retorno.getDados());
        assertEquals("Cliente cadastrado com sucesso! Segue abaixo o número da conta do usuário cadastrado.", retorno.getMensagem());
    }

}
