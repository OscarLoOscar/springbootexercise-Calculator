package com.vtxlab.bootcamp.springbootexercise1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CalculatorRequestDTO {
  String x;
  String y;
  String operation;
}
