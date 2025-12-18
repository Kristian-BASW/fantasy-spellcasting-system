package com.example.spellcastingsystem.dal.implementation;

import com.example.spellcastingsystem.be.WizardType;
import com.example.spellcastingsystem.be.constants.DatabaseConstants;
import com.example.spellcastingsystem.be.extenders.SQLQueryString;
import com.example.spellcastingsystem.dal.ICrudDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.lang.IO.println;

public class WizardTypeDao implements ICrudDao<WizardType> {

    public WizardTypeDao() {
        if(getAll().isEmpty()) {
            mockList();
        }
    }

    private void mockList(){
        var wizardTypes = List.of(
                new WizardType("Druid", List.of()),
                new WizardType("Necromancer", List.of())
        );
        for(WizardType wizardType : wizardTypes){
            this.add(wizardType);
        }
    }


    @Override
    public void add(WizardType item) {
        List<String> properties = new ArrayList<>();
        properties.add(WizardType.FieldNames.NAME);
        String sqlCreate = SQLQueryString.create(DatabaseConstants.WIZARD_TYPES_TABLE_NAME, properties).toString();
        try (var con = DatabaseContext.getConnection()){
            assert con != null;
            try (PreparedStatement ps = con.prepareStatement(sqlCreate)) {
                ps.setString(1, item.name);
                ps.executeUpdate();
            } catch (SQLException e){
                System.err.println(e.getMessage());
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(WizardType item) {
        List<String> properties = new ArrayList<>();
        properties.add(WizardType.FieldNames.NAME);
        String sqlUpdate = SQLQueryString.updateById(DatabaseConstants.WIZARD_TYPES_TABLE_NAME, item.id, properties).toString();

        try (var conn = DatabaseContext.getConnection()) {
            assert conn != null;
            // Create prepareStatement
            try (var pstmt = conn.prepareStatement(sqlUpdate)) {
                pstmt.setString(1, item.name);
                pstmt.executeUpdate();
                conn.close();
            }
            catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        // TODO: IMPLEMENTATION
    }

    @Override
    public List<WizardType> getAll() {
        try {
            List<WizardType> wizardTypes = new ArrayList<>();
            Connection con = DatabaseContext.getConnection();
            assert con != null;
            ResultSet rs = con.prepareStatement(SQLQueryString.selectAll(DatabaseConstants.WIZARD_TYPES_TABLE_NAME).toString()).executeQuery();
            while(rs.next()){
                wizardTypes.add(WizardType.fromDb(rs));
            }
            return wizardTypes;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public WizardType get(int id) {
        try {
            Connection con = DatabaseContext.getConnection();
            assert con != null;
            ResultSet rs = con.prepareStatement(SQLQueryString.selectById(DatabaseConstants.WIZARD_TYPES_TABLE_NAME, id).toString())
                    .executeQuery();
            rs.next();
            return WizardType.fromDb(rs);
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return  null;
    }
}
