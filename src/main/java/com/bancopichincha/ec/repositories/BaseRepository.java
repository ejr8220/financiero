package com.bancopichincha.ec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E, Long> extends JpaRepository<E, Long> {

}
