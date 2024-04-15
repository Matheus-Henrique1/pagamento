package com.inter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inter.BaseUnitTest;
import com.inter.dto.RetornoPadraoDTO;
import com.inter.repository.ClienteRepository;
import com.inter.repository.ContaCorrenteRepository;
import com.inter.repository.FaturaRepository;
import com.inter.repository.TransacaoRepository;
import com.inter.service.ClienteService;
import com.inter.service.TransacaoService;
import com.inter.utils.Mensagens;
import io.micronaut.http.MediaType;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@MicronautTest
public class TransacaoControllerTest extends BaseUnitTest {

    String urlBase = "/transacao";
    String sufix = "/buscar-por-mes-e-conta";

    private final ClienteRepository clienteRepository;
    private final ContaCorrenteRepository contaCorrenteRepository;
    private final FaturaRepository faturaRepository;
    private final TransacaoRepository transacaoRepository;
    private final ClienteService clienteService;
    private final TransacaoService transacaoService;


    @Test
    public void criarTransacaoSucessoTeste() {

        clienteService.cadastrar(criarClienteTest());
        String objeto = "{\"nomeEstabelecimento\":\"EstabelecimentodeTeste\",\"dataTransacao\":\"2024-04-09\"," +
                "\"valorDaTransacao\":150,\"valorDaParcela\":0,\"numeroDeParcelas\":1,\"numeroContaCorrente\":100000000}";

        Response responseHttp = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(objeto)
                .when()
                .post(urlBase);

        responseHttp.then().statusCode(HttpStatus.OK.value());
        RetornoPadraoDTO retornoPadraoDTO = responseHttp.getBody().as(RetornoPadraoDTO.class);
        assertNotNull(retornoPadraoDTO.getMensagem());
        assertNotNull(retornoPadraoDTO.getDados());
        assertEquals("Transação processada com sucesso. Abaixo id da Fatura!", retornoPadraoDTO.getMensagem());

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();

    }

    @Test
    public void criarTransacaoErroData() throws JsonProcessingException {

        clienteService.cadastrar(criarClienteTest());
        String objeto = "{\"nomeEstabelecimento\":\"EstabelecimentodeTeste\",\"dataTransacao\":\"2035-04-09\"," +
                "\"valorDaTransacao\":150,\"valorDaParcela\":0,\"numeroDeParcelas\":1,\"numeroContaCorrente\":100000000}";

        Response responseHttp = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(objeto)
                .when()
                .post(urlBase);

        responseHttp.then().statusCode(HttpStatus.BAD_REQUEST.value());
        assertNotNull(responseHttp.getBody());
        RetornoPadraoDTO retornoPadraoDTO = responseHttp.getBody().as(RetornoPadraoDTO.class);
        assertNotNull(retornoPadraoDTO.getMensagem());
        assertEquals(Mensagens.DATA_IVALIDA, retornoPadraoDTO.getMensagem());

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    public void criarTransacaoErroNumeroParcelasExcedidas() throws JsonProcessingException {

        clienteService.cadastrar(criarClienteTest());
        String objeto = "{\"nomeEstabelecimento\":\"EstabelecimentodeTeste\",\"dataTransacao\":\"2035-04-09\"," +
                "\"valorDaTransacao\":150,\"valorDaParcela\":0,\"numeroDeParcelas\":13,\"numeroContaCorrente\":100000000}";

        Response responseHttp = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(objeto)
                .when()
                .post(urlBase);

        responseHttp.then().statusCode(HttpStatus.BAD_REQUEST.value());
        assertNotNull(responseHttp.getBody());
        RetornoPadraoDTO retornoPadraoDTO = responseHttp.getBody().as(RetornoPadraoDTO.class);
        assertNotNull(retornoPadraoDTO.getMensagem());
        assertEquals(Mensagens.NUMERO_PARCELAS_EXCEDIDAS, retornoPadraoDTO.getMensagem());

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    public void criarTransacaoErroNumeroParcelasMenorQueUm() throws JsonProcessingException {

        clienteService.cadastrar(criarClienteTest());
        String objeto = "{\"nomeEstabelecimento\":\"EstabelecimentodeTeste\",\"dataTransacao\":\"2035-04-09\"," +
                "\"valorDaTransacao\":150,\"valorDaParcela\":0,\"numeroDeParcelas\":0,\"numeroContaCorrente\":100000000}";

        Response responseHttp = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(objeto)
                .when()
                .post(urlBase);

        responseHttp.then().statusCode(HttpStatus.BAD_REQUEST.value());
        assertNotNull(responseHttp.getBody());
        RetornoPadraoDTO retornoPadraoDTO = responseHttp.getBody().as(RetornoPadraoDTO.class);
        assertNotNull(retornoPadraoDTO.getMensagem());
        assertEquals(Mensagens.NUMERO_PARCELAS_MENOR_QUE_UM, retornoPadraoDTO.getMensagem());

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    public void buscarTransacoesPorIdFaturaEContaCorrente() {

        clienteService.cadastrar(criarClienteTest());
        transacaoService.criarTransacao(criarTransacao());

        Response responseHttp = given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParams("mes", String.valueOf(LocalDate.now().getMonthValue()))
                .pathParams("contaCorrente", "100000000")
                .when()
                .patch(urlBase + sufix);

        responseHttp.then().statusCode(HttpStatus.OK.value());
        assertNull(responseHttp.getBody());

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();

    }
}
