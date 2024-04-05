package com.inter.repository;

import com.inter.entity.Cliente;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
