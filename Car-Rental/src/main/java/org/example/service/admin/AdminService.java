package org.example.service.admin;

import org.example.entity.CarEntity;
import org.example.model.BookACarDto;
import org.example.model.CarDto;

import java.util.List;

public interface AdminService {
    boolean postCar(CarDto dto);

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    boolean updateCar(CarDto dto);

    List<BookACarDto> getBookings();

    List<CarDto> getCarByBrand(String brand);
    List<CarDto> getCarByColor(String color);
    List<CarDto> getCarByTransmission(String transmission);
    List<CarDto> getCarByTypeFuel(String fuel);

}
