package com.inter.service;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.dto.TransacaoDTO;

public interface TransacaoService {

    RetornoPadraoDTO criarTransacao(TransacaoDTO transacaoDTO);
}
