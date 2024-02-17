package com.luno.ferreteria.service;


import com.luno.ferreteria.dto.ChangePasswordRequestDTO;
import com.luno.ferreteria.entity.User;

import java.util.List;

public interface UserService {

    public User findById(int id);

    public List<User> findUserPro();

    public User saveUser(User user);

    public List<User> findAll();

    /**
     * Method for change the password of the user.
     * @param changePasswordRequest the request with the userId, the current password and the new password.
     */
    public void changePassword(ChangePasswordRequestDTO changePasswordRequest);
}
