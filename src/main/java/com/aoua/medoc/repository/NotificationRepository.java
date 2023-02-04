package com.aoua.medoc.repository;

import com.aoua.medoc.models.Notification;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends JpaRepository <Notification, Long> {

    List<Notification> findByDateAndUserAndTraitement(LocalDate date, User user, Traitement traitement);
}
