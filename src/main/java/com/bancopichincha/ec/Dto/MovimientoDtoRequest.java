package com.bancopichincha.ec.Dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDtoRequest {
	private Long idMovimiento;
	private Long cuentaId;
	private float valor;
	private String usuarioCreacion;
	private String feCreacion;
	private String ipCreacion;
}
