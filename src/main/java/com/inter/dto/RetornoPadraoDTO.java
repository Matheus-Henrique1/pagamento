package com.inter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetornoPadraoDTO<T> implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    private String mensagem;
    private T dados;

    public RetornoPadraoDTO(String mensagem) {
        this.mensagem = mensagem;
    }

}
