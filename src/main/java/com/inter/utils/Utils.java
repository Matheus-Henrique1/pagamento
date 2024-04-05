package com.inter.utils;

import com.inter.dto.RetornoPadraoDTO;

public class Utils {

    public static <T> RetornoPadraoDTO retornoPadrao(String mensagem, T dados) {
        return new RetornoPadraoDTO(mensagem, dados);
    }

    public static RetornoPadraoDTO retornoPadrao(String mensagem) {
        return new RetornoPadraoDTO(mensagem);
    }

}
