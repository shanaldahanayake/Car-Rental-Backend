package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.BookStatus;
import org.example.model.BookACarDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "bookACar")
@ToString
public class BookACarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Double price;
    private BookStatus bookStatus;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "userId",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "carId",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CarEntity carEntity;

    public BookACarDto getBookACarDto(){
        BookACarDto bookACarDto=new BookACarDto();
        bookACarDto.setId(getId());
        bookACarDto.setDays(getDays());
        bookACarDto.setBookStatus(getBookStatus());
        bookACarDto.setPrice(getPrice());
        bookACarDto.setToDate(getToDate());
        bookACarDto.setFromDate(getFromDate());
        bookACarDto.setEmail(userEntity.getEmail());
        bookACarDto.setUsername(userEntity.getUsername());
        bookACarDto.setUserId(userEntity.getId());
        bookACarDto.setCarId(carEntity.getId());
        return bookACarDto;
    }
}
