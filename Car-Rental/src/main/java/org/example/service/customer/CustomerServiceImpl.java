package org.example.service.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.entity.BookACarEntity;
import org.example.entity.CarEntity;
import org.example.entity.UserEntity;
import org.example.enums.BookStatus;
import org.example.model.BookACarDto;
import org.example.model.CarDto;
import org.example.repository.BookACarRepository;
import org.example.repository.CarRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CarRepository repository;
    private final UserRepository userRepository;
    private final BookACarRepository bookACarRepository;
    private final ObjectMapper mapper;

    @Override
    public List<CarDto> getAllCars() {
        List<CarDto> carDtos=new ArrayList<>();

        repository.findAll().forEach(car->{
            carDtos.add(mapper.convertValue(car, CarDto.class));
        });
        return carDtos;
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) throws ParseException {
        Optional<CarEntity> carEntity = repository.findById(bookACarDto.getCarEntityId());
        Optional<UserEntity> userEntity = userRepository.findById(bookACarDto.getUserEntityId());

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        if(carEntity.isPresent()&&userEntity.isPresent()){
            Date fromDate = dateFormat.parse(String.valueOf(bookACarDto.getFromDate()));
            Date toDate = dateFormat.parse(String.valueOf(bookACarDto.getToDate()));

            CarEntity car=carEntity.get();
            BookACarEntity bookACarEntity=new BookACarEntity();
            bookACarEntity.setUserEntity(userEntity.get());
            bookACarEntity.setCarEntity(car);
            bookACarEntity.setBookStatus(BookStatus.PENDING);
            bookACarEntity.setFromDate(fromDate);
            bookACarEntity.setToDate(toDate);
            long diffInMills= toDate.getTime()-fromDate.getTime();
            long days= TimeUnit.MILLISECONDS.toDays(diffInMills);
            bookACarEntity.setDays(days);
            bookACarEntity.setPrice(car.getPrice()*days);
            bookACarRepository.save(bookACarEntity);
            return true;
        }

        return false;
    }

    @Override
    public CarDto getCarById(Long id) {
        Optional<CarEntity> carEntity = repository.findById(id);
        return mapper.convertValue(carEntity, CarDto.class);
    }

    @Override
    public List<BookACarDto> getBookingByUserId(Long userId) {

        List<BookACarDto> carDtos=new ArrayList<>();

        bookACarRepository.findAllByUserEntityId(userId).forEach(element->{
            carDtos.add(mapper.convertValue(element, BookACarDto.class));
        });

/*
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACarEntity::getBookACarDto).collect(Collectors.toList());
        */
    return null;
    }

}
