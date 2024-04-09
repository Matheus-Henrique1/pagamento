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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        if (transacaoDTO.getDataTransacao().isAfter(LocalDate.now())
                || (transacaoDTO.getDataTransacao().getYear() < LocalDate.now().getYear()
                || transacaoDTO.getDataTransacao().getMonthValue() < LocalDate.now().getMonthValue())) {
            throw new DateTimeException(Mensagens.DATA_IVALIDA);
        }

        ContaCorrente cc = contaCorrenteService.buscarContaCorrente(transacaoDTO.getNumeroContaCorrente());
        Fatura fatura = faturaService.buscarFaturaAberta(transacaoDTO.getNumeroContaCorrente());
        List<Fatura> faturasFuturas = faturaService.buscarFaturasFuturas();
        Integer mesDaFaturaAberta = fatura.getMesDaFatura();
        Integer numeroDeParcelas = transacaoDTO.getNumeroDeParcelas();

        transacaoDTO.setParcelaAtual(1);
        fatura.setMesDaFatura(transacaoDTO.getDataTransacao().getMonthValue());
        transacaoDTO.setValorDaParcela(transacaoDTO.getValorDaTransacao().divide(BigDecimal.valueOf(numeroDeParcelas), 2, RoundingMode.HALF_UP));
        transacaoRepository.save(Converte.converteTransacaoDTOTransacao(transacaoDTO, fatura));

        if (faturasFuturas.isEmpty() || faturasFuturas.size() < transacaoDTO.getNumeroDeParcelas()) {
            for (int i = 2; i <= numeroDeParcelas; i++) {
                ++mesDaFaturaAberta;
                Fatura faturaFutura = new Fatura();
                faturaFutura.setStatus(StatusFaturaEnum.FUTURA.getDescricao());
                faturaFutura.setContaCorrente(cc);
                faturaFutura.setMesDaFatura(mesDaFaturaAberta > 12 ? 1 : mesDaFaturaAberta);
                transacaoDTO.setValorDaParcela(transacaoDTO.getValorDaTransacao().divide(BigDecimal.valueOf(numeroDeParcelas), 2, RoundingMode.HALF_UP));
                transacaoDTO.setParcelaAtual(i);
                faturaFutura.getTransacoes().add(Converte.converteTransacaoDTOTransacao(transacaoDTO, faturaFutura));
                faturaRepository.save(faturaFutura);
            }
        } else {
            if (transacaoDTO.getNumeroDeParcelas() > 1) {
                Comparator<Fatura> comparador = Comparator.comparing(Fatura::getMesDaFatura);
                Collections.sort(faturasFuturas, comparador);

                for (int i = 0; i < numeroDeParcelas; i++) {
                    Fatura fatura2 = faturasFuturas.get(i);
                    transacaoDTO.setValorDaParcela(transacaoDTO.getValorDaTransacao().divide(BigDecimal.valueOf(numeroDeParcelas), 2, RoundingMode.HALF_UP));
                    transacaoDTO.setParcelaAtual(i == 1 ? (i + 1) : (i + 2));
                    fatura2.getTransacoes().add(Converte.converteTransacaoDTOTransacao(transacaoDTO, fatura2));
                    faturaRepository.save(fatura2);
                }
            }
        }

        return new RetornoPadraoDTO(Mensagens.TRANSACAO_SUCESSO, fatura.getId());
    }

    @Transactional
    @Override
    public RetornoPadraoDTO buscaTransacoesPorMesEContaCorrente(Long mes, Long contaCorrente) {
        RetornoPadraoDTO retorno = new RetornoPadraoDTO<>();
        List<Transacao> transacao = transacaoRepository.buscaFaturaPorMesEContaCorrente(mes, contaCorrente);
        List<TransacaoDTO> listaTransacaoDTO = new ArrayList<>();
        transacao.forEach(t ->
                listaTransacaoDTO.add(Converte.converterTransacaoParaTransacaoDTO(t))
        );
        retorno.setDados(listaTransacaoDTO);
        return retorno;
    }

}
