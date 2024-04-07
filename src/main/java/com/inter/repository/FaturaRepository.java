package com.inter.repository;

import com.inter.entity.Fatura;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface FaturaRepository extends CrudRepository<Fatura, Long> {

    @Query(value = "SELECT * FROM fatura FT " +
            "INNER JOIN conta_corrente CC " +
            "ON FT.id_conta_corrente = CC.ID " +
            "WHERE CC.numero_conta = :contaCorrente AND FT.ABERTA = TRUE", nativeQuery = true)
    Fatura buscarFaturaAbertaPorIdContaCorrente(Long contaCorrente);
}
