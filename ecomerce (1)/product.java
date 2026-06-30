package com.agri.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;  // KES — BigDecimal avoids floating-point errors

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;

    @Min(0)
    @Column(nullable = false)
    private int stock;

    // null = unlimited (services), 0 = out of stock, >0 = available quantity
    @Column(name = "unlimited_stock", nullable = false)
    private boolean unlimitedStock = false;

    public enum Category {
        TRACTOR_HIRE,
        VETERINARIAN_SERVICES,
        CHICKS,
        LIVESTOCK_FOOD
    }
}