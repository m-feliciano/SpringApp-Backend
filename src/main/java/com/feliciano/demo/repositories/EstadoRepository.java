package com.feliciano.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feliciano.demo.resources.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {


}
