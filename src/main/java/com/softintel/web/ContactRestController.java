package com.softintel.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softintel.dao.ContactRepository;
import com.softintel.dtos.ContactDto;
import com.softintel.entities.Contact;

@RestController
public class ContactRestController {
	
	@Autowired
	private ContactRepository contactRepository;

	@GetMapping("/contacts")
	public List<Contact>getAllContacts(){
		return contactRepository.findAll();
	}
	
	@GetMapping("/contacts/{id}")
	public Contact getContactById(@PathVariable Long id) {
		return contactRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No contact found with id "+id));
	}
	
	@GetMapping("/contacts/search")
	public Page<Contact>searchContacts(
			@RequestParam(name = "keyword", defaultValue = "")String keyword,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size){
		return contactRepository.search("%"+keyword+"%", PageRequest.of(page, size));
	}
	
	@PostMapping("/contacts")
	public Contact save(@RequestBody @Valid ContactDto contactDto) {
		Contact c = contactDto.mapToEntity.get();
		return contactRepository.save(c);
	}
	
	@DeleteMapping("/contacts/{id}")
	public boolean deleteContact(@PathVariable Long id) {
		contactRepository.deleteById(id);
		return true;
	}
	
	@PutMapping("/contacts/{id}")
	public Contact update(@PathVariable Long id,@RequestBody Contact contact) {
		contact.setId(id);
		return contactRepository.save(contact);
	}
}
