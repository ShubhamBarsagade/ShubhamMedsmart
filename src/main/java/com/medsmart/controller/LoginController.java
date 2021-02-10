package com.medsmart.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.medsmart.entity.AdminDetail;
import com.medsmart.entity.Login;
import com.medsmart.entity.UserDetail;
import com.medsmart.service.EmailService;
import com.medsmart.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	HttpSession session;
	
	Random random = new Random(1000);
	
	@GetMapping("/login")
	public String login(Model model,Principal principal) {
		System.out.println("inside form");
		model.addAttribute("login", new Login());
		model.addAttribute("adminDetail", new AdminDetail());
		model.addAttribute("userDetail", new UserDetail());
		model.addAttribute("title","Login Here");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("param.logout","You have Successfully logged Out..!!");
		return "login";
	}
	
	@RequestMapping("/forgot")
	public String openEmailForm() {
		return "forgot_email_form";
	}
	
	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email) {
		System.out.println(email);
		
		//generating otp of 4 digit
		UserDetail user = userService.getUserDetailByUserEmail(email);
		
		//System.out.println("MyEmail"+user.getUserEmail());
		if(user!=null && user.getUserEmail()!=null && user.getUserEmail().equals(email)) {
			int otp = random.nextInt(999999);
			System.out.println(otp);
			
			//write code to send otp on email
			String message = ""
			                +"<div style='border:1px solid #e2e2e2; padding:20px'>"
					+"<h1>"
			                +"OTP is"
					        +"<b>"
			                +otp
			                +"</b>"
			                +"</h1>"
					        +"</div>";
			boolean flag = this.emailService.sendMail("OTP From SCM", message, email);
			if(flag) {
				session.setAttribute("myotp", otp);
				session.setAttribute("email", email);
				return "verify_otp";
			}else {
				session.setAttribute("message", "Check your Email id ..!!");
				return "forgot_email_form";
			}
			
		}else {
			session.setAttribute("message", "Your Email is not Registered ..!!");
			return "forgot_email_form";
		
		}
		
	}
	
	@PostMapping("/verify-otp")
	public String verifyOTP(@RequestParam("otp") int otp, HttpSession session) {
		int myOtp =(int) session.getAttribute("myotp");
		if(myOtp==otp) {
			return "password_change_form";
		}else {
			session.setAttribute("message", "You have entered wrong OTP..!!");
			return "verify_otp";
		}
		
	}
	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,HttpSession session) {
		String email = (String) session.getAttribute("email");
		UserDetail  user=userService.getUserDetailByUserEmail(email);
		if(newPassword.equals(confirmPassword)) {
			user.setUserPass(this.bCryptPasswordEncoder.encode(newPassword));
			user.setUserConfirmPass(confirmPassword);
			this.userService.saveUserDetail(user);
			return "redirect:/login?change=Your Password changed successfully..";
		}else {
			session.setAttribute("message", "New password and confirm password does not match..!!");
			return "password_change_form";
		}
		
	}
	
	@GetMapping("/registerUser")
	public String registerUser(Model model) {
		System.out.println("inside register user");
		model.addAttribute("title","Register User");
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
			userDetail.setUserPass(bCryptPasswordEncoder.encode(userDetail.getUserPass()));
			userDetail.setRole("ROLE_USER");
			userService.saveUserDetail(userDetail);
		    return "redirect:/login?change=You have successfully Registered";
		
		}
		
	}


}
