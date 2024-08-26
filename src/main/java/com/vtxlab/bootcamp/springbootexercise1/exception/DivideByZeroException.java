package com.vtxlab.bootcamp.springbootexercise1.exception;

import com.vtxlab.bootcamp.springbootexercise1.exception.exceptionEnum.Code;

public class DivideByZeroException extends BusinessRuntimeException {

  public DivideByZeroException(Code message) {
    super(message);
  }
}
