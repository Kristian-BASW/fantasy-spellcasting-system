package com.example.spellcastingsystem.dal.implementation;

import com.example.spellcastingsystem.be.Wizard;
import com.example.spellcastingsystem.be.constants.DatabaseConstants;
import com.example.spellcastingsystem.be.extenders.SQLQueryString;
import com.example.spellcastingsystem.dal.ICrudDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.lang.IO.println;

public class WizardDao implements ICrudDao<Wizard> {

    /**
     * Empty constructor
     */
    public WizardDao() {
        if(getAll().isEmpty()) {
            mockList();
        }
    }

    /**
     * Mocks a list of Wizards into the database
     */
    private void mockList(){
        add(new Wizard("Tim the mighty Wizard", 1));
        add(new Wizard("John the goofy Wizard", 2));
    }

    @Override
    public void add(Wizard item) {
        List<String> properties = new ArrayList<>();
        properties.add(Wizard.FieldNames.NAME);
        properties.add(Wizard.FieldNames.WIZARD_TYPE_ID);
        properties.add(Wizard.FieldNames.EXPERIENCE);
        properties.add(Wizard.FieldNames.LEVEL);
        String sqlCreate = SQLQueryString.create(DatabaseConstants.WIZARDS_TABLE_NAME, properties).toString();
        try (var con = DatabaseContext.getConnection()){
            assert con != null;
            try (PreparedStatement ps = con.prepareStatement(sqlCreate)) {
                ps.setString(1, item.name);
                ps.setInt(2, item.wizardTypeId);
                ps.setInt(3, item.level);
                ps.setInt(4, item.experience);
                ps.executeUpdate();
            } catch (SQLException e){
                System.err.println(e.getMessage());
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Wizard item) {
        List<String> properties = new ArrayList<>();
        properties.add(Wizard.FieldNames.NAME);
        properties.add(Wizard.FieldNames.EXPERIENCE);
        properties.add(Wizard.FieldNames.LEVEL);
        String sqlUpdate = SQLQueryString.create(DatabaseConstants.WIZARDS_TABLE_NAME, properties).toString();

        try (Connection conn = DatabaseContext.getConnection()) {
            assert conn != null;
            // Create prepareStatement
            try (PreparedStatement pstmt = DatabaseContext.getConnection().prepareStatement(sqlUpdate)) {
                pstmt.setString(1, item.name);
                pstmt.setInt(2, item.experience);
                pstmt.setInt(3, item.level);
                pstmt.executeUpdate();
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
        // TODO: IMPLEMNTATION
    }

    @Override
    public List<Wizard> getAll() {
        try (var con = DatabaseContext.getConnection()) {
            List<Wizard> wizards = new ArrayList<>(List.of());
            assert con != null;
            ResultSet rs = con.prepareStatement(SQLQueryString.selectAll(DatabaseConstants.WIZARDS_TABLE_NAME).toString()).executeQuery();
            while(rs.next()){
                wizards.add(Wizard.fromDb(rs));
            }
            rs.close();
            return wizards;
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Wizard get(int id) {
        try {
            Connection con = DatabaseContext.getConnection();
            assert con != null;
            ResultSet rs = con.prepareStatement(SQLQueryString.selectById(DatabaseConstants.WIZARDS_TABLE_NAME, id).toString())
                    .executeQuery();
            rs.next();
            return Wizard.fromDb(rs);
        } catch (SQLException ex) {
            println(ex);
        }
        return null;
    }

    /**
     * Checks if there is a Wizard with the given name
     * @param name Name to check
     * @return Returns the Wizard with given name
     * @throws SQLException
     */
    public Wizard existsName(String name)  {
        try{
            Connection con = DatabaseContext.getConnection();
            assert con != null;
            List<String> columnNames = new ArrayList<>();
            columnNames.add(Wizard.FieldNames.NAME);
            PreparedStatement pstmnt = con.prepareStatement(SQLQueryString.selectWhereEqual(DatabaseConstants.WIZARDS_TABLE_NAME, columnNames).toString());
            pstmnt.setString(1, name);
            ResultSet rs = pstmnt.executeQuery();

            if(rs.next()){
                return Wizard.fromDb(rs);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Wizard getByName(String name) {
        try {
            Connection con = DatabaseContext.getConnection();
            assert con != null;
            List<String> columnNames = new ArrayList<>();
            columnNames.add(Wizard.FieldNames.NAME);
            var pstmt = con.prepareStatement(SQLQueryString.selectWhereEqual(DatabaseConstants.WIZARDS_TABLE_NAME, columnNames).toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return Wizard.fromDb(rs);
        } catch (SQLException ex) {
            println(ex);
        }
        return null;
    }
}
