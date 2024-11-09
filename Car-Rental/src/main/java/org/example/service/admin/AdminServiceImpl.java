package org.example.service.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.entity.CarEntity;
import org.example.entity.BookACarEntity;
import org.example.enums.BookStatus;
import org.example.model.BookACarDto;
import org.example.model.CarDto;
import org.example.repository.BookACarRepository;
import org.example.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final CarRepository repository;
    private final ObjectMapper mapper;
    private final BookACarRepository bookACarRepository;

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
    public void deleteCar(Long id) {
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

    @Override
    public List<BookACarDto> getBookings() {
        List<BookACarDto> bookACarDtos = new ArrayList<>();

        for (BookACarEntity bookACarEntity : bookACarRepository.findAll()) {
            bookACarDtos.add(bookACarEntity.getBookACarDto());
        }
        return bookACarDtos;
    }

    @Override
    public List<CarDto> getCarByBrand(String brand) {
        List<CarDto> carDtos=new ArrayList<>();

        repository.findAllByBrand(brand).forEach(car->{
            carDtos.add(mapper.convertValue(car, CarDto.class));
        });
        return  carDtos;
    }

    @Override
    public List<CarDto> getCarByColor(String color) {
        List<CarDto> carDtos=new ArrayList<>();

        repository.findAllByColor(color).forEach(car->{
            carDtos.add(mapper.convertValue(car, CarDto.class));
        });
        return  carDtos;
    }

    @Override
    public List<CarDto> getCarByTransmission(String transmission) {
        List<CarDto> carDtos=new ArrayList<>();

        repository.findAllByTransmission(transmission).forEach(car->{
            carDtos.add(mapper.convertValue(car, CarDto.class));
        });
        return  carDtos;
    }

    @Override
    public List<CarDto> getCarByTypeFuel(String fuel) {
        List<CarDto> carDtos=new ArrayList<>();

        repository.findAllByTypeFuel(fuel).forEach(car->{
            carDtos.add(mapper.convertValue(car, CarDto.class));
        });
        return  carDtos;
    }

    @Override
    public void respondBooking(Long id, String status) {
        Optional<BookACarEntity> carEntity = bookACarRepository.findById(id);
        if(carEntity.isPresent()){
            BookACarEntity bookACarEntity = carEntity.get();

            if(Objects.equals(status,"APPROVED")){
                bookACarEntity.setBookStatus(BookStatus.APPROVED);
            }else{
                bookACarEntity.setBookStatus(BookStatus.REJECTED);
            }
            bookACarRepository.save(bookACarEntity);
        }
    }




}
