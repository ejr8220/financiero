package com.bancopichincha.ec.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.bancopichincha.ec.controllers.ClienteController;
import com.bancopichincha.ec.entities.Cuenta;
import com.bancopichincha.ec.entities.Movimiento;
import com.bancopichincha.ec.repositories.BaseRepository;
import com.bancopichincha.ec.repositories.CuentaRepository;
import com.bancopichincha.ec.service.CuentaService;

@Service
public class CuentaServiceImpl extends BaseServiceImpl<Cuenta, Long> implements CuentaService {
	private static final Logger LOGGER=LoggerFactory.getLogger(CuentaServiceImpl.class);	

	@Autowired
	CuentaRepository cuentaRepository;	
	
	public CuentaServiceImpl(BaseRepository<Cuenta, Long> baseRepository) {
		super(baseRepository);
		// TODO Auto-generated constructor stub
	}	

	@Override
	public Cuenta findByTipoCuentaAndNumero(String tipoCuenta, String numero) throws Exception {
		// TODO Auto-generated method stub
		Cuenta cuenta = cuentaRepository.findByTipoCuentaAndNumero(tipoCuenta, numero);
		return cuenta;
	}

	@Override
	public boolean existsCuenta(String tipoCuenta, String numero) throws Exception {
		// TODO Auto-generated method stub
			boolean isCuenta = false;
			Cuenta cuenta = this.findByTipoCuentaAndNumero(tipoCuenta, numero);
			
			if (cuenta != null) {
				isCuenta = true;
			}			
			return isCuenta;
	}

	@Override
	public Cuenta patch(Long id, Map<Object, Object> fields) throws Exception {
		// TODO Auto-generated method stub
		Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
		if (optionalCuenta.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Movimiento.class, (String) key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, optionalCuenta.get(), value);
			});
		}
		return cuentaRepository.saveAndFlush(optionalCuenta.get());
	}		
}
