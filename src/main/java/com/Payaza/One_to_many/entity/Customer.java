package com.Payaza.One_to_many.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @NotBlank(message = "Customer name is required")
    private String customerName;
    @Email(message = "Invalid email format")
    private String customerEmail;
    @NotBlank(message = "Customer address is required")
    private String customerAddress;
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String customerPhoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Product> productList;
}