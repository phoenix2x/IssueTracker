package org.example.issuetracker.service.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.example.issuetracker.domain.Notification;
import org.example.issuetracker.service.INotificationSender;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MyMailSender implements INotificationSender {
	
	private static final Logger LOG = Logger.getLogger(MyMailSender.class);
	
	@Inject
	private MailSender mailSender;
	
	@Inject
    private SimpleMailMessage templateMessage;

	@Override
	public void sendNotification(Notification notification) {
		LOG.info(notification);
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(notification.getTo());
		msg.setText(notification.getMessage());
		LOG.info(msg);
		mailSender.send(msg);
	}

}
