package com.vtxlab.bootcamp.springbootexercise1.exception;

import com.vtxlab.bootcamp.springbootexercise1.exception.exceptionEnum.Code;
import lombok.Getter;

@Getter
public class BusinessRuntimeException extends RuntimeException {
  private Code code;

  public BusinessRuntimeException(Code code) {
    super(code.getMessage());
    this.code = code;
  }

}
