package com.bancopichincha.ec.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.bancopichincha.ec.entities.Movimiento;
import com.bancopichincha.ec.repositories.BaseRepository;
import com.bancopichincha.ec.service.BaseService;
import com.bancopichincha.ec.utils.CustomException;

public abstract class BaseServiceImpl<E, id> implements BaseService<E> {
	
	protected BaseRepository<E, Long> baseRepository;
	
	public BaseServiceImpl(BaseRepository<E, Long> baseRepository) {
		this.baseRepository = baseRepository;
	}
	
	@Override
	@Transactional
	public List<E> findAll() throws Exception {
		List<E> entities = baseRepository.findAll();
		return entities;
	}
	
	@Override
	@Transactional
	public E findById(Long id) throws Exception {
		Optional<E> entityOptional = baseRepository.findById(id);
		return entityOptional.get();
	}
	
	@Override
	@Transactional
	public E save(E entity) throws Exception {
		entity = baseRepository.saveAndFlush(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public E update(Long id, E entity) throws Exception {
		Optional<E> entityOptional = baseRepository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new CustomException("1","Registro no Existe");
		}
		E entityUpdate = entityOptional.get();
		entityUpdate = baseRepository.saveAndFlush(entity);
		return entityUpdate;
	}
	
	@Override
	@Transactional
	public boolean delete(Long id) throws Exception {
		if(baseRepository.existsById(id)) {
			baseRepository.deleteById(id);
			return true;
		} else {
			throw new CustomException("1","Registro no Existe");
		}
	}


}
