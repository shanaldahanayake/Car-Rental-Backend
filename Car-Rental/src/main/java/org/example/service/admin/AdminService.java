package org.example.service.admin;

import org.example.entity.CarEntity;
import org.example.model.CarDto;

import java.util.List;

public interface AdminService {
    boolean postCar(CarDto dto);

    List<CarDto> getAllCars();

    void deleteCar(Integer id);

    boolean updateCar(CarDto dto);

}
