package com.bancopichincha.ec.service.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bancopichincha.ec.Dto.MovimientoDtoRequest;
import com.bancopichincha.ec.entities.Movimiento;
import com.bancopichincha.ec.utils.CustomException;

@Component
public class ValidatorMovimiento {
	@Autowired
	MovimientoServiceImpl movimientoServiceImpl; 
	@Autowired
	private Environment env;
	
	public Movimiento validaNuevoMovimiento(MovimientoDtoRequest movimientoDto) throws Exception {
		float monto = Float.parseFloat(env.getProperty("financiero.debito.cupo_diario"));
		float saldo = movimientoServiceImpl.getSaldoCuenta(movimientoDto.getCuentaId());
		float saldoDiario = monto - movimientoServiceImpl.getSaldoDiario(movimientoDto.getCuentaId(),
				Date.valueOf(movimientoDto.getFeCreacion()));
		if (saldo == 0 && movimientoDto.getValor() < 0) {
			throw new CustomException("1", "Saldo no Disponible");
		}
		saldo += movimientoDto.getValor();
		if (saldo < 0) {
			throw new CustomException("1", "Monto mayor al Saldo Disponible");
		}
		if (movimientoDto.getValor() < 0) {
			saldoDiario += movimientoDto.getValor();
			if (saldoDiario < 0) {
				throw new CustomException("1", "Cupo Diario Excedido");
			}
		}
		String tipoMovimiento = "Credito";
		Movimiento movimiento = new Movimiento();
		movimiento.setCuentaId(movimientoDto.getCuentaId());
		if (movimientoDto.getValor() < 0) {
			tipoMovimiento = "Debito";
		}
		movimiento.setTipoMovimiento(tipoMovimiento);
		movimiento.setValor(movimientoDto.getValor());
		movimiento.setSaldo(saldo);
		movimiento.setUsuarioCreacion(movimientoDto.getUsuarioCreacion());
		movimiento.setFeCreacion(Date.valueOf(movimientoDto.getFeCreacion()));
		movimiento.setIpCreacion(movimientoDto.getIpCreacion());
		return movimiento;
	}
}
