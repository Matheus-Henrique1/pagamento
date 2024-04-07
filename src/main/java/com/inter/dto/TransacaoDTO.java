package com.inter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoDTO implements Serializable {

    private Long id;
    private Date dataTransacao;
    private String nomeEstabelecimento;
    private BigDecimal valorDaTransacao;
    private BigDecimal valorDaParcela;
    private Integer numeroDeParcelas;
    private Long numeroContaCorrente;
}
