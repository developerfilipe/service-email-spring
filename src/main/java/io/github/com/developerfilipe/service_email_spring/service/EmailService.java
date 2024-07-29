package io.github.com.developerfilipe.service_email_spring.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import io.github.com.developerfilipe.service_email_spring.dto.EmailDto;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	

	private final JavaMailSender javaMailSender;
	

	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	} 
	
	
	/**
		*@Description - Envia email simples sem template
	**/
	public void enviaEmailSimples(EmailDto emailDto) {
		
		var message =  new SimpleMailMessage();
		//email de resposta no body
		message.setFrom(emailDto.emailResposta()); 
		//email no qual será enviado
		message.setTo(emailDto.enviarPara());
		//texto do subject
		message.setSubject(emailDto.subject());
		//texto do corpo do email
		message.setText(emailDto.texto());
		
		//envia o email 
		javaMailSender.send(message);

	}
	
	
	/**
	 *@Description - Envia email com template
	**/
	public void enviaEmail(EmailDto emailDto) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper  helper  = new MimeMessageHelper(message, true); 
			
			//helper.setFrom("noreply@email.com"); 
			helper.setFrom(emailDto.emailResposta() ); 
			helper.setSubject(emailDto.subject());
			helper.setTo(emailDto.enviarPara());
			
			String template = this.carregaTemplateEmail(); 
			
			
			
			template  = template.replace("#{tituloEmail}", emailDto.tituloEmail() );
		    template  = template.replace("#{pretensaoSalarial}", emailDto.pretensaoSalarial());
			template  = template.replace("#{nomeDaEmpresa}", emailDto.nomeDaEmpresa() );
			template =  template.replace("#{tipoVaga}", emailDto.tipoVaga() );
			
			helper.setText(template, true);
			javaMailSender.send(message);
		} catch (Exception e) {
			System.out.println("Falha ao enviar email: "+e.getMessage());
		}
	}
	
	
	/**
	 *@Description - Recupera o template
	**/
	private String carregaTemplateEmail() throws IOException {
		ClassPathResource resource = new ClassPathResource("templates/template-email.html");
		return  new String(resource.getInputStream().readAllBytes(),  StandardCharsets.UTF_8);
	}
	
	
	

}
