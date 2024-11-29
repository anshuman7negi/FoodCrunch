package com.samyati.user_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.samyati.user_management.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>   {

    Optional<User> findByemail(String email);

    Optional<User> findByname(String username);

    @Query(value = "Select * from USERS where name = ?1 or email = ?2",  nativeQuery = true)
    Optional<User> findByNameOREmail(String username, String email);

    
}
