package com.inter.repository;

import com.inter.entity.Transacao;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {
}
