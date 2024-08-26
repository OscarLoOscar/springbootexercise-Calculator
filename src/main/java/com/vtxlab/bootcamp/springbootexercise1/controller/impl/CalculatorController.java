package com.vtxlab.bootcamp.springbootexercise1.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtxlab.bootcamp.springbootexercise1.controller.CalculatorOperation;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorRequestDTO;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorResponseDTO;
import com.vtxlab.bootcamp.springbootexercise1.service.CalculatorService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:8085")
public class CalculatorController implements CalculatorOperation {

  @Autowired
  CalculatorService calculatorService;

  // @Override
  // public String calculate(String x, String y, String operation, Model model)
  // throws DivideByZeroException {
  // CalculatorResponseDTO result =
  // this.calculateByRequestParam(x, y, operation);
  // model.addAttribute("result", result.getResult());
  // return "/calculate";
  // }

  @Override
  public CalculatorResponseDTO calculateByRequestParam(String x, String y,
      String operation) {
    return calculatorService.calculateByVariable(Double.parseDouble(x),
        Double.parseDouble(y), operation);
  }

  @Override
  public CalculatorResponseDTO calculateByDTO(
      CalculatorRequestDTO calculatorRequest) {
    return calculatorService.calculateByDTO(calculatorRequest);
  }

  @Override
  public CalculatorResponseDTO calculateByPathVariable(String x, String y,
      String operation) {
    return calculatorService.calculateByVariable(Double.parseDouble(x),
        Double.parseDouble(y), operation);
  }




}
