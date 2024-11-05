package org.example.service.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.entity.CarEntity;
import org.example.model.CarDto;
import org.example.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final CarRepository repository;
    private final ObjectMapper mapper;

    @Override
    public boolean postCar(CarDto dto) {
        try{
            CarEntity entity=new CarEntity();
            entity.setBrand(dto.getBrand());
            entity.setColor(dto.getColor());
            entity.setDescription(dto.getDescription());
            entity.setModel(dto.getModel());
            entity.setPrice(dto.getPrice());
            entity.setTransmission(dto.getTransmission());
            entity.setTypeFuel(dto.getTypeFuel());
            repository.save(entity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<CarDto> getAllCars() {
        List<CarDto> carDtos=new ArrayList<>();

        repository.findAll().forEach(car->{
            carDtos.add(mapper.convertValue(car, CarDto.class));
        });

        return  carDtos;
    }

    @Override
    public void deleteCar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public boolean updateCar(CarDto dto) {
        try{
            CarEntity carEntity = repository.findById(dto.getId()).orElseThrow();
            carEntity.setBrand(dto.getBrand());
            carEntity.setModel(dto.getModel());
            carEntity.setColor(dto.getColor());
            carEntity.setPrice(dto.getPrice());
            carEntity.setTransmission(dto.getTransmission());
            carEntity.setTypeFuel(dto.getTypeFuel());
            carEntity.setDescription(dto.getDescription());
            repository.save(carEntity);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
