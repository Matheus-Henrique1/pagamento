package com.inter;

import com.inter.dto.ClienteDTO;
import com.inter.dto.TransacaoDTO;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@MicronautTest
public abstract class BaseUnitTest {

    public ClienteDTO criarClienteTest() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Cliente Test");
        clienteDTO.setCpf("91878945687");
        clienteDTO.setDatNascimento(LocalDate.now());
        return clienteDTO;
    }

    public TransacaoDTO criarTransacao() {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setNomeEstabelecimento("Estabelecimento de Teste");
        transacaoDTO.setDataTransacao(LocalDate.now());
        transacaoDTO.setValorDaTransacao(BigDecimal.valueOf(150));
        transacaoDTO.setValorDaParcela(BigDecimal.valueOf(150));
        transacaoDTO.setNumeroDeParcelas(1);
        transacaoDTO.setNumeroContaCorrente(100000000L);
        return transacaoDTO;
    }

}
