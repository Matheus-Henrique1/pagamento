package com.inter.repository;

import com.inter.entity.ContaCorrente;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ContaCorrenteRepository extends CrudRepository<ContaCorrente, Long> {
}
