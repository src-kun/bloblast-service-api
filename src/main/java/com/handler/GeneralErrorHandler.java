package com.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.GeneralError;
import com.exception.GeneralServiceException;

@ControllerAdvice
public class GeneralErrorHandler {
	
	@ExceptionHandler(GeneralServiceException.class)
	@ResponseBody
	public GeneralError ExcepitonHandler(HttpServletRequest request, Exception e) {
	    return new GeneralError(e.getMessage());
	}
}