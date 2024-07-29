package io.github.com.developerfilipe.service_email_spring.dto;

public record EmailDto( String pretensaoSalarial,
		String enviarPara, 
		String nomeDaEmpresa,
		String tituloEmail,
		String tipoVaga,
		String emailResposta,
		String subject, 
		String texto
		) {

}
