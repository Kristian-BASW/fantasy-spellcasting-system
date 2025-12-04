package com.example.spellcastingsystem.dal.implementation;

import com.example.spellcastingsystem.be.Spell;
import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.WizardType;
import com.example.spellcastingsystem.be.constants.DatabaseConstants;
import com.example.spellcastingsystem.dal.ICrudDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.IO.println;

public class WizardTypeDao implements ICrudDao<WizardType> {
    private List<WizardType> wizardTypes;

    public WizardTypeDao() {
        wizardTypes = new ArrayList<>();
        mockList();
    }

    private void mockList(){
        wizardTypes = List.of(
                new WizardType("Druid", List.of()),
                new WizardType("Necromancer", List.of())
        );
    }


    @Override
    public void add(WizardType item) {
        wizardTypes.add(item);
    }

    @Override
    public void update(WizardType item) {
        Optional<WizardType> wizardType = this.wizardTypes.stream()
                .filter((x) -> Objects.equals(x.id, item.id))
                .findFirst();
        if(wizardType.isPresent()){
            wizardType.get().name = item.name;
            wizardType.get().initialSpells = item.initialSpells;
            return;
        }
        throw new RuntimeException("Wizard not found");
    }

    @Override
    public void delete(int id) {
        wizardTypes.removeIf((x) -> Objects.equals(x.id, id));
    }

    @Override
    public List<WizardType> getAll() {
        try {
            List<WizardType> wizardTypes = new ArrayList<>();
            Connection con = DatabaseContext.connect();
            assert con != null;
            ResultSet rs = con.prepareStatement(DatabaseConstants.SelectAll(DatabaseConstants.WIZARD_TYPES_TABLE_NAME)).executeQuery();
            while(rs.next()){
                wizardTypes.add(WizardType.fromDb(rs));
            }
            return wizardTypes;
        } catch (SQLException e) {
            println(e.getMessage());
        }
        return null;
    }

    @Override
    public WizardType get(int id) {
        try {
            Connection con = DatabaseContext.connect();
            assert con != null;
            ResultSet rs = con.prepareStatement(DatabaseConstants.SelectById(DatabaseConstants.WIZARD_TYPES_TABLE_NAME, id))
                    .executeQuery();
            rs.next();
            return WizardType.fromDb(rs);
        } catch(SQLException ex){
            println(ex);
        }
        return  null;
    }
}
