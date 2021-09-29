package com.victor.services;

import org.springframework.mail.SimpleMailMessage;

import com.victor.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
