package com.realestate.dao;

import com.realestate.model.AbstractUser;
import java.util.List;

public interface UserDAO {
    int create(AbstractUser user) throws Exception;
    AbstractUser findById(int id) throws Exception;
    AbstractUser findByEmail(String email) throws Exception;
    List<AbstractUser> findAll() throws Exception;
    boolean delete(int id) throws Exception;
}
