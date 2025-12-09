package com.feliciano.store.repositories;

import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.resources.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Transactional(readOnly = true)
	Page<Order> findByClient(Client clent, Pageable pageRequest);
}
