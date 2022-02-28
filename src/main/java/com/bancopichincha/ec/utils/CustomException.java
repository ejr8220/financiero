package com.bancopichincha.ec.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
	private String code;
	public CustomException (String code,String mensaje){
        super(mensaje);
        this.code = code;
    }
}
