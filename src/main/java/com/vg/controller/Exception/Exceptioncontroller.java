package com.vg.controller.Exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class Exceptioncontroller {
   
	@ExceptionHandler(Exception.class)
	public String exceptHand(Exception ex) {
		System.out.println("cuole ");
		return "{'code':'500'}";
	}
	@ExceptionHandler(AbstractMethodError.class)
	public String exceptHand2(Exception ex) {
		System.out.println("cuole ");
		return "{'code':'500'}";
	}
}
