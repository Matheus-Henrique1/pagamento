package com.inter.controller;

import com.inter.BaseUnitTest;
import com.inter.repository.ClienteRepository;
import com.inter.repository.ContaCorrenteRepository;
import com.inter.repository.FaturaRepository;
import com.inter.repository.TransacaoRepository;
import com.inter.service.ClienteService;
import com.inter.utils.Mensagens;
import io.micronaut.http.MediaType;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
@MicronautTest
public class FaturaControllerTest extends BaseUnitTest {

    String urlBase = "/fatura/fechar-fatura";

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;
    private final ContaCorrenteRepository contaCorrenteRepository;
    private final FaturaRepository faturaRepository;
    private final TransacaoRepository transacaoRepository;


    @Test
    public void fecharFaturaSucesso() {

        clienteService.cadastrar(criarClienteTest());
        Response responseHttp = given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("idFaturaAberta", 1)
                .pathParam("contaCorrente", 100000000)
                .when()
                .patch(urlBase + "/{idFaturaAberta}/{contaCorrente}");

        responseHttp.then().statusCode(HttpStatus.OK.value());
        assertNull(responseHttp.getBody());

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    public void fecharFaturaErroNaoEncontrada() {

        criarClienteTest();
        Response responseHttp = given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParams("idFaturaAberta", 1)
                .pathParams("contaCorrente", 100000000)
                .when()
                .patch(urlBase + "/1/100000000");

        responseHttp.then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException(Mensagens.FATURA_NAO_ENCONTRADA);
        });

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();
    }

}
