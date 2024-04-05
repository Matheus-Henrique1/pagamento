package com.inter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O campo nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O campo CPF é obrigatorio")
    private String cpf;

    @NotNull(message = "O campo data de nascimento é obrigatorio")
    private Date datNascimento;

    private String contaCorrente;

}
