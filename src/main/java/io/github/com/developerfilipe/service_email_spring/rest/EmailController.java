package io.github.com.developerfilipe.service_email_spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import io.github.com.developerfilipe.service_email_spring.dto.EmailDto;
import io.github.com.developerfilipe.service_email_spring.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService; 

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void enviarEmail(@RequestBody EmailDto emailDto) {
		emailService.enviaEmail(emailDto);
	}
}
