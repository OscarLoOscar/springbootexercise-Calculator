package com.vtxlab.bootcamp.springbootexercise1.service.impl;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.vtxlab.bootcamp.springbootexercise1.exception.DivideByZeroException;
import com.vtxlab.bootcamp.springbootexercise1.infra.Mapper;
import com.vtxlab.bootcamp.springbootexercise1.infra.OperationEnum.Operation;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorRequestDTO;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorResponseDTO;
import com.vtxlab.bootcamp.springbootexercise1.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

  @Override
  public double calculate(double x, double y, String division) {
    BigDecimal operandX = BigDecimal.valueOf(x);
    BigDecimal operandY = BigDecimal.valueOf(y);

    Operation operation = getOperationEnum(division);

    String result = operation.performOperation(operandX, operandY);
    return Double.valueOf(result);
  }

  @Override
  public CalculatorResponseDTO calculateByDTO(CalculatorRequestDTO request) {
    BigDecimal operandX = Mapper.map(request.getX());
    BigDecimal operandY = Mapper.map(request.getY());
    Operation operation = getOperationEnum(request.getOperation());
    String result = operation.performOperation(operandX, operandY);

    return CalculatorResponseDTO.builder() //
        .x(request.getX()) //
        .y(request.getY()) //
        .operation(request.getOperation())//
        .result(result) //
        .build();
  }

  @Override
  public CalculatorResponseDTO calculateByVariable(double x, double y,
      String operation) {
    BigDecimal operandX = BigDecimal.valueOf(x);
    BigDecimal operandY = BigDecimal.valueOf(y);
    Operation op = getOperationEnum(operation);
    String result = op.performOperation(operandX, operandY);
    return CalculatorResponseDTO.builder() //
        .x(String.valueOf(x)) //
        .y(String.valueOf(y)) //
        .operation(operation)//
        .result(result) //
        .build();
  }


  private Operation getOperationEnum(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Operation cannot be null");
    }
    for (Operation op : Operation.values()) {
      if (op.getOperation().equals(input) || //
          op.getValidValuesOperation().equals(input)) {
        return op;
      }
    }
    throw new IllegalArgumentException("Invalid operation");
  }
}
