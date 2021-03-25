package com.at.serviceBash.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoomException extends RuntimeException {
    private Integer code;
    private String msg;

}
