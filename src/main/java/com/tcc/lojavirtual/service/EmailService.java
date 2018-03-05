package com.tcc.lojavirtual.service;

import org.springframework.mail.SimpleMailMessage;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.domain.Pedido;
/* Padrão strategy e method template para envio de e-mail Real e Teste */
/* Quando Profile teste está ativado, é injetado o Bean da classe ProfileTestConfig (MockEmailService , 
 * quando Dev é ativado, injeta Bean da Classe ProfileDevConfig (SmtpEmailService)  */

public interface EmailService {
	void sendConfirmationPedidoEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
