package com.inter.service;

import com.inter.entity.Cliente;
import com.inter.entity.ContaCorrente;

public interface ContaCorrenteService {

    ContaCorrente cadastrar(Cliente cliente);

    ContaCorrente buscarContaCorrente(Long numeroContaCorrente);

}
