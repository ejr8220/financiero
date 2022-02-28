package com.bancopichincha.ec.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente", schema = "financiero")
public class Cliente extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(name = "clave")
	private String clave;
	
	@Column(name = "estado")
	private String estado;
	
	
	@Column(name = "usuario_creacion")
	private String usuarioCreacion;
	
	@Column(name = "fe_creacion")
	private Date feCreacion;
	
	@Column(name = "ip_creacion")
	private String ipCreacion;	

}
