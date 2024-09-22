package com.example.demo.service;

import com.example.demo.model.Holidays;
import com.example.demo.model.VacationPayEntity;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class VacationPayCalculatorService {

    private final double averageNumberOfDaysInMonth=29.3;
    private final double NDFL =0.13;




    public BigDecimal  calculateVacationPay(BigDecimal salary,int weekends){
        BigDecimal  averageDailyEarnings=salary.divide(BigDecimal.valueOf(averageNumberOfDaysInMonth) ,2, RoundingMode.HALF_EVEN);

        BigDecimal totalVacationPay=averageDailyEarnings.multiply(BigDecimal.valueOf(weekends)).setScale(2);

        BigDecimal amountNdfl=totalVacationPay.multiply(BigDecimal.valueOf(NDFL)).setScale(2, RoundingMode.HALF_DOWN);

        BigDecimal totalAmount=totalVacationPay.subtract(amountNdfl).setScale(2);


        return totalAmount;



    }

    Predicate<LocalDate> holidays=Holidays.holidaysList::contains;
    int countDays;
    public int  calculatePayDays(int weekends, LocalDate startVacationDay){
        countDays= Math.toIntExact(Stream.iterate(startVacationDay, nextVacationDay -> nextVacationDay.plusDays(1))
                .limit(weekends)
                .filter(vacationDay -> !(holidays.test(vacationDay)))
                .filter(vacationDate -> !(vacationDate.getDayOfWeek() == DayOfWeek.SATURDAY || vacationDate.getDayOfWeek() == DayOfWeek.SUNDAY))
                .count());

        return countDays;


    }









}
