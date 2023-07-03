package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.BookEntity;
import org.example.entity.ProfileEntity;
import org.example.enums.TakenBookStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakenBookDto {
    private String id;
    private String studentId;
    private String bookId;
    private LocalDateTime createdDate;
    private TakenBookStatus status;
    private String note;
    private LocalDate returnDate;
}
