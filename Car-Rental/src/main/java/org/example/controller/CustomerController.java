package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.BookACarDto;
import org.example.model.CarDto;
import org.example.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/car/book")
    public ResponseEntity<Object> bookACar(@RequestBody BookACarDto bookACarDto){

        try{
            boolean b = customerService.bookACar(bookACarDto);
            if(b){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id){
        CarDto carById = customerService.getCarById(id);
        if(carById==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carById);
    }

    @GetMapping("/car/bookings/{id}")
    public ResponseEntity<List<BookACarDto>> getBookingsByUserId(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getBookingByUserId(id));
    }
}
