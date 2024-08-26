package com.vtxlab.bootcamp.springbootexercise1.service;

import com.vtxlab.bootcamp.springbootexercise1.exception.DivideByZeroException;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorRequestDTO;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorResponseDTO;

public interface CalculatorService {

  double calculate(double x, double y, String operation);

  // Spring boot exercies 1
  CalculatorResponseDTO calculateByDTO(CalculatorRequestDTO request);

  CalculatorResponseDTO calculateByVariable(double x, double y,
      String operation) ;

}
