package com.aoua.medoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aoua.medoc.models.ERole;
import com.aoua.medoc.models.Role;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(ERole name);


  //creation des roles lors du run
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO roles (name) VALUES (\"ROLE_ADMIN\"),(\"ROLE_USER\");",nativeQuery = true)
  Void  creationRoleAdmin();

  //Attribuer role au user
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO   user_roles (user_id,role_id) VALUES (1,1);",nativeQuery = true)
  void addRoleToUser();
}

