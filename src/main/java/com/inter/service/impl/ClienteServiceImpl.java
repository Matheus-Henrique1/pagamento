package com.inter.service.impl;

import com.inter.dto.ClienteDTO;
import com.inter.dto.RetornoPadraoDTO;
import com.inter.entity.Cliente;
import com.inter.entity.ContaCorrente;
import com.inter.repository.ClienteRepository;
import com.inter.service.ClienteService;
import com.inter.service.ContaCorrenteService;
import com.inter.utils.Converte;
import com.inter.utils.Mensagens;
import com.inter.utils.Utils;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Singleton
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContaCorrenteService contaCorrenteService;

    @Transactional
    @Override
    public RetornoPadraoDTO cadastrar(ClienteDTO clienteDTO) {
        clienteRepository.findByCpf(clienteDTO.getCpf()).ifPresent(cliente -> {
            throw new RuntimeException(Mensagens.CLIENTE_JA_EXISTE);
        });
        Cliente cliente = clienteRepository.save(Converte.converteClienteDTOParaCliente(clienteDTO));
        ContaCorrente cc = contaCorrenteService.cadastrar(cliente);
        return Utils.retornoPadrao(Mensagens.SUCESSO_CADASTRAR_CLIENTE, cc.getNumeroConta());
    }

}
