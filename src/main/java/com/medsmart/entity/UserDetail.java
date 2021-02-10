package com.medsmart.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_credential")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 32, message = "User Name must be between 3 to 32")
	@Column(name = "name")
	private String userName;

	@Size(min = 2, max = 20, message = "User Name must be between 2 to 20")
	@Column(name = "qualification")
	private String userQualification;

	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
	@Column(name = "email")
	private String userEmail;

	@Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number")
	@Column(name = "number")
	private String userNumber;

	@Column(name = "created_date", columnDefinition = "TIMESTAMP")
	private Date userDate;

	@NotBlank(message = "User Name can not  be empty !!")
	@Size(min = 4, max = 10, message = "User Name must be between 4 to 10")
	@Column(name = "user_username")
	private String userUsername;
	
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Please enter Valid Password")
	@Column(name = "user_password")
	private String userPass;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "The password confirmation does not match")
	@Column(name = "user_confirm_password")
	private String userConfirmPass;
	
	@Column(name = "role")
	private String role;

	
	public UserDetail(String userName, String userQualification, String userEmail, String userNumber,
			String userUsername, String userPass) {
		super();
		this.userName = userName;
		this.userQualification = userQualification;
		this.userEmail = userEmail;
		this.userNumber = userNumber;
		this.userUsername = userUsername;
		this.userPass = userPass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserQualification() {
		return userQualification;
	}

	public void setUserQualification(String userQualification) {
		this.userQualification = userQualification;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserConfirmPass() {
		return userConfirmPass;
	}

	public void setUserConfirmPass(String userConfirmPass) {
		this.userConfirmPass = userConfirmPass;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserDetail() {

	}


	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", userName=" + userName + ", userQualification=" + userQualification
				+ ", userEmail=" + userEmail + ", userNumber=" + userNumber + ", userDate=" + userDate
				+ ", userUsername=" + userUsername + ", userPass=" + userPass + ", userConfirmPass=" + userConfirmPass
				+ ", role=" + role + "]";
	}
	
	

}
