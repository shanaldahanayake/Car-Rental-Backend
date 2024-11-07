package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.BookStatus;

import java.util.Date;

@Getter
@Setter
@ToString
public class BookACarDto {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Double price;
    private BookStatus bookStatus;
    private Long carEntityId;
    private Long userEntityId;
    private String username;
    private String email;
}
