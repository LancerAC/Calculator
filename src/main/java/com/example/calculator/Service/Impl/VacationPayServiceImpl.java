package com.example.calculator.Service.Impl;

import com.example.calculator.Service.VacationPayService;
import com.example.calculator.dto.VacationPayDatesRequest;
import com.example.calculator.dto.VacationPayRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class VacationPayServiceImpl implements VacationPayService {
    private double calculateVacationPayForDays(double salary, int workingDays) {
        return ((salary / 29.3) * workingDays) * 0.87; //вычитаем налог НДФЛ
    }

    @Override
    public double calculateVacationPay(VacationPayRequest vacationPayRequest) {
        int workingDays = vacationPayRequest.getNumberOfVacationDays();

        double result = calculateVacationPayForDays(vacationPayRequest.getSalary(), workingDays);

        return Math.round(result * 100.0) / 100.0;
    }

    @Override
    public double calculateVacationPayWithDate(VacationPayDatesRequest vacationPayRequest) {
        int workingDays = calculateWorkingDays(vacationPayRequest.getStartDate(),
                vacationPayRequest.getNumberOfVacationDays());

        double result = calculateVacationPayForDays(vacationPayRequest.getSalary(), workingDays);

        return Math.round(result * 100.0) / 100.0;
    }

    @Override
    public int calculateWorkingDays(LocalDate startDate, int numberOfVacationDays) {
        int workingDays = 0;

        LocalDate date = startDate;

        //Подсчитываем дату окончания отпуска
        LocalDate endDate = startDate.plusDays(numberOfVacationDays - 1);

        while (!date.isAfter(endDate)) {

            //Проверяем, не является ли текущий день выходным или праздником.
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY &&
                    date.getDayOfWeek() != DayOfWeek.SUNDAY &&
                    !addHolidays(startDate.getYear()).contains(date)) {
                workingDays++;
            }
            date = date.plusDays(1);
        }
        return workingDays;
    }

    //Добавляем праздники, которые не будут оплачиваться
    private static Set<LocalDate> addHolidays(int year) {
        Set<LocalDate> holidays = new HashSet<>();

        holidays.add(LocalDate.of(year, 1, 1));
        holidays.add(LocalDate.of(year, 1, 2));
        holidays.add(LocalDate.of(year, 1, 3));
        holidays.add(LocalDate.of(year, 1, 4));
        holidays.add(LocalDate.of(year, 1, 5));
        holidays.add(LocalDate.of(year, 1, 6));
        holidays.add(LocalDate.of(year, 1, 7));
        holidays.add(LocalDate.of(year, 1, 8));
        holidays.add(LocalDate.of(year, 1, 9));

        holidays.add(LocalDate.of(year, 2, 23));

        holidays.add(LocalDate.of(year, 3, 8));

        holidays.add(LocalDate.of(year, 5, 9));

        return holidays;
    }


}
