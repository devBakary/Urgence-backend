package com.application.urgence.repository;

import com.application.urgence.models.Notification;
import com.application.urgence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value="select * from notification where etat = 0 ORDER BY id DESC ", nativeQuery = true)
    List<Notification> listeNotif();
}
