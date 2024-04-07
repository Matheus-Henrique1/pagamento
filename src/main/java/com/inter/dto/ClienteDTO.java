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
import java.util.Date;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO implements Serializable {

    private Long id;

    @NotBlank(message = Mensagens.NOME_OBRIGATORIO)
    @Size(min = 1, max = 200)
    private String nome;
    @NotBlank(message = Mensagens.CPF_OBRIGATORIO)
    @Size(min = 11, max = 11, message = Mensagens.CPF_CARACTERES)
    private String cpf;
    @NotNull(message = Mensagens.DT_NASCIMENTO_OBRIGATORIO)
    private Date datNascimento;
    private String contaCorrente;

}
