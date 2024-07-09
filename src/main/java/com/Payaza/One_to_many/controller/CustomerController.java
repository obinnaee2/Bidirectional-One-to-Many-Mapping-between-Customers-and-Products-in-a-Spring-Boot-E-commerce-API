package com.Payaza.One_to_many.controller;

import com.Payaza.One_to_many.entity.Customer;
import com.Payaza.One_to_many.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer with ID " + customerId + " not found.");
        }
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId, @RequestBody Customer customerDetails) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();
            customerToUpdate.setCustomerName(customerDetails.getCustomerName());
            customerToUpdate.setCustomerEmail(customerDetails.getCustomerEmail());
            customerToUpdate.setCustomerAddress(customerDetails.getCustomerAddress());
            customerToUpdate.setCustomerPhoneNumber(customerDetails.getCustomerPhoneNumber());
            return ResponseEntity.ok(customerRepository.save(customerToUpdate));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer with ID " + customerId + " not found.");
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {
        Optional<Customer> customerToDelete = customerRepository.findById(customerId);
        if (customerToDelete.isPresent()) {
            customerRepository.delete(customerToDelete.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer with ID " + customerId + " not found.");
        }
    }
}

