package com.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exception.GeneralServiceException;

public class MyExceptionHandler {
	
	@ExceptionHandler(GeneralServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerMyException(GeneralServiceException ex) {
        Map<String,Object> result = new HashMap<>();
        result.put("message", ex.getMessage());
        result.put("error type", "MyException");
        return result;
    }
}
