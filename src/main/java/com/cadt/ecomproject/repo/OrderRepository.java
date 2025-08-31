package com.cadt.ecomproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cadt.ecomproject.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
