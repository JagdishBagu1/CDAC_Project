package com.cdac.dtos;

import com.cdac.entities.Role;
import com.cdac.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;
    @Email
    @NotEmpty(message = "Can't be null or empty")
    private String email;
    @NotEmpty(message = "Can't be null or empty")
    private String password;
    @Size(min = 2, max = 255, message = "Size must be between 2 and 255")
    private String firstName;
    @Size(min = 2, max = 255, message = "Size must be between 2 and 255")
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private Date createdAt;
    private Date updatedAt;

}
