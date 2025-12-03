package com.example.spellcastingsystem.bll;

import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.dal.ICrudDao;
import com.example.spellcastingsystem.dal.implementation.WizardDao;

import java.util.List;

public class WizardService {
    private ICrudDao<Wizard> _wizardDao;
    public WizardService(){
         _wizardDao = new WizardDao();
    }
    public Wizard getWizard(int id){
        return _wizardDao.get(id);
    }

    public void create(Wizard wizard){
        this._wizardDao.add(wizard);
    }

    public void update(Wizard wizard){
        this._wizardDao.add(wizard);
    }
    public void delete(int id){
        this._wizardDao.delete(id);
    }

    public List<Wizard> getWizards(){
        return _wizardDao.getAll();
    }

    public void levelUp(int id){
        var wizard = this._wizardDao.get(id);
        wizard.level += 1;
        this._wizardDao.update(wizard);
    }
}
