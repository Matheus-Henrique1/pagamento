package com.inter.service.impl;

import com.inter.dto.FaturaDTO;
import com.inter.dto.RetornoPadraoDTO;
import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;
import com.inter.enums.StatusFaturaEnum;
import com.inter.repository.FaturaRepository;
import com.inter.service.FaturaService;
import com.inter.utils.Converte;
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

            /**
             * o bloco a seguir irá deixar uma fatura aberta caso exista faturas futuras ou ira abrir uma nova.
             */

            Integer mesFaturaFechada = (fatura.getMesDaFatura() + 1);
            mesFaturaFechada = mesFaturaFechada > 12 ? 1 : mesFaturaFechada;
            Optional<Fatura> faturaQueSeraAberta = faturaRepository.findByMesDaFatura(mesFaturaFechada);
            if (faturaQueSeraAberta.isPresent()) {
                Fatura fatura2 = faturaQueSeraAberta.get();
                fatura2.setStatus(StatusFaturaEnum.ABERTA.getDescricao());
                faturaRepository.save(fatura2);
            }else{
                Fatura faturaNovaAberta = new Fatura();
                faturaNovaAberta.setContaCorrente(faturaAberta.get().getContaCorrente());
                faturaNovaAberta.setStatus(StatusFaturaEnum.ABERTA.getDescricao());
                faturaNovaAberta.setMesDaFatura(faturaAberta.get().getMesDaFatura() >= 12 ? 1 : (faturaAberta.get().getMesDaFatura() + 1));
                faturaRepository.save(faturaNovaAberta);
            }
        } else {
            throw new RuntimeException("Não foram encontradas faturas abertas com o id " + idFaturaAberta + ", e com o número de conta corrente " + contaCorrente);
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
