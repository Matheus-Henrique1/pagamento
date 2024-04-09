package com.inter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inter.utils.Mensagens;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoDTO implements Serializable {

    private Long id;
    @NotNull(message = Mensagens.DATA_TRANSACAO_OBRIGATORIA)
    private LocalDate dataTransacao;
    @NotBlank(message = Mensagens.NOME_ESTABELECIMENTO_OBRIGATORIO)
    @Size(min = 1, max = 200)
    private String nomeEstabelecimento;
    @NotNull(message = Mensagens.VALOR_TRANSACAO_OBRIGATORIO)
    private BigDecimal valorDaTransacao;
    @NotNull(message = Mensagens.VALOR_PARCELA_OBRIGATORIO)
    private BigDecimal valorDaParcela;
    @NotNull(message = Mensagens.NUMERO_PARCELAS_OBRIGATORIO)
    private Integer numeroDeParcelas;
    @NotNull(message = Mensagens.NUMERO_CONTA_CORRENTE_OBRIGATORIO)
    private Long numeroContaCorrente;
    private Integer parcelaAtual;

}
