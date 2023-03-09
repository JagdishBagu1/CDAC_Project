package com.cdac.dtos;

import com.cdac.entities.Category;
import com.cdac.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDTO {

    private long id;
    @NotEmpty
    @Size(min = 5, max = 200)
    private String title;
    private String imageUrl;
    @NotEmpty
    @Size(min = 10, max = 65530)
    private String content;
    private Date createdAt;
    private Date updatedAt;

    private CategoryDTO categoryDTO;
    private UserDTO userDTO;

}
