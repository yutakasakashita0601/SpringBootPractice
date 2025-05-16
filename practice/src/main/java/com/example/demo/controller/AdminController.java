package com.example.demo.controller;

import java.sql.Timestamp;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Contact;
import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;
import com.example.demo.service.ContactService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/admin/signup")
	public String showSignupForm(Model model) {
		model.addAttribute("admin", new AdminForm());
		return "admin/signup";
	}
	
	@PostMapping("admin/signup")
	public String signup (@Validated @ModelAttribute AdminForm adminForm,
			              BindingResult result,
			              Model model) {
		if(result.hasErrors()) {
			return "admin/signup";
		}
		
		adminService.register(adminForm);
		return "redirect:/admin/signin";
	}
	
	@GetMapping("/admin/signin")
	public String signForm(Model model) {
		model.addAttribute("adminForm" , new AdminForm());
		return "admin/signin";
	}
	
	@PostMapping("/admin/signin")
	public String signin(@RequestParam String email,
	                     @RequestParam String password,
	                     Model model,
	                     HttpSession session) {
		Admin admin = adminService.findByEmail(email);
		
		if (admin == null || !passwordEncoder.matches(password, admin.getPassword())) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います");
			return "admin/signin";
		}
		
		session.setAttribute("admin", admin);
		return "redirect:/admin/contacts";
	}
	
	@GetMapping("/admin/contacts")
	public String showContactList(Model model) {
	    model.addAttribute("contactList", contactService.findAll());
	    return "admin/contacts";
	}
	
	@GetMapping("/admin/contacts/{id}")
	public String showContactDetail(@PathVariable Long id, Model model) {
		Contact contact = contactService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("お問い合わせが見つかりません(ID:" + id +")"));
		
		model.addAttribute("contact", contact);
		return "admin/contact_detail";
	}
	
	@GetMapping("/admin/contacts/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		Contact contact = contactService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("お問い合わせが見つかりません(ID:" + id + ")"));
		model.addAttribute("contact", contact);
		return "admin/contact_edit";
	}
	
	
	
	@PostMapping("/admin/contacts/{id}/edit")
	public String updateContact(@PathVariable Long id, @ModelAttribute Contact contact) {
		Contact existing = contactService.findById(id)
		    .orElseThrow(() -> new IllegalArgumentException("お問い合わせが見つかりません(ID:" + id + ")"));
		
		contact.setCreatedAt(existing.getCreatedAt());

		contact.setId(id);
		contact.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		contactService.saveContact(contact);
		return "redirect:/admin/contacts";
	}
	
	@PostMapping("/admin/contacts/{id}/delete")
	public String deleteContact(@PathVariable Long id) {
		contactService.deleteById(id);
		return "redirect:/admin/contacts";
	}
	
	@PostMapping("/admin/logout")
	public String logout(HttpSession session ) {
		session.invalidate();
		return "redirect:/admin/signin";
	}

}
