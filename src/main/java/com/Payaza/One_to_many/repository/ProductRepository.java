package com.Payaza.One_to_many.repository;

import com.Payaza.One_to_many.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
