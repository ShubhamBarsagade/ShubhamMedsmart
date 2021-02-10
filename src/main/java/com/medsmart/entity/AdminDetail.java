package com.medsmart.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name = "admin_credential")
public class AdminDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 32, message = "User Name must be between 3 to 32")
	@Column(name = "name")
	private String adminName;

	@Size(min = 2, max = 20, message = "User Name must be between 2 to 20")
	@Column(name = "qualification")
	private String adminQualification;

	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
	@Column(name = "email")
	private String adminEmail;

	@Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number")
	@Column(name = "number")
	private String adminNumber;

	@Column(name = "created_date", columnDefinition = "TIMESTAMP")
	private Date adminDate;

	@NotBlank(message = "User Name can not  be empty !!")
	@Size(min = 4, max = 10, message = "User Name must be between 4 to 10")
	@Column(name = "admin_user")
	private String adminUser;

	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "The password confirmation does not match")
	@Column(name = "admin_password")
	private String adminPass;

	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "The password confirmation does not match")
	@Column(name = "admin_confirm_password")
	private String adminConfirmPass;
	
	@Column(name = "role")
	private String role;

	public AdminDetail() {

	}

	public AdminDetail(String adminName, String adminQualification, String adminEmail, String adminNumber,
			String adminUser, String adminPass) {
		super();
		this.adminName = adminName;
		this.adminQualification = adminQualification;
		this.adminEmail = adminEmail;
		this.adminNumber = adminNumber;
		this.adminUser = adminUser;
		this.adminPass = adminPass;
	}

	public String getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminQualification() {
		return adminQualification;
	}

	public void setAdminQualification(String adminQualification) {
		this.adminQualification = adminQualification;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminNumber() {
		return adminNumber;
	}

	public void setAdminNumber(String adminNumber) {
		this.adminNumber = adminNumber;
	}

	public Date getAdminDate() {
		return adminDate;
	}

	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
	}

	public String getAdminConfirmPass() {
		return adminConfirmPass;
	}

	public void setAdminConfirmPass(String adminConfirmPass) {
		this.adminConfirmPass = adminConfirmPass;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AdminDetail [id=" + id + ", adminName=" + adminName + ", adminQualification=" + adminQualification
				+ ", adminEmail=" + adminEmail + ", adminNumber=" + adminNumber + ", adminDate=" + adminDate
				+ ", adminUser=" + adminUser + ", adminPass=" + adminPass + ", adminConfirmPass=" + adminConfirmPass
				+ ", role=" + role + "]";
	}

}
