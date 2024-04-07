package com.inter.enums;

import com.inter.utils.Mensagens;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MesesEnum {

    JANEIRO(1, "January"),
    FEVEREIRO(2, "February"),
    MARCO(3, "March"),
    ABRIL(4, "April"),
    MAIO(5, "May"),
    JUNHO(6, "June"),
    JULHO(7, "July"),
    AGOSTO(8, "August"),
    SETEMBRO(9, "September"),
    OUTUBRO(10, "October"),
    NOVEMBRO(11, "November"),
    DEZEMBRO(12, "December");

    private final int numero;
    private final String descricao;

    public static String buscarMes(Integer codigo) {
        for (MesesEnum mes : MesesEnum.values()) {
            if (mes.getNumero() == codigo) {
                return mes.getDescricao();
            }
        }
        throw new IllegalArgumentException(Mensagens.CODIGO_MES_INVALIDO + codigo);
    }

}
