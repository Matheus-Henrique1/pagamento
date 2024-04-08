package com.inter.service.impl;

import com.inter.dto.RetornoPadraoDTO;
import com.inter.dto.TransacaoDTO;
import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;
import com.inter.entity.Transacao;
import com.inter.enums.StatusFaturaEnum;
import com.inter.repository.FaturaRepository;
import com.inter.repository.TransacaoRepository;
import com.inter.service.ContaCorrenteService;
import com.inter.service.FaturaService;
import com.inter.service.TransacaoService;
import com.inter.utils.Converte;
import com.inter.utils.Mensagens;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final FaturaService faturaService;
    private final ContaCorrenteService contaCorrenteService;
    private final FaturaRepository faturaRepository;

    @Transactional
    @Override
    public RetornoPadraoDTO criarTransacao(TransacaoDTO transacaoDTO) {
        ContaCorrente cc = contaCorrenteService.buscarContaCorrente(transacaoDTO.getNumeroContaCorrente());
        List<Fatura> faturasFuturas = faturaService.buscarFaturasFuturas();
        Fatura fatura = faturaService.buscarFaturaAberta(transacaoDTO.getNumeroContaCorrente());
        Integer mesDaFaturaAberta = fatura.getMesDaFatura();
        Integer numeroDeParcelas = transacaoDTO.getNumeroDeParcelas();

        if (numeroDeParcelas == 1) {
            fatura.setMesDaFatura(transacaoDTO.getDataTransacao().getMonthValue());
            transacaoRepository.save(Converte.converteTransacaoDTOTransacao(transacaoDTO, fatura));
            return new RetornoPadraoDTO(Mensagens.TRANSACAO_SUCESSO, fatura.getId());
        } else {
            transacaoDTO.setParcela(1);
            transacaoDTO.setValorDaParcela(transacaoDTO.getValorDaTransacao().divide(BigDecimal.valueOf(numeroDeParcelas), 2, RoundingMode.HALF_UP));
            transacaoRepository.save(Converte.converteTransacaoDTOTransacao(transacaoDTO, fatura));
            if (faturasFuturas.isEmpty()) {
                for (int i = 2; i <= numeroDeParcelas; i++) {
                    Fatura faturaFutura = new Fatura();
                    faturaFutura.setStatus(StatusFaturaEnum.FUTURA.getDescricao());
                    faturaFutura.setContaCorrente(cc);
                    faturaFutura.setMesDaFatura(mesDaFaturaAberta > 12 ? 1 : (i - 1));
                    transacaoDTO.setValorDaParcela(transacaoDTO.getValorDaTransacao().divide(BigDecimal.valueOf(numeroDeParcelas), 2, RoundingMode.HALF_UP));
                    transacaoDTO.setParcela(i);
                    faturaFutura.getTransacoes().add(Converte.converteTransacaoDTOTransacao(transacaoDTO, faturaFutura));
                    faturaRepository.save(faturaFutura);
                }
            }
        }

        return new RetornoPadraoDTO(Mensagens.TRANSACAO_SUCESSO, "teste");
    }

    @Override
    public RetornoPadraoDTO buscaTransacoesPorMesEContaCorrente(Long mes, Long contaCorrente) {
        RetornoPadraoDTO retorno = new RetornoPadraoDTO<>();
        List<Transacao> transacao = transacaoRepository.buscaFaturaPorMesEContaCorrente(mes, contaCorrente);

        retorno.setDados(transacao);
        return retorno;

    }


}
