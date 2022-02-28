package com.bancopichincha.ec.service;

import com.bancopichincha.ec.entities.Cuenta;

public interface CuentaService extends BaseService<Cuenta>{
	public Cuenta findByTipoCuentaAndNumero(String tipoCuenta, String numero) throws Exception;
	
	public boolean existsCuenta(String tipoCuenta, String numero) throws Exception;
}
