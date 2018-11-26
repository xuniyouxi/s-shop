package com.vg.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class Exceptioncontroller {
   
	@ExceptionHandler(Exception.class)
	public String exceptHand(Exception ex) {
		System.out.println("cuole ");
		return "redirect:NewFile.html";
	}
	@ExceptionHandler(AbstractMethodError.class)
	public String exceptHand2(Exception ex) {
		System.out.println("cuole ");
		return "NewFile.html";
	}
}
