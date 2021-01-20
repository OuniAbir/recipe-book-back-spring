package com.recipesbook.Service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.recipesbook.Domain.NotificationEmail;
import com.recipesbook.Exception.RecipeBookException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
	
	private final JavaMailSender  mailSender ;
	private final MailContentBuilder mailContentBuilder ;
	
	public void sendMail(NotificationEmail  notificationEmail ) 
	{
		    MimeMessagePreparator  messagePreparator  =  mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);  
			messageHelper.setFrom("RecipeBook@email.com");
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setText(mailContentBuilder.Build(notificationEmail.getBody())); } ;
			
			try {
				mailSender.send(messagePreparator);
	            log.info("Activation email sent!!");
				
			} catch (MailException e) {
				throw new RecipeBookException("Exception occcured when sending mail to " + notificationEmail.getRecipient()) ; 			
			} 
	}
	
}
