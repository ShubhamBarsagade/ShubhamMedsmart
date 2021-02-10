package com.medsmart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsmart.dao.ExpenseRepository;
import com.medsmart.entity.ExpenseDate;
import com.medsmart.entity.Expenses;

@Service
public class ExpenseService {
	
	@Autowired
	ExpenseRepository repository;
	
	public void saveExpense(Expenses expenses) {
		System.out.println("inside saveExpense");
		repository.save(expenses);
		
	}

	public List<Expenses> getExpensesFromStartDateToEndDate(Date startDate, Date endDate) {
		
		List<Expenses> expenseList = repository.getExpensesFromStartDateToEndDate( startDate,  endDate);
		return expenseList;
	}

}
