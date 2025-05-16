package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {
	void saveContact(ContactForm contactForm);

	Optional<Contact> findById(Long id);
	List<Contact> getAllContacts();
	
	public void saveContact(Contact contact);

	void deleteById(Long id);
	
	List<Contact> findAll();
}
