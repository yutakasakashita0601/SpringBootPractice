package com.example.demo.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void register(AdminForm adminForm) {
		Admin admin = new Admin();
		admin.setLastName(adminForm.getLastName());
		admin.setFirstName(adminForm.getFirstName());
		admin.setEmail(adminForm.getEmail());
		String encodePassword = passwordEncoder.encode(adminForm.getPassword());
		admin.setPassword(encodePassword);
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		admin.setCreatedAt(now);
		admin.setUpdated_at(now);
		
		adminRepository.save(admin);
		
	}

	@Override
	public boolean isEmailRegistered(String email) {
		return adminRepository.findByEmail(email) !=null;
	}
	
	@Override
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	

}
