package com.website.mokshagarbatti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BaseController {

	@GetMapping("/adminpage")
	public String getAdminHomePage() {
		return "adminhome";
	}
	
	@GetMapping("/userpage")
	public String getUserHomePage() {
		return "userhome";
	}
	
	@GetMapping("/contactus")
	public String getContactPage() {
		return "contactus";
	}
	
	@GetMapping("/addproduct")
	public String getAddProductPage() {
		return "addproduct";
	}
	
	@GetMapping("/product")
	public String getProductPage() {
		return "product";
	}
	
	@GetMapping("/product?")
	public String getProductCartPage() {
		return "cart";
	}
	
	
	@GetMapping("/addadmin")
	public String getAddadminPage() {
		return "addadmin";
	}
	
	@GetMapping("/adduser")
	public String getAddUserPage() {
		return "adduser";
	}
	
	@GetMapping("/signup")
	public String getSignUpPage() {
		return "signup";
	}
	
	@GetMapping("/order")
	public String getAboutHomePage() {
		return "order";
	}
	
	@GetMapping("/")
	public String getLoginPage() {
		return "login";
	}
	
	@GetMapping("/forgot")
	public String getForgotPage() {
		return "forgot";
	}
	
	@GetMapping("/cart")
	public String getCartPage() {
		return "cart";
	}
	
	@GetMapping("/user")
	public String getAllUsersPage() {
		return "user";
	}
	
	@GetMapping("/aboutus")
	public String getAboutUsPage() {
		return "aboutus";
	}
	
	@GetMapping("/profile")
	public String getAdminProfilePage() {
		return "profile";
	}
	

	
	@GetMapping("/usernav.html")
	public String getUserNavPage() {
		return "usernav";
	}
	
	@GetMapping("/adminnav.html")
	public String getAdminNavPage() {
		return "adminnav";
	}
}
