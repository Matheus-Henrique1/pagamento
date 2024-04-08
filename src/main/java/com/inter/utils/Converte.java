package com.inter.utils;

import com.inter.dto.ClienteDTO;
import com.inter.dto.ContaCorrenteDTO;
import com.inter.dto.FaturaDTO;
import com.inter.dto.TransacaoDTO;
import com.inter.entity.Cliente;
import com.inter.entity.ContaCorrente;
import com.inter.entity.Fatura;
import com.inter.entity.Transacao;

public class Converte {

    public static Cliente converteClienteDTOParaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setDatNascimento(clienteDTO.getDatNascimento());
        cliente.setId(clienteDTO.getId());
        return cliente;
    }

    public static ClienteDTO converteClienteParaClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setDatNascimento(cliente.getDatNascimento());
        clienteDTO.setId(cliente.getId());
        return clienteDTO;
    }

    public static TransacaoDTO converterTransacaoParaTransacaoDTO(Transacao transacao) {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setId(transacao.getId());
        transacaoDTO.setDataTransacao(transacao.getDataTransacao());
        transacaoDTO.setNomeEstabelecimento(transacao.getNomeEstabelecimento());
        transacaoDTO.setValorDaTransacao(transacao.getValorDaTransacao());
        transacaoDTO.setValorDaParcela(transacao.getValorDaParcela());
        transacaoDTO.setNumeroDeParcelas(transacao.getNumeroDeParcelas());
        transacaoDTO.setParcela(transacao.getParcela());
        return transacaoDTO;
    }

    public static ContaCorrenteDTO converterContaCorrenteParaContaCorrenteDTO(ContaCorrente contaCorrente) {
        ContaCorrenteDTO contaCorrenteDTO = new ContaCorrenteDTO();
        contaCorrenteDTO.setId(contaCorrente.getId());
        contaCorrenteDTO.setNumeroConta(contaCorrente.getNumeroConta());
        contaCorrenteDTO.setCliente(converteClienteParaClienteDTO(contaCorrente.getCliente()));
        return contaCorrenteDTO;
    }


    public static FaturaDTO converteFaturaParaFaturaDTO(Fatura fatura) {
        FaturaDTO faturaDTO = new FaturaDTO();
        faturaDTO.setId(fatura.getId());
        faturaDTO.setStatus(fatura.getStatus());
        faturaDTO.setMesDaFatura(fatura.getMesDaFatura());
        fatura.getTransacoes().forEach(e ->
                faturaDTO.getTransacoes().add(Converte.converterTransacaoParaTransacaoDTO(e))

        );
        return faturaDTO;
    }

    public static Transacao converteTransacaoDTOTransacao(TransacaoDTO transacaoDTO, Fatura fatura) {
        Transacao transacao = new Transacao();
        transacao.setDataTransacao(transacaoDTO.getDataTransacao());
        transacao.setValorDaTransacao(transacaoDTO.getValorDaTransacao());
        transacao.setNomeEstabelecimento(transacaoDTO.getNomeEstabelecimento());
        transacao.setNumeroContaCorrente(transacaoDTO.getNumeroContaCorrente());
        transacao.setNumeroDeParcelas(transacaoDTO.getNumeroDeParcelas());
        transacao.setFatura(fatura);
        transacao.setParcela(transacaoDTO.getParcela());
        transacao.setValorDaParcela(transacaoDTO.getValorDaParcela());
        return transacao;
    }

}
