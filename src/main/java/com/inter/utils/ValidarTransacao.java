package com.inter.utils;

import com.inter.dto.TransacaoDTO;
import com.inter.exception.NumeroParcelasException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class ValidarTransacao {

    public ValidarTransacao(TransacaoDTO transacaoDTO) {
    }

    public static void validaTransacao(TransacaoDTO transacaoDTO) {
        if (transacaoDTO.getDataTransacao().isAfter(LocalDate.now())
                || (transacaoDTO.getDataTransacao().getYear() < LocalDate.now().getYear()
                || transacaoDTO.getDataTransacao().getMonthValue() < LocalDate.now().getMonthValue())) {
            throw new DateTimeException(Mensagens.DATA_IVALIDA);
        }
        if (transacaoDTO.getNumeroDeParcelas() < 1) {
            throw new NumeroParcelasException(Mensagens.NUMERO_PARCELAS_MENOR_QUE_UM);
        }
        if (transacaoDTO.getNumeroDeParcelas() > 12) {
            throw new NumeroParcelasException(Mensagens.NUMERO_PARCELAS_EXCEDIDAS);
        }
    }

}
