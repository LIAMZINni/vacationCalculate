package com.example.demo.service;

import com.example.demo.model.VacationPayEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class VacationCalculatorApplicationTest {
	@Autowired
	VacationPayCalculatorService vacationPayCalculatorService;

	@Test
	@DisplayName("test vacation calculator with date")
	public void testVacationCalculatorWithDate(){
		VacationPayEntity vacationPayEntity=new VacationPayEntity(14,new BigDecimal(30000),LocalDate.of(2024,05,02));
		BigDecimal actualResult=vacationPayCalculatorService.calculateVacationPay(
				vacationPayEntity.getSalary()
				, vacationPayCalculatorService.calculatePayDays(vacationPayEntity.getWeekends(),vacationPayEntity.getStartVacationDay()));
		Assertions.assertThat(actualResult).isEqualByComparingTo(String.valueOf(8017.06));



	}




	@Test
	@DisplayName("test vacation calculator ")
	public void  testVacationCalculator(){
		BigDecimal actualResult =vacationPayCalculatorService.calculateVacationPay(new BigDecimal(30000),14);
		Assertions.assertThat(actualResult).isEqualByComparingTo(String.valueOf(12470.98));
	}
	void contextLoads() {
	}

}
