package com.luno.ferreteria.service.ServiceImp;


import com.luno.ferreteria.dao.IUserDao;
import com.luno.ferreteria.dao.IUserProDAO;
import com.luno.ferreteria.dto.ChangePasswordRequestDTO;
import com.luno.ferreteria.dto.UserEditDTO;
import com.luno.ferreteria.entity.User;
import com.luno.ferreteria.entity.UserPro;
import com.luno.ferreteria.mappers.UserMapper;
import com.luno.ferreteria.role.Role;
import com.luno.ferreteria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    IUserDao userDao;

    @Autowired
    IUserProDAO userProDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<User> findUserPro() {

        return  userDao.findUserPro();
    }

    @Override
    public List<User> findAll(){
        return userDao.findAll();
    }

    @Override
    public User saveUser(User user){


        return userDao.save(user);
    }

    /**
     * Method for change the password of the user.
     * @param request the request with the userId, the current password and the new password.
     */
    @Override
    public void changePassword(ChangePasswordRequestDTO request){

        // Find the user in the database. If the user does not exist, throw an exception.
        User user = userDao.findById(request.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database"));

        // Check if the current password is correct. If not, throw an exception.
        if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
            throw new IllegalStateException("Wrong password");
        }

        // Check if the new password and the confirmation password are the same. If not, throw an exception.
        if(!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Password are not the same");
        }

        // Set the new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // Save the user in the database
        userDao.save(user);
    }

    @Override
    public String editUser(UserEditDTO user) {
        try {

            if (user.getRol() == Role.ROLE_PRO) {

                UserPro userPro = userProDao.findUserProByEmail(user.getEmail());
                userPro.setNombre(user.getFirstName());
                userPro.setUrlImg(user.getUrlImg());
                userPro.setEmail(user.getEmail());
                userPro.setCosto(user.getCosto());
                userProDao.save(userPro);
            }

            User userEdit = userDao.findByEmail(user.getEmail()).get();
            userEdit.setFirstName(user.getFirstName());
            userEdit.setLastName(user.getLastName());
            userEdit.setUrlImg(user.getUrlImg());
            userEdit.setEmail(user.getEmail());
            userDao.save(userEdit);

            return "Usuario Editado con Exito!";

        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }


}
