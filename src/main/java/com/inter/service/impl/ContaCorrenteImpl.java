package com.inter.service.impl;

import com.inter.entity.Cliente;
import com.inter.entity.ContaCorrente;
import com.inter.repository.ContaCorrenteRepository;
import com.inter.service.ContaCorrenteService;
import com.inter.service.FaturaService;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class ContaCorrenteImpl implements ContaCorrenteService {

    private final ContaCorrenteRepository contaCorrenteRepository;
    private final FaturaService faturaService;
    private static final Long numInicial = 100000000L;

    @Transactional
    @Override
    public ContaCorrente cadastrar(Cliente cliente) {
        Optional<Long> ultimaConta = contaCorrenteRepository.buscarUltimaConta();
        ContaCorrente cc = new ContaCorrente();
        cc.setCliente(cliente);
        cc.setNumeroConta(ultimaConta.map(aLong -> aLong + 1).orElse(numInicial));
        cc = contaCorrenteRepository.save(cc);
        faturaService.criarPrimeiraFatura(cc);
        return cc;
    }

    @Transactional
    @Override
    public ContaCorrente buscarContaCorrente(Long numeroContaCorrente) {
        return contaCorrenteRepository.findByNumeroConta(numeroContaCorrente);
    }
}
