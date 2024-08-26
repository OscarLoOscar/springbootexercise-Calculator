package com.vtxlab.bootcamp.springbootexercise1.infra.OperationEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.vtxlab.bootcamp.springbootexercise1.exception.DivideByZeroException;
import com.vtxlab.bootcamp.springbootexercise1.exception.exceptionEnum.Code;
import lombok.Getter;

@Getter
public enum Operation {
  ADD("a", "add"), //
  SUBTRACT("s", "sub"), //
  MULTIPLY("m", "mul"), //
  DIVIDE("d", "div");

  private String operation;
  private String validValuesOperation;

  private Operation(String operation, String validValuesOperation) {
    this.operation = operation;
    this.validValuesOperation = validValuesOperation;
  }

  public String performOperation(BigDecimal x, BigDecimal y) throws DivideByZeroException {
    return switch (this) {
      case ADD -> x.add(y).setScale(5).toString();
      case SUBTRACT -> x.subtract(y).setScale(5).toString();
      case MULTIPLY -> x.multiply(y).setScale(5).toString();
      case DIVIDE -> {
        if (y.equals(BigDecimal.ZERO)) {
          throw new DivideByZeroException(Code.DIVIDE_BY_ZERO);
        }
        // You can customize the MathContext and RoundingMode here
        yield x.divide(y, 5, RoundingMode.HALF_UP).toString();
      }
      default -> throw new IllegalArgumentException("Invalid operation");
    };
  }
}
