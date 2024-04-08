package com.inter.service;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;

import java.util.List;

public interface FaturaService {

    Fatura criarPrimeiraFatura(ContaCorrente contaCorrente);

    Fatura buscarFaturaAberta(Long contaCorrente);

    Fatura criarFaturasFuturas(ContaCorrente contaCorrente, int mes);

    void fecharFatura();

    RetornoPadraoDTO buscarFaturas();

    List<Fatura> buscarFaturasFuturas();

}
