package com.vtxlab.bootcamp.springbootexercise1.infra;

import java.math.BigDecimal;

public class Mapper {
  public static String map(double input) {
    return String.valueOf(input);
  }

  public static BigDecimal map(String input) {
    return BigDecimal.valueOf(Double.parseDouble(input));
  } 
}
