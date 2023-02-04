package com.aoua.medoc.ServiceImplement;

import com.aoua.medoc.models.Notification;
import com.aoua.medoc.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class NotificationImplement {
    @Autowired
    private static NotificationRepository notificationRepository;

    public static void sendNotification() {

        LocalTime now = LocalTime.now();
        LocalTime desiredTime = LocalTime.of(19, 41);

        if (now.equals(desiredTime)) {
            Notification notification = new Notification();
            notification.setTitre("Rappel");
            notification.setMessage("Veuillez prendre votre medicament : ");
            notificationRepository.save(notification);
        }
    }
//    long duree_traitement = traitement.getDuree_traitement();



}
