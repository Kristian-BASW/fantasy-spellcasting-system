package com.example.spellcastingsystem.bll;

import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.dal.ICrudDao;
import com.example.spellcastingsystem.dal.implementation.WizardDao;

import java.util.List;

import static java.lang.IO.println;

public class WizardService {
    private final ICrudDao<Wizard> _wizardCrudDao;
    private final WizardDao _wizardDao;
    public WizardService(){
         _wizardCrudDao = new WizardDao();
         _wizardDao = new WizardDao();
    }

    public Wizard getWizard(int id){
        return _wizardCrudDao.get(id);
    }

    /**
     * Creates a Wizard if the wizard name is not used
     * @param wizard Wizard to create
     * @return true if the Wizard is created
     */
    public boolean create(Wizard wizard){
        if(existsName(wizard.name) == null){
            this._wizardCrudDao.add(wizard);
            return true;
        }
        return false;
    }

    public void update(Wizard wizard){
        this._wizardCrudDao.update(wizard);
    }
    public void delete(int id){
        this._wizardCrudDao.delete(id);
    }

    public List<Wizard> getWizards(){
        return _wizardCrudDao.getAll();
    }

    public Wizard existsName(String name){
        return _wizardDao.existsName(name);
    }

    public Wizard getByName(String name){
        return _wizardDao.getByName(name);
    }

    public void levelUp(int id) {
        var wizard = this._wizardCrudDao.get(id);
        wizard.level += 1;
        this._wizardCrudDao.update(wizard);
    }
}
