package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.CarDto;
import org.example.service.customer.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @GetMapping("/car")
    public ResponseEntity<List<CarDto>> getAllCars(){
        List<CarDto> allCars = customerService.getAllCars();
        return ResponseEntity.ok(allCars);
    }
}
