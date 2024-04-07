package com.inter.service;

import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;

public interface FaturaService {

    Fatura criarFatura(ContaCorrente contaCorrente);

    Fatura buscarFaturaAberta(Long contaCorrente);

    Fatura criarFaturasFuturas(ContaCorrente contaCorrente, int mes);
}
