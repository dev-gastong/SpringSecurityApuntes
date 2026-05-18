package com.SpringSecurity.Curso.repository;

import com.SpringSecurity.Curso.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername (String username);

    //@Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    //Optional<UserEntity> findUser (String user);

}
