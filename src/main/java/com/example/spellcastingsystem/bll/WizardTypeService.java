package com.example.spellcastingsystem.bll;

import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.WizardType;
import com.example.spellcastingsystem.dal.implementation.WizardTypeDao;

import java.util.List;

public class WizardTypeService {
    public WizardTypeDao dao;

    /**
     * Constructor for WizardTypeService
     */
    public WizardTypeService() {
        dao = new WizardTypeDao();
    }

    /**
     * Gets all of the Wizard types
     */
    public List<WizardType> getAll(){
        return dao.getAll();
    }

    /**
     * Gets all names of WizardTypes
     * @return
     */
    public List<String> getNames() {
        var list = dao.getAll();
        return list.stream().map(x -> x.name).toList();
    }

}
