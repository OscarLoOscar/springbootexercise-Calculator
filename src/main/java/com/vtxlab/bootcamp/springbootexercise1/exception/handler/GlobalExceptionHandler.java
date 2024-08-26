package com.vtxlab.bootcamp.springbootexercise1.exception.handler;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.vtxlab.bootcamp.springbootexercise1.exception.ApiResp;
import com.vtxlab.bootcamp.springbootexercise1.exception.BusinessException;
import com.vtxlab.bootcamp.springbootexercise1.exception.DivideByZeroException;
import com.vtxlab.bootcamp.springbootexercise1.exception.InvalidInputException;
import com.vtxlab.bootcamp.springbootexercise1.exception.exceptionEnum.Code;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // @ExceptionHandler(value = BusinessException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> bc2311ExceptionHandler(BusinessException e) {
  // return ApiResp.<Void>builder() //
  // .status(Code.fromCode(e.getCode().getCode())) //
  // .concatMessageIfPresent(e.getMessage())//
  // // .data(null) //
  // .build();
  // }
  @ExceptionHandler(value = DivideByZeroException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> handleDivideByZeroException(DivideByZeroException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e))//
        .concatMessageIfPresent("")//
        // .data(null) //
        .build();
  }

  @ExceptionHandler(value = InvalidInputException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> handleInvalidInputException(InvalidInputException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e))//
        .concatMessageIfPresent("")//
        // .data(null) //
        .build();
  }


  // @ExceptionHandler(value = RuntimeException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> runtimeExceptionHandler(RuntimeException e) {
  // return ApiResp.<Void>builder() //
  // .status(getRespCode(e)) //
  // .concatMessageIfPresent(e.getMessage())//
  // // .data(null) //
  // .build();
  // }

  // @ExceptionHandler(value = Exception.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> exceptionHandler(Exception e) {
  // return ApiResp.<Void>builder() //
  // .status(getRespCode(e)) //
  // .concatMessageIfPresent(e.getMessage())//
  // // .data(null) //
  // .build();
  // }

  private static Code getRespCode(Exception e) {
  
    if (e instanceof IllegalArgumentException) {
      return Code.INVALID_INPUT;
    }
    if (e instanceof DivideByZeroException) {
      return Code.DIVIDE_BY_ZERO;
    }

    // ...
    // return null;
    return Code.INVALID_OPERATION;
  }
}
