package com.medsmart.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medsmart.entity.ExpenseDate;
import com.medsmart.entity.Expenses;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses,Long>{
	
	
	@Query("SELECT e FROM medsmart_expenses e WHERE e.expenseDate between  ?1 and ?2")
	List<Expenses> getExpensesFromStartDateToEndDate(Date startDate, Date endDate);

}
