package com.bancopichincha.ec.Dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDtoRequest implements Serializable {
	/**
	 * 
	 */
	
	private Integer idCliente;
	private String nombre;
	private String genero;
	private Date feNacimiento;
	private String identificacion;
	private String telefono;
	private String clave;
	private String estado;
}
