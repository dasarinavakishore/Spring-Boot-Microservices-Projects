package com.microservice.os.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.os.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
