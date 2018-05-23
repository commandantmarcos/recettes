package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Have;

import java.util.List;

public interface IHaveDAO {
    public Have create(Have new_have);
    public List<Have> findAll(int id);
    public void update(Have new_have);
    public void delete(Have old_have);
}
