package com.inter.service;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;

import java.util.List;

public interface FaturaService {

    Fatura criarPrimeiraFatura(ContaCorrente contaCorrente);

    Fatura buscarFaturaAberta(Long contaCorrente);

    void fecharFatura(Long idFaturaAberta, Long contaCorrente);

    RetornoPadraoDTO buscarFaturas();

    List<Fatura> buscarFaturasFuturas();

}
