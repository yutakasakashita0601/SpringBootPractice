package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();
		
		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		contact.setCreatedAt(now);
		contact.setUpdatedAt(now);
		
		contactRepository.save(contact);

	}
	
	@Override
	public Optional<Contact> findById(Long id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}
	
	public List<Contact> findAll() {
        return contactRepository.findAll();
	}
	
	@Override
	public void saveContact(Contact contact) {
		contactRepository.save(contact);
	}
	
	@Override
	public void deleteById(Long id) {
		contactRepository.deleteById(id);
	}

}
