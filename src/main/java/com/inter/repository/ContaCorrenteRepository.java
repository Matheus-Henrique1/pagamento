package com.inter.repository;

import com.inter.entity.ContaCorrente;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface ContaCorrenteRepository extends CrudRepository<ContaCorrente, Long> {

    @Query(value = "SELECT MAX(cc.numero_conta) FROM conta_corrente cc ", nativeQuery = true)
    Optional<Long> buscarUltimaConta();

    ContaCorrente findByNumeroConta(Long numeroContaCorrente);

}
