package com.bancopichincha.ec.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancopichincha.ec.Dto.MovimientoDtoRequest;
import com.bancopichincha.ec.Dto.MovimientoDtoResponse;
import com.bancopichincha.ec.entities.Movimiento;
import com.bancopichincha.ec.service.impl.CuentaServiceImpl;
import com.bancopichincha.ec.service.impl.MovimientoServiceImpl;
import com.bancopichincha.ec.service.impl.ValidatorMovimiento;
import com.bancopichincha.ec.utils.CustomException;

@RestController
@RequestMapping("/api")
public class MovimientoController {

	@Autowired
	MovimientoServiceImpl movimientoServiceImpl;
	@Autowired
	CuentaServiceImpl mcuentaServiceImpl;
	@Autowired
	ValidatorMovimiento validatorMovimiento;
	@Autowired
	private Environment env;

	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientoController.class);

	@GetMapping("/movimientos/reportes/{fechaDesde}/{fechaHasta}/{id}")
	public ResponseEntity<List<MovimientoDtoResponse>> getEstadoCuenta(@PathVariable("fechaDesde") String fechaDesde,
			@PathVariable("fechaHasta") String fechaHasta, @PathVariable("id") Long id) throws Exception {
		List<MovimientoDtoResponse> lstMovimientoResponse = new ArrayList<MovimientoDtoResponse>();
		movimientoServiceImpl.getEstadoCuenta(Date.valueOf(fechaDesde), Date.valueOf(fechaHasta), id)
				.forEach(new Consumer<Movimiento>() {

					@Override
					public void accept(final Movimiento movimiento) {
						lstMovimientoResponse.add(new MovimientoDtoResponse(movimiento.getFeCreacion().toString(),
								movimiento.getCuenta().getCliente().getNombre(), movimiento.getCuenta().getNumero(),
								movimiento.getCuenta().getTipoCuenta(), movimiento.getCuenta().getSaldoInicial(),
								movimiento.getCuenta().getEstado(), movimiento.getValor(), movimiento.getSaldo()));
					}

				});
		if (lstMovimientoResponse.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstMovimientoResponse, HttpStatus.OK);

	}

	@PostMapping("/movimientos")
	public ResponseEntity<?> newMovimiento(@RequestBody MovimientoDtoRequest movimientoDto) throws Exception {
		Movimiento movimiento = validatorMovimiento.validaNuevoMovimiento(movimientoDto); 
		movimiento = movimientoServiceImpl.save(movimiento);
		movimientoDto.setIdMovimiento(movimiento.getIdMovimiento());
		return ResponseEntity.status(HttpStatus.OK).body(movimientoDto);
	}

	@PutMapping("/movimientos/{id}")
	public ResponseEntity<?> updateMovimiento(@PathVariable("id") long id, @RequestBody Movimiento movimiento)
			throws Exception {
		movimiento.setIdMovimiento(id);
		Movimiento movimientoData = movimientoServiceImpl.update(id, movimiento);
		return ResponseEntity.status(HttpStatus.OK).body(movimientoData);
	}

	@DeleteMapping("/movimientos/{id}")
	public ResponseEntity<?> deleteMovimientos(@PathVariable("id") long id) throws Exception {
		movimientoServiceImpl.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("movimientos/{id}")
	public ResponseEntity<?> patchMovimiento(@PathVariable("id") long id, @RequestBody Map<Object, Object> fields)
			throws Exception {
		Movimiento movimiento = movimientoServiceImpl.patch(id, fields);
		return ResponseEntity.status(HttpStatus.OK).body(movimiento);
	}

}
