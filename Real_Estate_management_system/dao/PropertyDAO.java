package com.realestate.dao;

import com.realestate.model.Property;
import java.util.List;

public interface PropertyDAO {
    int create(Property property) throws Exception;
    Property findById(int id) throws Exception;
    List<Property> findAll() throws Exception;
    boolean update(Property property) throws Exception;
    boolean delete(int id) throws Exception;
}
