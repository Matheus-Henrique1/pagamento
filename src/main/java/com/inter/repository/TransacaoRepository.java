package com.inter.repository;

import com.inter.entity.Fatura;
import com.inter.entity.Transacao;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    @Query(value = "SELECT * FROM transacao TS " +
            "INNER JOIN fatura FT " +
            "ON TS.id_fatura = FT.id " +
            "INNER JOIN conta_corrente CC " +
            "ON FT.id_conta_corrente = CC.id " +
            "WHERE CC.numero_conta = :contaCorrente AND FT.mes_fatura = :mes", nativeQuery = true)
    List<Transacao> buscaFaturaPorMesEContaCorrente(Long mes, Long contaCorrente);
}
