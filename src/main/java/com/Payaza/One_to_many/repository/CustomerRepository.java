package com.Payaza.One_to_many.repository;

import com.Payaza.One_to_many.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
