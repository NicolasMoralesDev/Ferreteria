package com.luno.ferreteria.service.ServiceImp;

import com.luno.ferreteria.dao.IUserProDAO;
import com.luno.ferreteria.entity.UserPro;
import com.luno.ferreteria.service.IUserPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProService implements IUserPro {

    @Autowired
    private IUserProDAO userDao;


    @Override
    public List<UserPro> getAllUserPro() {
       try {
           return userDao.findAll();
       } catch (Exception e){
           return null;
       }
    }

    @Override
    public UserPro findById(long id) {

        return userDao.findById(id).orElse(null);
    }

    @Override
    public UserPro findByEmail(String email) {
        return userDao.findUserProByEmail(email);
    }
}
