package com.vtxlab.bootcamp.springbootexercise1.exception;

import com.vtxlab.bootcamp.springbootexercise1.exception.exceptionEnum.Code;

public class InvalidInputException extends BusinessException{
  
  public InvalidInputException(Code message) {
    super(message);
  }
}
