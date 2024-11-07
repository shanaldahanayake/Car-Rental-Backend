package org.example.model;

import lombok.Data;

@Data
public class CarDto {
    private Long id;

    private String brand;

    private String color;

    private String description;

    private String model;

    private double price;

    private String transmission;

    private String typeFuel;
}
