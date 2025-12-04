package com.example.spellcastingsystem.dal.implementation;

import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.constants.DatabaseConstants;
import com.example.spellcastingsystem.dal.ICrudDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.IO.println;

public class WizardDao implements ICrudDao<Wizard> {
    List<Wizard> wizards = new ArrayList<>();

    @Override
    public void add(Wizard item) {
        this.wizards.add(item);
    }

    @Override
    public void update(Wizard item) {
        Optional<Wizard> wizard = this.wizards.stream()
                .filter((x) -> x.id == item.id)
                .findFirst();
        if(wizard.isPresent()){
            wizard.get().name = item.name;
            wizard.get().level = item.level;
            return;
        }
        throw new RuntimeException("Wizard not found");

    }

    @Override
    public void delete(int id) {
        this.wizards.removeIf((x) -> x.id == id);
    }

    @Override
    public List<Wizard> getAll() {
        try{
            Connection con = DatabaseContext.connect();
            List<Wizard> wizards = new ArrayList<>(List.of());
            assert con != null;
            ResultSet rs = con.prepareStatement(DatabaseConstants.SelectAll(DatabaseConstants.WIZARDS_TABLE_NAME)).executeQuery();
            while(rs.next()){
                wizards.add(Wizard.fromDb(rs));
            }
            return wizards;
        } catch (SQLException e){
            println(e);
        }

        return null;
    }

    @Override
    public Wizard get(int id) {
        try {
            Connection con = DatabaseContext.connect();
            assert con != null;
            ResultSet rs = con.prepareStatement(DatabaseConstants.SelectById(DatabaseConstants.WIZARDS_TABLE_NAME, id))
                    .executeQuery();
            rs.next();
            return Wizard.fromDb(rs);
        } catch (SQLException ex) {
            println(ex);
        }
        return null;
    }
}
