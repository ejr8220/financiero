package com.bancopichincha.ec.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter

public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name= "genero")
	private String genero;
	
	@Column(name = "fe_nacimiento")
	private Date feNacimiento;
	
	@Column(name = "identificacion")
	private String identificacion;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	
}
