package com.medsmart.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medsmart.entity.AdminDetail;
import com.medsmart.entity.Expenses;
import com.medsmart.entity.Login;
import com.medsmart.entity.UserDetail;
import com.medsmart.service.AdminService;
import com.medsmart.service.ExpenseService;
import com.medsmart.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired 
	AdminService adminService;
	
	@Autowired
	UserService userService;
	

	@Autowired
	ExpenseService expenseService;
	
	@RequestMapping("/index")
	public String dashboard() {
		
		return "user_dashboard";
	}
	
	public Model allCommonData(Model model, Principal principal) {
		UserDetail user = userService.getUserByUserUsername(principal.getName());
		System.out.println("user is " +user);
		model.addAttribute("user", user);
		model.addAttribute("param.logout", "You Have SuccessFully logged out..!!");
		return model;
		
	}

	@GetMapping("/userHome")
	public String userHome(Model model,Principal principal) throws ParseException {
		model= allCommonData( model, principal);
		model.addAttribute("title","Home");
		
		return "user-home";
	}
	
	@GetMapping("/registerUser")
	public String registerUser(Model model) {
		System.out.println("inside register user");
		model.addAttribute("title","Register Admin");
		model.addAttribute("userDetail", new UserDetail());
		return "register-user";
	}
	
	@PostMapping("/registerUser")
	public String processForm(@Valid @ModelAttribute("userDetail") UserDetail userDetail,
			BindingResult bindingResult,ModelMap modelMap , Model model) {
        System.out.println(" the User details are :"+ userDetail);
        if (bindingResult.hasErrors()) {
			System.out.println("inside error");
			System.out.println(bindingResult);
			return "register-user";
		}else if(userService.getUserByUserUsername(userDetail.getUserUsername()) != null){
			System.out.println("inside login user present");
			modelMap.addAttribute("bindingResult", bindingResult);
			modelMap.addAttribute("message", "Username Already Present");
			return "register-user";
		}else if(!(userDetail.getUserPass()).equalsIgnoreCase(userDetail.getUserConfirmPass())){
			System.out.println("inside login user present");
			modelMap.addAttribute("bindingResult", bindingResult);
			modelMap.addAttribute("message", "The password confirmation does not match");
			return "register-user";
		}else {
        
			Timestamp date = new Timestamp(new Date().getTime());
			System.out.println(date);
			userDetail.setUserDate(date);
			userDetail.setUserPass(passwordEncoder.encode(userDetail.getUserPass()));
			userDetail.setRole("ROLE_USER");
			userService.saveUserDetail(userDetail);
		    return "redirect:/login?change=You have successfully Registered";
		
		}
		
	}
	
	@GetMapping("/addExpenses")
	public String addExpenses(Model model,Principal principal) {
		
		model= allCommonData( model, principal);
		UserDetail user = userService.getUserByUserUsername(principal.getName());
		model.addAttribute("title","Add Expenses");
		model.addAttribute("expenses", new Expenses());

		if(user.getRole().equals("ROLE_USER")) {
		return "add-expenses";
		}else {
			return "admin-add-expenses";
		}
	}
	
	@PostMapping("/addExpenses")
	public String addPostExpenses(@Valid @ModelAttribute("expenses") Expenses expenses,
			BindingResult bindingResult,ModelMap modelMap , Model model,Principal principal) {
		model= allCommonData( model, principal);
		
		 System.out.println(" the Expense details are :"+ expenses);
		 UserDetail user = userService.getUserByUserUsername(principal.getName());
	        if (bindingResult.hasErrors()) {
				System.out.println("inside error");
				System.out.println(bindingResult);
				
				if(user.getRole().equals("ROLE_USER")) {
				return "add-expenses";
				}else {
					return "admin-add-expenses";
				}
			}else {
				Timestamp date = new Timestamp(new Date().getTime());
				expenses.setExpenseDate(date);
				expenseService.saveExpense(expenses);
				model.addAttribute("success", "You have saved your expenses successfully");
				if(user.getRole().equals("ROLE_USER")) {
		         return "add-expenses";
				}else {
					return "admin-add-expenses";
				}
		         
			}
	}
}
