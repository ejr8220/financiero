package com.bancopichincha.ec.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancopichincha.ec.Dto.ClienteDtoResponse;
import com.bancopichincha.ec.entities.Cliente;
import com.bancopichincha.ec.service.impl.ClienteServiceImpl;
import com.bancopichincha.ec.utils.CustomException;


@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	ClienteServiceImpl clienteServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteDtoResponse>> getAll() throws Exception{
		List<ClienteDtoResponse> lstClienteResponse = new ArrayList<ClienteDtoResponse>();

		clienteServiceImpl.findAll().forEach(new Consumer<Cliente>() {
			@Override
			public void accept(final Cliente cliente) {
				lstClienteResponse.add(new ClienteDtoResponse(cliente.getNombre(), cliente.getDireccion(),
						cliente.getTelefono(), cliente.getClave(), cliente.getEstado()));
			}

		});
		if (lstClienteResponse.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstClienteResponse, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> newCliente(@RequestBody Cliente cliente) throws Exception{
		if (clienteServiceImpl.existsCliente(cliente.getIdentificacion())) {
			//throw new CustomException("Cliente ya Existe");
			throw new CustomException("1","Cliente ya Existe");
		}
		cliente = clienteServiceImpl.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) throws Exception{
		cliente.setIdCliente(id);
		Cliente clienteData = clienteServiceImpl.update(id, cliente);
		return ResponseEntity.status(HttpStatus.OK).body(clienteData);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable("id") long id) throws Exception{
		clienteServiceImpl.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
