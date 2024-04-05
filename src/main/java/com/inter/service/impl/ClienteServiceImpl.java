package com.inter.service.impl;

import com.inter.dto.ClienteDTO;
import com.inter.dto.RetornoPadraoDTO;
import com.inter.entity.Cliente;
import com.inter.repository.ClienteRepository;
import com.inter.service.ClienteService;
import com.inter.utils.Mensagens;
import com.inter.utils.Utils;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Singleton
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    @Override
    public RetornoPadraoDTO cadastrar(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setDatNascimento(clienteDTO.getDatNascimento());
//        cliente.setContaCorrente("321561564");

        clienteRepository.save(cliente);
        return Utils.retornoPadrao(Mensagens.SUCESSO_CADASTRAR_CLIENTE, clienteRepository.save(cliente).getContaCorrente());
    }

}
