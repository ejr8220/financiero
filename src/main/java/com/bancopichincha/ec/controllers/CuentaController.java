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
import com.bancopichincha.ec.Dto.CuentaDtoResponse;
import com.bancopichincha.ec.entities.Cliente;
import com.bancopichincha.ec.entities.Cuenta;
import com.bancopichincha.ec.service.impl.ClienteServiceImpl;
import com.bancopichincha.ec.service.impl.CuentaServiceImpl;
import com.bancopichincha.ec.utils.CustomException;


@RestController
@RequestMapping("/api")
public class CuentaController {
	@Autowired
	CuentaServiceImpl cuentaServiceImpl;
	
	@Autowired
	ClienteServiceImpl clienteServiceImpl;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CuentaController.class);	

	@GetMapping("/cuentas")
	public ResponseEntity<List<CuentaDtoResponse>> getAll() throws Exception{
		List<CuentaDtoResponse> lstCuentaResponse = new ArrayList<CuentaDtoResponse>();
		
		cuentaServiceImpl.findAll().forEach(new Consumer <Cuenta> () {
			
			@Override
			public void accept(final Cuenta cuenta) {
				lstCuentaResponse.add(new CuentaDtoResponse(cuenta.getNumero(), cuenta.getTipoCuenta(), 
						                                    cuenta.getSaldoInicial(), cuenta.getEstado(),
						                                    cuenta.getCliente().getNombre()));
			}
			
		});
		if (lstCuentaResponse.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstCuentaResponse, HttpStatus.OK);
	}

	@PostMapping("/cuentas")
	public ResponseEntity<?> newCuenta(@RequestBody Cuenta cuenta) throws Exception{
		if (cuentaServiceImpl.existsCuenta(cuenta.getTipoCuenta(), cuenta.getNumero())){
			throw new CustomException("1","Cuenta ya Existe");			
		}
		cuenta = cuentaServiceImpl.save(cuenta);
		return ResponseEntity.status(HttpStatus.OK).body(cuenta);
	}
	
	@PutMapping("/cuentas/{id}")
	public ResponseEntity<?> updateCuenta(@PathVariable("id") long id, @RequestBody Cuenta cuenta) throws Exception{
		Cuenta cuentaData = cuentaServiceImpl.update(id, cuenta);
		return ResponseEntity.status(HttpStatus.OK).body(cuentaData);
	}

	@DeleteMapping("/cuentas/{id}")
	public ResponseEntity<?> deleteCuentas(@PathVariable("id") long id) throws Exception{
		cuentaServiceImpl.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
