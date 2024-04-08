package com.inter.service.impl;

import com.inter.dto.FaturaDTO;
import com.inter.dto.RetornoPadraoDTO;
import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;
import com.inter.enums.MesesEnum;
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

@Singleton
@RequiredArgsConstructor
public class FaturaServiceImpl implements FaturaService {

    private final FaturaRepository faturaRepository;

    @Transactional
    @Override
    public Fatura criarPrimeiraFatura(ContaCorrente contaCorrente) {
        Fatura fatura = new Fatura();
        fatura.setStatus(StatusFaturaEnum.ABERTA.getDescricao());
        fatura.setMesDaFatura(LocalDate.now().getMonth().getValue());
        fatura.setContaCorrente(contaCorrente);
        fatura = faturaRepository.save(fatura);
        return fatura;
    }

    @Override
    public Fatura buscarFaturaAberta(Long contaCorrente) {
        return faturaRepository.buscarFaturaAbertaPorIdContaCorrente(contaCorrente);
    }

    @Transactional
    @Override
    public Fatura criarFaturasFuturas(ContaCorrente contaCorrente, int mes) {
        Fatura fatura = new Fatura();
        fatura.setContaCorrente(contaCorrente);
        fatura.setMesDaFatura(mes);
        return faturaRepository.save(fatura);
    }

    @Transactional
    @Override
    public void fecharFatura() {
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


    @Override
    public List<Fatura> buscarFaturasFuturas() {
        List<Fatura> faturasFuturas = faturaRepository.findByStatus(StatusFaturaEnum.FUTURA.getDescricao());
        return faturasFuturas;
    }

}
