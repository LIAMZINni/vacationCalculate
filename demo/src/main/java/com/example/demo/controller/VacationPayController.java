package com.example.demo.controller;

import com.example.demo.model.VacationPayEntity;
import com.example.demo.service.VacationPayCalculatorService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
@RestController
public class VacationPayController {

    @Autowired
    VacationPayCalculatorService vacationPayCalculatorService;
    @GetMapping("/calculate")
    public BigDecimal calculateVacationPay(@Valid @RequestBody VacationPayEntity vacationPayEntity){
        if (vacationPayEntity.getStartVacationDay() != null) {
            return vacationPayCalculatorService.calculateVacationPay(vacationPayEntity.getSalary(), vacationPayCalculatorService
                    .calculatePayDays(vacationPayEntity.getWeekends(), vacationPayEntity.getStartVacationDay()));

        }

        return  vacationPayCalculatorService.calculateVacationPay(vacationPayEntity.getSalary(), vacationPayEntity.getWeekends());

    }
}
