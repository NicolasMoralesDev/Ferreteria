package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IUserDao extends JpaRepository<User, Integer> {

    /**
     * Method for find a user by email.
     * @param email String, email of the user.
     * @return Optional<User>, the user if it exists.
     */
    Optional<User> findByEmail(String email);

    /**
     * Method for check if a user exists by email.
     * @param email String, email of the user.
     * @return boolean, true if the user exists.
     */
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = ROLE_PRO")
    List<User> findUserPro();


}
