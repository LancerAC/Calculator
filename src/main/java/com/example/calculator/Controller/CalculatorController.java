package com.example.calculator.Controller;

import com.example.calculator.Exception.RequestError;
import com.example.calculator.dto.VacationPayDatesRequest;
import com.example.calculator.dto.VacationPayRequest;
import com.example.calculator.Service.Impl.VacationPayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final VacationPayServiceImpl vacationPayServiceImpl;

    @GetMapping("/calculate")
    public ResponseEntity<Double> getVacationPay
            (@Validated @RequestBody VacationPayRequest request) {

        return new ResponseEntity<>(vacationPayServiceImpl
                .calculateVacationPay(request),
                HttpStatus.OK);


    }

    @GetMapping("/calculate/dates")
    public ResponseEntity<Double> getVacationPayWithDates
            (@Validated @RequestBody VacationPayDatesRequest request){

        return new ResponseEntity<>(vacationPayServiceImpl
                .calculateVacationPayWithDate(request),
                HttpStatus.OK);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<RequestError> handleExceptions(BindException ex) {
        RequestError requestError = new RequestError(System.currentTimeMillis(),
                ex.getAllErrors().get(0).getDefaultMessage());

        return new ResponseEntity<>(requestError,
                HttpStatus.BAD_REQUEST);


    }
}

