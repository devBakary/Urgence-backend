package com.application.urgence.security.services;

import com.application.urgence.models.Notification;
import com.application.urgence.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification creationNotif(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public String suppression(Long id) {
        return null;
    }
}
