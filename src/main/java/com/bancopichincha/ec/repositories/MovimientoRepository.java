package com.bancopichincha.ec.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bancopichincha.ec.entities.Movimiento;

/**
 * @author epin
 * 
 */
@Repository
public interface MovimientoRepository extends BaseRepository< Movimiento, Long>{
	//obtener el ultimo registro de una cuenta para poder consultar el saldo
	Optional<Movimiento> findFirstByCuentaIdOrderByIdMovimientoDesc(Long cuentaId);
	
	//Obtener el saldo diario por cuenta
	@Query("SELECT COALESCE(SUM(valor),0) FROM Movimiento WHERE cuentaId =?1 and feCreacion = ?2 AND valor < 0")
	Float getSaldoDiario(Long cuentaId, Date fecha);
	
	@Query("SELECT m FROM Movimiento m WHERE m.feCreacion >=?1 and m.feCreacion <=?2 and m.cuenta.clienteId = ?3")
	List<Movimiento> getEstadoCuenta(Date fechaDesde, Date fechaHasta, Long idCliente);
}
