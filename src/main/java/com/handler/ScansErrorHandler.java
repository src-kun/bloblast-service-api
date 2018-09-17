package com.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bean.exception.GeneralErrorBean;
import com.exception.AlreadyRunningException;

@ControllerAdvice
@ResponseBody
@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class ScansErrorHandler {

	@ExceptionHandler(AlreadyRunningException.class)
	public GeneralErrorBean alreadyRunningException(HttpServletRequest request, Exception e) {
		return new GeneralErrorBean(request.getRequestURI().toString(), e.getMessage());
	}

}
