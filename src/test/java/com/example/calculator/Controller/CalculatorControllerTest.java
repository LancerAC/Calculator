package com.example.calculator.Controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/calculate";

    private static final String URL_DATES = "/calculate/dates";

    @Test
    @Tag("without dates")
    @SneakyThrows
    void shouldReturnExpectedValueAndStatus200(){
        double expectedValue = 35631.4;
        String jsonInput = "{\"salary\": 40000, \"numberOfVacationDays\": 30}";

        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(String.
                        valueOf(expectedValue)));
    }

    @Test
    @Tag("without dates")
    @SneakyThrows
    void returnStatus400IfSalaryLessThanOne() {
        final String jsonInput =
                "{\"salary\": 0, \"numberOfVacationDays\": 28}";

        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
    @Test
    @Tag("without dates")
    @SneakyThrows
    void returnStatus400IfNumberOfVacationDaysLessThanOne() {
        final String jsonInput = "{\"salary\": 346346, \"numberOfVacationDays\": 0}";

        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    @Tag("with dates")
    @SneakyThrows
    void shouldReturnZeroAndStatus200(){
        double expectedValue = 0.0;

        final String jsonInput =  "{ \"salary\": 10000," +
                " \"startDate\": \"2022-01-01\"," +
                " \"numberOfVacationDays\": 5 }";


        mockMvc.perform(MockMvcRequestBuilders.get(URL_DATES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput))
                .andExpect(MockMvcResultMatchers.content().string(String
                        .valueOf(expectedValue)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Tag("with dates")
    @SneakyThrows
    void shouldReturnExpectedValue(){
        double expectedValue = 34146.76;

        final String jsonInput =  "{ \"salary\": 50000," +
                " \"startDate\": \"2022-01-01\"," +
                " \"numberOfVacationDays\": 40 }";

        mockMvc.perform(MockMvcRequestBuilders.get(URL_DATES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.content().string(String
                        .valueOf(expectedValue)));
    }

    @Test
    @Tag("with dates")
    @SneakyThrows
    void shouldReturnStatus400IfStartDateIsIncorrect(){
        final String jsonInput =  "{ \"salary\": 50000," +
                " \"startDate\": \"2oo3-gg-034\"," +
                " \"numberOfVacationDays\": 40 }";

        mockMvc.perform(MockMvcRequestBuilders.get(URL_DATES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
    @Test
    @Tag("with dates")
    @SneakyThrows
    void shouldReturnStatus400IfSalaryLessThanOne(){
        final String jsonInput =  "{ \"salary\": 0," +
                " \"startDate\": \"2003-12-03\"," +
                " \"numberOfVacationDays\": 40 }";

        mockMvc.perform(MockMvcRequestBuilders.get(URL_DATES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}