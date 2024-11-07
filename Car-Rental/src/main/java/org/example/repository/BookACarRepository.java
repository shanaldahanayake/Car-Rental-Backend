package org.example.repository;

import org.example.entity.BookACarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookACarRepository extends JpaRepository<BookACarEntity,Long> {

    List<BookACarEntity> findAllByUserEntityId(Long userId);


}
