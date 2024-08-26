package com.vtxlab.bootcamp.springbootexercise1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorRequestDTO;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorResponseDTO;

public interface CalculatorOperation {

        @GetMapping("/") // "/x=1&y=2&operation=add"
        @ResponseStatus(HttpStatus.OK)
        CalculatorResponseDTO calculateByRequestParam(@RequestParam String x, //
                        @RequestParam String y, //
                        @RequestParam String operation);

        @PostMapping("/")
        @ResponseStatus(HttpStatus.CREATED)
        CalculatorResponseDTO calculateByDTO(
                        @RequestBody CalculatorRequestDTO calculatorRequest);

        @GetMapping("/{x}/{y}/{operation}")
        @ResponseStatus(HttpStatus.OK)
        CalculatorResponseDTO calculateByPathVariable(@PathVariable String x, //
                        @PathVariable String y, //
                        @PathVariable String operation);
}
