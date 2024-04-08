package com.inter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusFaturaEnum {

    ABERTA(1, "aberta"),
    FECHADA(2, "fechada"),
    FUTURA(3, "futura");

    private final int numero;
    private final String descricao;
}
