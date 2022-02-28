package com.bancopichincha.ec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancopichincha.ec.entities.Cliente;
import com.bancopichincha.ec.entities.Cuenta;

@Repository
public interface CuentaRepository extends BaseRepository<Cuenta, Long> {
	Cuenta findByTipoCuentaAndNumero(String tipoCuenta, String numero); 
}
