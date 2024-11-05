package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.CarDto;
import org.example.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/car")
    public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto){
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
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id){
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


}
