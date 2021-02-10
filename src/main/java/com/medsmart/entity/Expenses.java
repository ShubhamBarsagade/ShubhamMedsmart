package com.medsmart.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="medsmart_expenses")
@Table(name="medsmart_expenses")
public class Expenses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Min(value=0 ,message="Opening Balance must be greater than Zero")
	@Max(value=10000000 ,message="Opening Balance must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "opening_balance")
	private long openingBalance;
	
	@NotNull
	@Min(value=0 ,message="Online Payment must be greater than Zero")
	@Max(value=10000000 ,message="Online Payment must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "online_payment")
	private long onlinePayment;
	
	@NotNull
	@Min(value=0 ,message="Cash Payment must be greater than Zero")
	@Max(value=10000000 ,message="Cash Payment must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "cash_payment")
	private long cashPayment;
	
	@NotNull
	@Min(value=0 ,message="RD1 must be greater than Zero")
	@Max(value=10000000 ,message="RD1 must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "rd_one")
	private long rdOne;
	
	@NotNull
	@Min(value=0 ,message="RD2 must be greater than Zero")
	@Max(value=10000000 ,message="RD2 must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "rd_two")
	private long rdTwo;
	
	@NotNull
	@Min(value=0 ,message="RD3 must be greater than Zero")
	@Max(value=10000000 ,message="RD3 must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "rd_three")
	private long rdThree;
	
	@NotNull
	@Min(value=0 ,message="RD4 must be greater than Zero")
	@Max(value=10000000 ,message="RD4 must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "rd_four")
	private long rdFour;
	
	@NotNull
	@Min(value=0 ,message="Receive Amount must be greater than Zero")
	@Max(value=10000000 ,message="Receive Amount must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "receive")
	private long receive;
	
	@NotNull
	@Min(value=0 ,message="Credit Amount Balance must be greater than Zero")
	@Max(value=10000000 ,message="Credit Amount must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "credit")
	private long credit;
	
	@NotNull
	@Min(value=0 ,message="Sales Return must be greater than Zero")
	@Max(value=10000000 ,message="Sales Return must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "sale_return")
	private long saleReturn;
	
	@NotNull
	@Min(value=0 ,message="Tea Amount must be greater than Zero")
	@Max(value=10000000 ,message="Tea Amount must be less than 1000000")
	@Digits(fraction =1000000,integer =10000000,message="Must be A number")	
	@Column(name = "tea")
	private long tea;
	
	@Size(min = 2,  message = "Remark should contain 10 words")
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "expense_date", columnDefinition = "TIMESTAMP")
	private Date expenseDate;
	
	public Expenses() {
		
	}

	public Expenses(Long openingBalance, Long onlinePayment, Long cashPayment, Long rdOne, Long rdTwo, Long rdThree,
			Long rdFour, Long receive, Long credit, Long saleReturn, Long tea, String remark, Date expenseDate) {
		this.openingBalance = openingBalance;
		this.onlinePayment = onlinePayment;
		this.cashPayment = cashPayment;
		this.rdOne = rdOne;
		this.rdTwo = rdTwo;
		this.rdThree = rdThree;
		this.rdFour = rdFour;
		this.receive = receive;
		this.credit = credit;
		this.saleReturn = saleReturn;
		this.tea = tea;
		this.remark = remark;
		this.expenseDate = expenseDate;
	}

	public Long getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Long openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Long getOnlinePayment() {
		return onlinePayment;
	}

	public void setOnlinePayment(Long onlinePayment) {
		this.onlinePayment = onlinePayment;
	}

	public Long getCashPayment() {
		return cashPayment;
	}

	public void setCashPayment(Long cashPayment) {
		this.cashPayment = cashPayment;
	}

	public Long getRdOne() {
		return rdOne;
	}

	public void setRdOne(Long rdOne) {
		this.rdOne = rdOne;
	}

	public Long getRdTwo() {
		return rdTwo;
	}

	public void setRdTwo(Long rdTwo) {
		this.rdTwo = rdTwo;
	}

	public Long getRdThree() {
		return rdThree;
	}

	public void setRdThree(Long rdThree) {
		this.rdThree = rdThree;
	}

	public Long getRdFour() {
		return rdFour;
	}

	public void setRdFour(Long rdFour) {
		this.rdFour = rdFour;
	}

	public Long getReceive() {
		return receive;
	}

	public void setReceive(Long receive) {
		this.receive = receive;
	}

	public Long getCredit() {
		return credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}

	public Long getSaleReturn() {
		return saleReturn;
	}

	public void setSaleReturn(Long saleReturn) {
		this.saleReturn = saleReturn;
	}

	public Long getTea() {
		return tea;
	}

	public void setTea(Long tea) {
		this.tea = tea;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	@Override
	public String toString() {
		return "Expenses [openingBalance=" + openingBalance + ", onlinePayment=" + onlinePayment + ", cashPayment="
				+ cashPayment + ", rdOne=" + rdOne + ", rdTwo=" + rdTwo + ", rdThree=" + rdThree + ", rdFour=" + rdFour
				+ ", receive=" + receive + ", credit=" + credit + ", saleReturn=" + saleReturn + ", tea=" + tea
				+ ", remark=" + remark + ", expenseDate=" + expenseDate + "]";
	}
	

}
