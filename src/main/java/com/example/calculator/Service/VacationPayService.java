package com.example.calculator.Service;

import com.example.calculator.dto.VacationPayDatesRequest;
import com.example.calculator.dto.VacationPayRequest;

import java.time.LocalDate;

public interface VacationPayService {
    double calculateVacationPay(VacationPayRequest vacationPayRequest);

    double calculateVacationPayWithDate(VacationPayDatesRequest vacationPayRequest);
}
