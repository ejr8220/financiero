package com.bancopichincha.ec.service.impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.bancopichincha.ec.entities.Cliente;
import com.bancopichincha.ec.entities.Movimiento;
import com.bancopichincha.ec.repositories.BaseRepository;
import com.bancopichincha.ec.repositories.ClienteRepository;
import com.bancopichincha.ec.service.ClienteService;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long> implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository) {
		super(baseRepository);
	}



	@Override
	public Cliente findByIdentificacion(String identificacion) throws Exception {
		// TODO Auto-generated method stub
		Cliente cliente = clienteRepository.findByIdentificacion(identificacion);
		return cliente;
		
	}


	@Override
	public boolean existsCliente(String identificacion) throws Exception {
		boolean isCliente = false;
		Cliente cliente = this.findByIdentificacion(identificacion);
		if (cliente != null) {
			isCliente = true;
		}			
		return isCliente;		
	}
	
	@Override
	public Cliente patch(Long id, Map<Object, Object> fields) throws Exception {
		// TODO Auto-generated method stub
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (optionalCliente.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Cliente.class, (String) key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, optionalCliente.get(), value);
			});
		}
		return clienteRepository.saveAndFlush(optionalCliente.get());
	}		
}
