package com.vtxlab.bootcamp.springbootexercise1.exception;

import com.vtxlab.bootcamp.springbootexercise1.exception.exceptionEnum.Code;
import lombok.Getter;

@Getter
public class BusinessException extends Exception {
  private Code code;

  public BusinessException(Code code) {
    super(code.getMessage());
    this.code = code;
  }
}
