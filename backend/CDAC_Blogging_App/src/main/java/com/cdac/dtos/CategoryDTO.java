package com.cdac.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    private Long id;
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;
    @Size(min = 10, max = 500)
    private String description;
    private Date createdAt;
    private Date updatedAt;

}
