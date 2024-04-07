package com.inter.service.impl;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.dto.TransacaoDTO;
import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;
import com.inter.entity.Transacao;
import com.inter.enums.MesesEnum;
import com.inter.repository.TransacaoRepository;
import com.inter.service.ContaCorrenteService;
import com.inter.service.FaturaService;
import com.inter.service.TransacaoService;
import com.inter.utils.Mensagens;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@Singleton
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final FaturaService faturaService;
    private final ContaCorrenteService contaCorrenteService;

    @Transactional
    @Override
    public RetornoPadraoDTO criarTransacao(TransacaoDTO transacaoDTO) {
        ContaCorrente cc = contaCorrenteService.buscarContaCorrente(transacaoDTO.getNumeroContaCorrente());

        Fatura fatura = faturaService.buscarFaturaAberta(transacaoDTO.getNumeroContaCorrente());
        transacaoRepository.save(converteDTOparaEntidade(transacaoDTO, fatura));

        if (transacaoDTO.getNumeroDeParcelas() > 1) {
            IntStream.range(2, transacaoDTO.getNumeroDeParcelas()).forEach(i -> {
                Transacao transacao = converteDTOparaEntidade(transacaoDTO, fatura);
                transacao.setParcela(i);
                int mes = MesesEnum.valueOf(fatura.getMesDaFatura()).getNumero();
                faturaService.criarFaturasFuturas(cc, mes == 12 ? 1 : ++mes);
                transacaoRepository.save(transacao);
            });
        }

        return new RetornoPadraoDTO(Mensagens.TRANSACAO_SUCESSO, fatura.getId());
    }


    private Transacao converteDTOparaEntidade(TransacaoDTO transacaoDTO, Fatura fatura) {
        Transacao transacao = new Transacao();
        transacao.setDataTransacao(transacaoDTO.getDataTransacao());
        transacao.setValorDaTransacao(transacaoDTO.getValorDaTransacao());
        transacao.setNomeEstabelecimento(transacaoDTO.getNomeEstabelecimento());
        transacao.setNumeroContaCorrente(transacaoDTO.getNumeroContaCorrente());
        transacao.setNumeroDeParcelas(transacaoDTO.getNumeroDeParcelas());
        transacao.setFatura(fatura);
        return transacao;
    }

}
