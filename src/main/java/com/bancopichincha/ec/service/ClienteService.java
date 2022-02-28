package com.bancopichincha.ec.service;

import com.bancopichincha.ec.entities.Cliente;

public interface ClienteService extends BaseService<Cliente> {
	public Cliente findByIdentificacion(String identificacion) throws Exception;
	
	public boolean existsCliente(String identificacion) throws Exception;
}
