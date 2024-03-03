package com.luno.ferreteria.service;

import com.luno.ferreteria.entity.UserPro;

import java.util.List;

public interface IUserPro {


    public List<UserPro> getAllUserPro();

    public UserPro findById(long id);

    public UserPro findByEmail(String email);

}
