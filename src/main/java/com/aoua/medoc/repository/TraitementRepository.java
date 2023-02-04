package com.aoua.medoc.repository;

import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Repository
public interface TraitementRepository extends JpaRepository <Traitement, Long> {

    List<Traitement> findByUser(User user);


}
