package com.example.calculator.dto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationPayDatesRequest {

    @Min(value = 1, message = "The salary should be more than 1")
    private double salary;

    @NotNull(message = "enter the start of the vacation")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @Min(value = 1,
            message = "The number of vacation days should be more than 1")
    private int numberOfVacationDays;


}
