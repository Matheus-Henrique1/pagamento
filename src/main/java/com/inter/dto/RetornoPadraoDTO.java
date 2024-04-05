package com.inter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetornoPadraoDTO<T> {

    private String mensagem;
    private T dados;

    public RetornoPadraoDTO(String mensagem) {
        this.mensagem = mensagem;
    }

}
