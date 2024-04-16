package com.example.apibanco.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ErrorDTO {
    /**
     * The datetime when error happens.
     */
    private LocalDateTime timestamp;
    /**
     * The HTTP status code of the API response.
     */
    private Integer status;
    /**
     * The error message.
     */
    private String error;
    /**
     * Path of the permiso API.
     */
    private String path;
}
