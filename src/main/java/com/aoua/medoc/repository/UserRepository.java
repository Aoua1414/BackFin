package com.aoua.medoc.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.aoua.medoc.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aoua.medoc.models.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);


//  Role RecupererRoleUser(User user);

  //List<User> findByRole(Role role);
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO users (email, numero, password, username) " +
          "VALUES (\"amidou@gmail.com\", \"77777777\", :password, \"Amidou\");",
          nativeQuery = true)
  Void adminConnect(String password);
}


