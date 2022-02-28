package com.bancopichincha.ec.Dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDtoResponse implements Serializable{

	private String nombre;
	private String direccion;
	private String telefono;
	private String clave;
	private String estado;
}
