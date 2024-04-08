package com.inter.enums;

import com.inter.utils.Mensagens;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MesesEnum {

    JANUARY(1, "JANUARY"),
    FEBRUARY(2, "FEBRUARY"),
    MARCH(3, "MARCH"),
    APRIL(4, "APRIL"),
    MAY(5, "MAY"),
    JUNE(6, "JUNE"),
    JULY(7, "JULY"),
    AUGUST(8, "AUGUST"),
    SEPTEMBER(9, "SEPTEMBER"),
    OCTOBER(10, "OCTOBER"),
    NOVEMBER(11, "NOVEMBER"),
    DECEMBER(12, "DECEMBER");

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
