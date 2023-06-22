package com.lophiester.webService.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
