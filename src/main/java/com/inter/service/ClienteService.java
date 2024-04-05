package com.inter.service;

import com.inter.dto.ClienteDTO;
import com.inter.dto.RetornoPadraoDTO;

public interface ClienteService {

    RetornoPadraoDTO cadastrar(ClienteDTO clienteDTO);
}
