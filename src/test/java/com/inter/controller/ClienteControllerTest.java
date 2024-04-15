package com.inter.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inter.BaseUnitTest;
import com.inter.LocalDateAdapter;
import com.inter.dto.ClienteDTO;
import com.inter.repository.ClienteRepository;
import com.inter.repository.ContaCorrenteRepository;
import com.inter.repository.FaturaRepository;
import com.inter.repository.TransacaoRepository;
import com.inter.utils.Mensagens;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;

@RequiredArgsConstructor
@MicronautTest
public class ClienteControllerTest extends BaseUnitTest {

    private final String baseUrl = "cliente";

    private final ClienteRepository clienteRepository;
    private final ContaCorrenteRepository contaCorrenteRepository;
    private final FaturaRepository faturaRepository;
    private final TransacaoRepository transacaoRepository;

    @Test
    public void cadastrarClienteTeste() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Cliente de Teste");
        clienteDTO.setCpf("09798817248");
        clienteDTO.setDatNascimento(LocalDate.now());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        Gson gson = gsonBuilder.create();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(clienteDTO))
                .when()
                .post(baseUrl)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("statusCodeValue", equalTo(HttpStatus.CREATED.value()))
                .body("body.mensagem", equalTo(Mensagens.SUCESSO_CADASTRAR_CLIENTE))
                .body("body.dados", equalTo(100000000));

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();

    }

    @Test
    public void cadastrarClienteErroJaExistenteTeste() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Cliente de Teste");
        clienteDTO.setCpf("09798817248");
        clienteDTO.setDatNascimento(LocalDate.now());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        Gson gson = gsonBuilder.create();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(clienteDTO))
                .when()
                .post(baseUrl)
                .then()
                .contentType(ContentType.JSON)
                .body("statusCodeValue", equalTo(HttpStatus.CONFLICT.value()))
                .body("body.mensagem", equalTo(Mensagens.CLIENTE_JA_EXISTE));

        transacaoRepository.deleteAll();
        faturaRepository.deleteAll();
        contaCorrenteRepository.deleteAll();
        clienteRepository.deleteAll();

    }


}

