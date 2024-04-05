package com.inter.repository;

import com.inter.entity.Fatura;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface FaturaRepository extends CrudRepository<Fatura, Long> {
}
