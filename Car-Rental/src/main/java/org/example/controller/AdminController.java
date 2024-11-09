package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.enums.BookStatus;
import org.example.model.BookACarDto;
import org.example.model.CarDto;
import org.example.model.SearchACarDto;
import org.example.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/car")
    public ResponseEntity<?> postCar(@RequestBody CarDto carDto){
        boolean success=adminService.postCar(carDto);
        if(success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/car")
    public ResponseEntity<?> getAllCars(@ModelAttribute CarDto carDto){
        return ResponseEntity.ok(adminService.getAllCars());
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        adminService.deleteCar(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/car")
    public ResponseEntity<?> updateCar(@ModelAttribute CarDto carDto){
        boolean success=adminService.updateCar(carDto);
        if(success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookACarDto>> getBookings(){
        return ResponseEntity.ok(adminService.getBookings());
    }

    @GetMapping("/car/search")
    public ResponseEntity<List<CarDto>> searchCar(@ModelAttribute SearchACarDto searchACarDto){
        if(searchACarDto.getMethod().equals("Brand")){
            return ResponseEntity.ok((adminService.getCarByBrand(searchACarDto.getType())));
        }else if(searchACarDto.getMethod().equals("Fuel Type")){
            return ResponseEntity.ok((adminService.getCarByTypeFuel(searchACarDto.getType())));
        } else if (searchACarDto.getMethod().equals("Color")) {
            return ResponseEntity.ok((adminService.getCarByColor(searchACarDto.getType())));
        } else if (searchACarDto.getMethod().equals("Transmission")) {
            return ResponseEntity.ok((adminService.getCarByTransmission(searchACarDto.getType())));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/bookings/{id}/{status}")
    public void acceptBooking(@PathVariable Long id,@PathVariable String status){
        adminService.respondBooking(id, status);
    }
}
