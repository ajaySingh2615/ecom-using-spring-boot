package com.cadt.ecomproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cadt.ecomproject.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("Select * from Orders o Join fetch o.user")
    List<Orders> findAllOrdersWithUsers();

}
