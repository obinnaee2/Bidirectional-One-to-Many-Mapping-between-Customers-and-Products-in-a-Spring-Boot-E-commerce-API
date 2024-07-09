package com.Payaza.One_to_many.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @NotBlank(message = "Product name is required")
    private String productName;
    @NotBlank(message = "Product Description is required")
    private String productDescription;
    @NotNull(message = "Product price is required")
    @Positive(message = "Product price must be zero or positive")
    private Double productPrice;
    @NotBlank(message = "Product category is required")
    private String productCategory;

    @ManyToOne @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
}