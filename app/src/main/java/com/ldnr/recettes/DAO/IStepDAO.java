package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Step;

import java.util.List;

public interface IStepDAO {
    public Step create(Step new_step);
    public Step find(int id);
    public Step find(String name);
    public List<Step> findAll(String name);
    public List<Step> findAll(int id);
    public List<Step> findAll();
    public void update(Step new_step);
    public void delete(Step old_step);
}
