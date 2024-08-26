package com.vtxlab.bootcamp.springbootexercise1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CalculatorResponseDTO {
  String x;
  String y;
  String operation;
  String result;

}
