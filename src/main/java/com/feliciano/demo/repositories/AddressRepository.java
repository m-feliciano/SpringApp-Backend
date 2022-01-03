package com.feliciano.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feliciano.demo.resources.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
