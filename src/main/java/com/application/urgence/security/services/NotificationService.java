package com.application.urgence.security.services;

import com.application.urgence.models.Notification;

public interface NotificationService {

    Notification creationNotif(Notification notification);

    String suppression(Long id);
}
