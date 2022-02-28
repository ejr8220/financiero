package com.bancopichincha.ec.service.impl;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.bancopichincha.ec.controllers.CuentaController;
import com.bancopichincha.ec.entities.Cuenta;
import com.bancopichincha.ec.entities.Movimiento;
import com.bancopichincha.ec.repositories.BaseRepository;
import com.bancopichincha.ec.repositories.CuentaRepository;
import com.bancopichincha.ec.repositories.MovimientoRepository;
import com.bancopichincha.ec.service.MovimientoService;

@Service
public class MovimientoServiceImpl extends BaseServiceImpl<Movimiento, Long> implements MovimientoService {
	
	@Autowired
	MovimientoRepository movimientoRepository;

	@Autowired
	CuentaRepository cuentaRepository;
	private static final Logger LOGGER=LoggerFactory.getLogger(CuentaController.class);	

	public MovimientoServiceImpl(BaseRepository<Movimiento, Long> baseRepository) {
		super(baseRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<Movimiento> findFirstByCuentaIdOrderByIdMovimientoDesc(Long cuentaId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Movimiento> optionalMovimiento = movimientoRepository.findFirstByCuentaIdOrderByIdMovimientoDesc(cuentaId);
		return optionalMovimiento;
	}

	@Override
	public float getSaldoCuenta(Long cuentaId) throws Exception {
		// TODO Auto-generated method stub
		float saldo = 0;
		Optional<Cuenta> optionalCuenta = cuentaRepository.findById(cuentaId); 
		if (optionalCuenta.isPresent()) {
			saldo = optionalCuenta.get().getSaldoInicial();
		}		
		Optional<Movimiento> movimiento = this.findFirstByCuentaIdOrderByIdMovimientoDesc(cuentaId);
		if (movimiento.isPresent()) {
			saldo = movimiento.get().getSaldo();
		}
		return saldo;
	}

	@Override
	public float getSaldoDiario(Long cuentaId, Date fecha) {
		// TODO Auto-generated method stub
		LOGGER.info("1.2.1 cuenta id " + cuentaId + " fecha " + fecha);
		
		return movimientoRepository.getSaldoDiario(cuentaId, fecha);
	}

	@Override
	public List<Movimiento> getEstadoCuenta(Date fechaDesde, Date fechaHasta, Long idCliente) {
		// TODO Auto-generated method stub
		return movimientoRepository.getEstadoCuenta(fechaDesde, fechaHasta, idCliente);	
	}

	@Override
	public Movimiento patch(Long id, Map<Object, Object> fields) throws Exception {
		// TODO Auto-generated method stub
		Optional<Movimiento> optionalMovimiento = movimientoRepository.findById(id);
		if (optionalMovimiento.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Movimiento.class, (String) key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, optionalMovimiento.get(), value);
			});
		}
		return movimientoRepository.saveAndFlush(optionalMovimiento.get());
	}	
	
}
