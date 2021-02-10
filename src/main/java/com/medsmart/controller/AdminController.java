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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medsmart.service.AdminService;
import com.medsmart.service.ExpenseService;
import com.medsmart.service.UserService;
import com.medsmart.entity.AdminDetail;
import com.medsmart.entity.ExpenseDate;
import com.medsmart.entity.Expenses;
import com.medsmart.entity.Login;
import com.medsmart.entity.UserDetail;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired 
	AdminService adminService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ExpenseService expenseService;
	
	
	@GetMapping("/adminHome")
	public String adminHome(Model model, Principal principal) {
		System.out.println("inside form");
		UserDetail user = userService.getUserByUserUsername(principal.getName());
		System.out.println("user is " +user);
		model.addAttribute("user", user);
		model.addAttribute("title","Home");
		return "admin-home";
	}
	
	@GetMapping("/registerAdmin")
	public String registerAdmin(Model model,Principal principal) {
		System.out.println("inside form");
		model=allData(model, principal);
		model.addAttribute("title","Register Admin");
		model.addAttribute("userDetail", new UserDetail());
		return "register-admin";
	}
	
	@PostMapping("/registerAdmin")
	public String registerAdmin(@Valid @ModelAttribute("userDetail") UserDetail userDetail,
			BindingResult bindingResult,ModelMap modelMap , Model model,Principal principal) {
		model=allData(model, principal);
		System.out.println(" the User details are :"+ userDetail);
        if (bindingResult.hasErrors()) {
			System.out.println("inside error");
			System.out.println(bindingResult);
			return "register-admin";
		}else if(userService.getUserByUserUsername(userDetail.getUserUsername()) != null){
			System.out.println("inside login user present");
			modelMap.addAttribute("bindingResult", bindingResult);
			modelMap.addAttribute("message", "Username Already Present");
			return "register-admin";
		}else if(!(userDetail.getUserPass()).equalsIgnoreCase(userDetail.getUserConfirmPass())){
			System.out.println("inside login user present");
			modelMap.addAttribute("bindingResult", bindingResult);
			modelMap.addAttribute("message", "The password confirmation does not match");
			return "register-admin";
		}else {
        
			Timestamp date = new Timestamp(new Date().getTime());
			System.out.println(date);
			userDetail.setUserDate(date);
			userDetail.setUserPass(passwordEncoder.encode(userDetail.getUserPass()));
			userDetail.setRole("ROLE_ADMIN");
			userService.saveUserDetail(userDetail);
			model.addAttribute("success", "You have successfully Registered ");
		    return "register-admin";
		
		}
	}

	private Model allData(Model model, Principal principal) {
		UserDetail user = userService.getUserByUserUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("param.logout", "You Have SuccessFully logged out..!!");
		return model;
	}
	
	@GetMapping("/viewExpenses")
	public String viewFormExpenses(Model model,Principal principal) {
		System.out.println("inside form");
		model=allData(model, principal);
		model.addAttribute("title","View Expenses");
		model.addAttribute("userDetail", new UserDetail());
		return "view-expenses";
	}
	
	@PostMapping("/viewExpenses")
	@ResponseBody
	public List <Expenses> viewExpenses(@RequestBody ExpenseDate expenseDate ) throws ParseException {
		System.out.println("inside form");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(expenseDate.getStartDate()  +"    "+expenseDate.getEndDate());
		
		System.out.println("expense start date "+ sdf.parse(expenseDate.getStartDate())+ "  expense endDate "+sdf.parse(expenseDate.getEndDate()));
		
		List <Expenses> expenseDateList = expenseService.getExpensesFromStartDateToEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(expenseDate.getStartDate()),new SimpleDateFormat("yyyy-MM-dd").parse(expenseDate.getEndDate()));
		
		return expenseDateList;
	}
	
	
	/*
	 * @GetMapping("/home") public String home(Model model) {
	 * System.out.println("inside form"); model.addAttribute("adminDetail", new
	 * AdminDetail()); return "home"; }
	 * 
	 * 
	 * @GetMapping("/form") public String openForm(Model model) {
	 * System.out.println("inside form"); model.addAttribute("adminDetail", new
	 * AdminDetail()); return "form"; }
	 * 
	 * @GetMapping("/user") public String openUser(Model model) {
	 * System.out.println("inside form"); model.addAttribute("userDetail", new
	 * UserDetail()); return "user"; }
	 */
	
	
	/*
	 * @PostMapping("/loginAdmin") public String
	 * login(@Valid @ModelAttribute("adminDetail") AdminDetail adminDetail,
	 * BindingResult bindingResult,ModelMap modelMap) {
	 * System.out.println(" the Admin details are :"+ adminDetail); if
	 * (bindingResult.hasErrors()) { System.out.println("inside error");
	 * System.out.println(bindingResult); return "form";
	 * 
	 * } else if(adminService.getAdminByUser(adminDetail.getAdminUser()) == null){
	 * System.out.println("inside login user present");
	 * modelMap.addAttribute("bindingResult", bindingResult);
	 * modelMap.addAttribute("message", "Invalid Username"); return "form"; }else
	 * if(adminService.getAdminByUserAndPassword(adminDetail.getAdminUser(),
	 * adminDetail.getAdminPass())==null){ modelMap.addAttribute("check password",
	 * bindingResult); modelMap.addAttribute("message",
	 * "Invalid Username or Password"); return "form"; }else {
	 * System.out.println(adminDetail); return "admin-home"; } }
	 */
	
	/*
	 * @PostMapping("/login") public String login(@Valid @ModelAttribute("login")
	 * Login login, BindingResult bindingResult,ModelMap modelMap) {
	 * System.out.println(" the Admin details are :"+ login); if
	 * (bindingResult.hasErrors()) { System.out.println("inside error");
	 * System.out.println(bindingResult); return "login";
	 * 
	 * } else if((adminService.getAdminByUser(login.getUsername()) == null ) &&
	 * (userService.getUserByUserUsername(login.getUsername())==null)){
	 * System.out.println("inside login user present");
	 * modelMap.addAttribute("bindingResult", bindingResult);
	 * modelMap.addAttribute("message", "Invalid Username"); return "login"; }else
	 * if((adminService.getAdminByUserAndPassword(login.getUsername(),login.
	 * getPassword())==null) &&
	 * (userService.getUserByUserUsernameAndUserPass(login.getUsername(),login.
	 * getPassword())==null)){ System.out.println("inside getpass");
	 * modelMap.addAttribute("check password", bindingResult);
	 * modelMap.addAttribute("message", "Invalid Username or Password"); return
	 * "login"; }else { System.out.println("inside else"); AdminDetail adminDetail =
	 * adminService.getAdminByUserAndPassword(login.getUsername(),login.getPassword(
	 * )); UserDetail userDetail =
	 * userService.getUserByUserUsernameAndUserPass(login.getUsername(),login.
	 * getPassword()); System.out.println(adminDetail);
	 * System.out.println(userDetail); if(adminDetail != null &&
	 * adminDetail.getAdminUser() !=null &&
	 * (login.getUsername().equals(adminDetail.getAdminUser()))&&(login.getPassword(
	 * ).equals(adminDetail.getAdminPass()))) { System.out.println("inside admin");
	 * return "admin-home"; }else if(userDetail!=null &&
	 * userDetail.getUserUsername()!=null
	 * &&(login.getPassword().equals(userDetail.getUserUsername()))&&(login.
	 * getPassword().equals(userDetail.getUserPass()))) {
	 * System.out.println("inside user"); System.out.println(userDetail); return
	 * "user-home"; }else { modelMap.addAttribute("check password", bindingResult);
	 * modelMap.addAttribute("message", "Invalid Username or Password"); return
	 * "login"; } } }
	 */

	
	
	/*
	 * @PostMapping("/loginUser") public String
	 * loginUser(@Valid @ModelAttribute("userDetail") UserDetail userDetail,
	 * BindingResult bindingResult,ModelMap modelMap) {
	 * System.out.println(" the Admin details are :"+ userDetail); if
	 * (bindingResult.hasErrors()) { System.out.println("inside error user");
	 * System.out.println(bindingResult); return "user";
	 * 
	 * } else if(userService.getUserByUserUsername(userDetail.getUserUsername()) ==
	 * null){ System.out.println("inside login user present");
	 * modelMap.addAttribute("bindingResult", bindingResult);
	 * modelMap.addAttribute("message", "Invalid Username"); return "user"; }else
	 * if(userService.getUserByUserUsernameAndUserPass(userDetail.getUserUsername(),
	 * userDetail.getUserPass())==null){ modelMap.addAttribute("check password",
	 * bindingResult); modelMap.addAttribute("message",
	 * "Invalid Username or Password"); return "user"; }else {
	 * System.out.println(userDetail); return "user-home"; } }
	 */
	
}
