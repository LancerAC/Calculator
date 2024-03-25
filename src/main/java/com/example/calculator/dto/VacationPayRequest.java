package com.example.calculator.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationPayRequest {


    @Min(value = 1, message = "The salary should be more than 1")
    private double salary;


    @Min(value = 1,
            message = "The number of vacation days should be more than 1")
    private int numberOfVacationDays;
}
