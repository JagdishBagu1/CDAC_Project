package com.cdac.dtos;

import com.cdac.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    @Email
    @NotEmpty(message = "Can't be null or empty")
    private String email;
    @Size(min = 2, max = 255, message = "Size must be between 2 and 255")
    private String firstName;
    @Size(min = 2, max = 255, message = "Size must be between 2 and 255")
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;

}
