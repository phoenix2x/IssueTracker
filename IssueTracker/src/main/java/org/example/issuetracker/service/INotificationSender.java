package org.example.issuetracker.service;

import org.example.issuetracker.domain.Notification;

public interface INotificationSender {
	
	void sendNotification(Notification notification);

}
