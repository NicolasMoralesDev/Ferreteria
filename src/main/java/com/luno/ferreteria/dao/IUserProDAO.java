package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.User;
import com.luno.ferreteria.entity.UserPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserProDAO extends JpaRepository<UserPro, Long> {

    @Query("SELECT u FROM UserPro u WHERE u.email = :email")
    UserPro findUserProByEmail(@Param("email") String email);
}
