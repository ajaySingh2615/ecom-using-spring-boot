package com.cadt.ecomproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cadt.ecomproject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
