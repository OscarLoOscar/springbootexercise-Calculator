package com.vtxlab.bootcamp.springbootexercise1;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.bootcamp.springbootexercise1.controller.impl.CalculatorController;
import com.vtxlab.bootcamp.springbootexercise1.exception.DivideByZeroException;
import com.vtxlab.bootcamp.springbootexercise1.exception.exceptionEnum.Code;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorRequestDTO;
import com.vtxlab.bootcamp.springbootexercise1.model.CalculatorResponseDTO;
import com.vtxlab.bootcamp.springbootexercise1.service.CalculatorService;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;

    @Test
    void testCalculateByDTO_Add() throws Exception {
        CalculatorRequestDTO mockCalculatorRequestDTO =
                CalculatorRequestDTO.builder()//
                        .x("1")//
                        .y("2")//
                        .operation("add")//
                        .build();
        CalculatorResponseDTO movcCalculatorResponseDTO =
                CalculatorResponseDTO.builder()//
                        .x("1")//
                        .y("2")//
                        .operation("add")//
                        .result("3.0")//
                        .build();
        Mockito.when(calculatorService.calculateByDTO(mockCalculatorRequestDTO))//
                .thenReturn(movcCalculatorResponseDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/")//
                .contentType(MediaType.APPLICATION_JSON)//
                .content(new ObjectMapper()
                        .writeValueAsString(mockCalculatorRequestDTO)))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.x").value("1"))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.y").value("2"))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.operation")
                        .value("add"))//
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.result").value("3.0"))//
                .andExpect(MockMvcResultMatchers.status().isCreated())//
                .andExpect(MockMvcResultMatchers.content().json(
                        "{\"x\":\"1\",\"y\":\"2\",\"operation\":\"add\",\"result\":\"3.0\"}"));
    }

    @Test
    void testCalculateByDTO_SUBTRACT() throws Exception {
        CalculatorRequestDTO mockCalculatorRequestDTO =
                CalculatorRequestDTO.builder()//
                        .x("10")//
                        .y("20")//
                        .operation("sub")//
                        .build();
        CalculatorResponseDTO movcCalculatorResponseDTO =
                CalculatorResponseDTO.builder()//
                        .x("10")//
                        .y("20")//
                        .operation("sub")//
                        .result("-10.0")//
                        .build();
        Mockito.when(calculatorService.calculateByDTO(mockCalculatorRequestDTO))//
                .thenReturn(movcCalculatorResponseDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/")//
                .contentType(MediaType.APPLICATION_JSON)//
                .content(new ObjectMapper()
                        .writeValueAsString(mockCalculatorRequestDTO)))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.x").value("10"))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.y").value("20"))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.operation")
                        .value("sub"))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.result")
                        .value("-10.0"))//
                .andExpect(MockMvcResultMatchers.status().isCreated())//
                .andExpect(MockMvcResultMatchers.content().json(
                        "{\"x\":\"10\",\"y\":\"20\",\"operation\":\"sub\",\"result\":\"-10.0\"}"));
    }


    @Test
    void testCalculateByDTO_MULTIPLY() throws Exception {
        CalculatorRequestDTO mockCalculatorRequestDTO =
                CalculatorRequestDTO.builder()//
                        .x("9.0")//
                        .y("-9.0")//
                        .operation("mul")//
                        .build();
        CalculatorResponseDTO movcCalculatorResponseDTO =
                CalculatorResponseDTO.builder()//
                        .x("9.0")//
                        .y("-9.0")//
                        .operation("mul")//
                        .result("-81.0")//
                        .build();
        Mockito.when(calculatorService.calculateByDTO(mockCalculatorRequestDTO))//
                .thenReturn(movcCalculatorResponseDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/")//
                .contentType(MediaType.APPLICATION_JSON)//
                .content(new ObjectMapper()
                        .writeValueAsString(mockCalculatorRequestDTO)))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.x").value("9.0"))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.y").value("-9.0"))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.operation")
                        .value("mul"))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.result")
                        .value("-81.0"))//
                .andExpect(MockMvcResultMatchers.status().isCreated())//
                .andExpect(MockMvcResultMatchers.content().json(
                        "{\"x\":\"9.0\",\"y\":\"-9.0\",\"operation\":\"mul\",\"result\":\"-81.0\"}"));
    }


    @Test
    void testCalculateByDTO_DIVIDE() throws Exception {
        CalculatorRequestDTO mockCalculatorRequestDTO =
                CalculatorRequestDTO.builder()//
                        .x("10.0")//
                        .y("0.0")//
                        .operation("div")//
                        .build();

        Mockito.when(calculatorService.calculateByDTO(mockCalculatorRequestDTO))//
                .thenThrow(new DivideByZeroException(Code.DIVIDE_BY_ZERO));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper()
                        .writeValueAsString(mockCalculatorRequestDTO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Divide by zero "))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
