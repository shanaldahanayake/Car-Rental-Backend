package org.example.repository;

import org.example.entity.BookACarEntity;
import org.example.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {
    List<CarEntity> findAllByBrand(String brand);

    List<CarEntity> findAllByColor(String color);

    List<CarEntity> findAllByTransmission(String transmission);

    List<CarEntity> findAllByTypeFuel(String fuel);
}
