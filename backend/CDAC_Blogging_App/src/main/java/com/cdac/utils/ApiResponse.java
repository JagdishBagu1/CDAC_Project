package com.cdac.utils;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<T> {

    private boolean success;
    private String error;
    private String message;
    private T body;

}
