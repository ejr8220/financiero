package com.bancopichincha.ec.Dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CuentaDtoResponse implements Serializable{
	private String numero;
	private String tipoCuenta;
	private Float saldoInicial;
	private String estado;
	private String cliente;
}
