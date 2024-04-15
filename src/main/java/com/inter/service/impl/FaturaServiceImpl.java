package com.inter.service.impl;

import com.inter.dto.FaturaDTO;
import com.inter.dto.RetornoPadraoDTO;
import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;
import com.inter.enums.StatusFaturaEnum;
import com.inter.repository.FaturaRepository;
import com.inter.service.FaturaService;
import com.inter.utils.Converte;
import com.inter.utils.Mensagens;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class FaturaServiceImpl implements FaturaService {

    private final FaturaRepository faturaRepository;

    @Transactional
    @Override
    public Fatura criarPrimeiraFatura(ContaCorrente contaCorrente) {
        Fatura fatura = new Fatura();
        fatura.setStatus(StatusFaturaEnum.ABERTA.getDescricao());
        fatura.setMesDaFatura(LocalDate.now().getMonthValue());
        fatura.setContaCorrente(contaCorrente);
        fatura.setAnoFatura(LocalDate.now().getYear());
        fatura = faturaRepository.save(fatura);
        return fatura;
    }

    @Transactional
    @Override
    public Fatura buscarFaturaAberta(Long contaCorrente) {
        return faturaRepository.buscarFaturaAbertaPorIdContaCorrente(contaCorrente);
    }

    @Transactional
    @Override
    public void fecharFatura(Long idFaturaAberta, Long contaCorrente) {
        Optional<Fatura> faturaAberta = (faturaRepository.buscarFaturaAbertaPorIdFaturaEIdContaCorrente(idFaturaAberta, contaCorrente));
        if (faturaAberta.isPresent()) {
            Fatura fatura = faturaAberta.get();
            fatura.setStatus(StatusFaturaEnum.FECHADA.getDescricao());
            faturaRepository.save(fatura);

            //O bloco a seguir irá deixar uma fatura aberta caso exista faturas futuras ou ira abrir uma nova.
            int mesFaturaSeguinte = (fatura.getMesDaFatura() + 1) >= 12 ? 1 : (fatura.getMesDaFatura() + 1);

            Optional<Fatura> faturaQueSeraAberta = faturaRepository.findByMesDaFatura(mesFaturaSeguinte);
            if (faturaQueSeraAberta.isPresent()) {
                Fatura fatura2 = faturaQueSeraAberta.get();
                fatura2.setStatus(StatusFaturaEnum.ABERTA.getDescricao());
                faturaRepository.save(fatura2);
            } else {
                Fatura faturaNovaAberta = new Fatura();
                faturaNovaAberta.setContaCorrente(faturaAberta.get().getContaCorrente());
                faturaNovaAberta.setStatus(StatusFaturaEnum.ABERTA.getDescricao());
                faturaNovaAberta.setMesDaFatura(mesFaturaSeguinte);
                faturaRepository.save(faturaNovaAberta);
            }
        } else {
            throw new RuntimeException(Mensagens.FATURA_NAO_ENCONTRADA);
        }
    }

    @Transactional
    @Override
    public RetornoPadraoDTO buscarFaturas() {
        RetornoPadraoDTO retorno = new RetornoPadraoDTO<>();
        List<Fatura> listaDeFaturas;
        List<FaturaDTO> listaDeFaturasDTO = new ArrayList<>();
        listaDeFaturas = (List<Fatura>) faturaRepository.findAll();
        for (Fatura fatura : listaDeFaturas) {
            listaDeFaturasDTO.add(Converte.converteFaturaParaFaturaDTO(fatura));
        }
        retorno.setDados(listaDeFaturasDTO);
        return retorno;
    }

    @Transactional
    @Override
    public List<Fatura> buscarFaturasFuturas() {
        List<Fatura> faturasFuturas = faturaRepository.findByStatus(StatusFaturaEnum.FUTURA.getDescricao());
        return faturasFuturas;
    }

}
