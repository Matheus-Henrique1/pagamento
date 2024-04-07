package com.inter.service.impl;

import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;
import com.inter.enums.MesesEnum;
import com.inter.repository.FaturaRepository;
import com.inter.service.FaturaService;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Singleton
@RequiredArgsConstructor
public class FaturaServiceImpl implements FaturaService {

    private final FaturaRepository faturaRepository;

    @Transactional
    @Override
    public Fatura criarFatura(ContaCorrente contaCorrente) {
        Fatura fatura = new Fatura();
        fatura.setAberta(true);
        fatura.setMesDaFatura(LocalDate.now().getMonth().toString());
        fatura.setContaCorrente(contaCorrente);
        fatura = faturaRepository.save(fatura);
        return fatura;
    }

    @Override
    public Fatura buscarFaturaAberta(Long contaCorrente) {
        return faturaRepository.buscarFaturaAbertaPorIdContaCorrente(contaCorrente);
    }

    @Transactional
    @Override
    public Fatura criarFaturasFuturas(ContaCorrente contaCorrente, int mes) {
        Fatura fatura = new Fatura();
        fatura.setContaCorrente(contaCorrente);
        fatura.setMesDaFatura(MesesEnum.buscarMes(mes));
        return faturaRepository.save(fatura);
    }

}
