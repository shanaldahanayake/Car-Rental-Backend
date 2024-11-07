package org.example.service.customer;

import org.example.model.BookACarDto;
import org.example.model.CarDto;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    List<CarDto> getAllCars();
    boolean bookACar(BookACarDto bookACarDto) throws ParseException;
    CarDto getCarById(Long id);
    List<BookACarDto> getBookingByUserId(Long userId);
}
