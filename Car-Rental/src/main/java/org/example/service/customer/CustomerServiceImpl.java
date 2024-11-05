package org.example.service.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.model.CarDto;
import org.example.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CarRepository repository;
    private final ObjectMapper mapper;

    @Override
    public List<CarDto> getAllCars() {
        List<CarDto> carDtos=new ArrayList<>();

        repository.findAll().forEach(car->{
            carDtos.add(mapper.convertValue(car, CarDto.class));
        });
        return carDtos;
    }
}
