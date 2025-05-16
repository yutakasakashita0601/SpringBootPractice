package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;

public interface AdminService {
	void register (AdminForm adminForm);
	
	boolean isEmailRegistered(String email);
	
	Admin findByEmail(String email);

}
