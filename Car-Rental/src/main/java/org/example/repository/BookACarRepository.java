package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.BookACarEntity;
import org.example.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookACarRepository extends JpaRepository<BookACarEntity,Long> {

    List<BookACarEntity> findAllByUserEntityId(Long userId);

}
