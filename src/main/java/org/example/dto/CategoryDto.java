package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String  id;
    private String categoryName;
    private LocalDateTime createdDate;
    private boolean visible;
}
