package com.bancopichincha.ec.Dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovimientoDtoResponse implements Serializable{
	private String fecha;
	private String cliente;
	private String numero;
	private String tipo;
	private float saldoInicial;
	private String estado;
	private float movimiento;
	private float saldoDisponible; 
	
}
