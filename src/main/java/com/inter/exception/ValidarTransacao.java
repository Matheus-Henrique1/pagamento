package com.inter.exception;

import com.inter.dto.TransacaoDTO;
import com.inter.utils.Mensagens;

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
            throw new NumeroParcelasException("O número de parcelas não pode ser menor que 1.");
        }
        if (transacaoDTO.getNumeroDeParcelas() > 12) {
            throw new NumeroParcelasException("A compra só pode ser parcelada no máximo em até 12 vezes.");
        }
    }

}
