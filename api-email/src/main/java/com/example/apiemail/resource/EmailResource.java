package com.example.apiemail.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiemail.model.Email;
import com.example.apiemail.service.EmailService;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public ResponseEntity<String> sendEmail(@RequestBody Email email){
		emailService.sendEmail(email);
		return ResponseEntity.created(null).body("Email enviado com sucesso!");
	}

}
