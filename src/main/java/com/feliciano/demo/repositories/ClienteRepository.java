package com.feliciano.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feliciano.demo.resources.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}
