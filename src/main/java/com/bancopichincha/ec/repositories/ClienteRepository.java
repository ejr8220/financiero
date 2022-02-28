package com.bancopichincha.ec.repositories;

import org.springframework.stereotype.Repository;

import com.bancopichincha.ec.entities.Cliente;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long>{
	Cliente findByIdentificacion(String identificacion);
}
