package com.vtxlab.bootcamp.springbootexercise1.exception;

import java.util.Objects;
import org.springframework.validation.BindingResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vtxlab.bootcamp.springbootexercise1.exception.exceptionEnum.Code;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ApiResp<T> {
  private int code;
  private String message;
  private T data;

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }

  public T getData() {
    return this.data;
  }

  public static <T> ApiResponseBuilder<T> builder() {
    return new ApiResponseBuilder<>();
  }

  private ApiResp(ApiResponseBuilder<T> builder) {
    this.code = builder.code;
    this.message = builder.message;
    this.data = builder.data;
  }

  public static class ApiResponseBuilder<T> {
    private int code;
    private String message;
    private T data;

    public ApiResponseBuilder<T> status(Code code) {
      this.code = code.getCode();
      this.message = code.getMessage();
      return this;
    }

    public ApiResponseBuilder<T> concatMessageIfPresent(String str) {
      if (this.message != null && str != null)
        this.message += " " + str;
      return this;
    }

    public ApiResponseBuilder<T> ok() {
      this.code = Code.OK.getCode();
      this.message = Code.OK.getMessage();
      return this;
    }

    public ApiResponseBuilder<T> error() {
      this.code = Code.INVALID_INPUT.getCode();
      this.message = Code.INVALID_INPUT.getMessage();
      return this;
    }

    public ApiResponseBuilder<T> error(Code code) {
      this.code = code.getCode();
      return this;
    }

    public ApiResponseBuilder<T> error(Code code, BindingResult bindingResult) {
      this.code = code.getCode();
      this.message =
          Objects.requireNonNull(bindingResult.getFieldError()).getField() + " "
              + bindingResult.getFieldError().getCode();
      return this;
    }

    public ApiResponseBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public ApiResponseBuilder<T> message(String message) {
      this.message = message;
      return this;
    }

    public ApiResp<T> build() {
      if (this.code == 0 || this.message == null)
        throw new RuntimeException();
      return new ApiResp<>(this);
    }

  }
}

// {
// "code" : 200,
// "message" : "OK",
// "data" : [

// ],
// "error" : [
// "", ""
// ],
// }
