package com.cdac.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO {

    private long id;
    @NotEmpty(message = "Category name is required!")
    private String name;
    private Date createdAt;
    private Date updatedAt;

}
