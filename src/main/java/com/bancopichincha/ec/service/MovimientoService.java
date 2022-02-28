package com.bancopichincha.ec.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.bancopichincha.ec.entities.Movimiento;

public interface MovimientoService extends BaseService<Movimiento>{
	public Optional<Movimiento> findFirstByCuentaIdOrderByIdMovimientoDesc(Long cuentaId) throws Exception;
	public float getSaldoCuenta(Long cuentaId) throws Exception;
	public float getSaldoDiario(Long cuentaId, Date fecha) throws Exception;
	public List<Movimiento> getEstadoCuenta(Date fechaDesde, Date fechaHasta, Long idCliente);	
}
