package com.dh.beTFI.dentalPractices.repository.dentistRepository;

import com.dh.beTFI.dentalPractices.repository.IRepository;
import com.dh.beTFI.dentalPractices.repository.h2Aux;
import com.dh.beTFI.dentalPractices.model.Dentist;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DentistRepositoryH2 implements IRepository<Dentist> {
    @Override
    public List<Dentist> getAll() {
        Connection connection = null;
        List<Dentist> dentists = new ArrayList<>();
        String sqlSelectAllDentists = "SELECT * FROM DENTISTS";

        try {
            connection = h2Aux.getConnection();
            PreparedStatement psGetAll = connection.prepareStatement(sqlSelectAllDentists);
            ResultSet result = psGetAll.executeQuery();
            while (result.next()) {
                Dentist dentist = new Dentist(
                        result.getInt("ID"),
                        result.getString("LASTNAME"),
                        result.getString("FIRSTNAME"),
                        result.getInt("PROFESSIONAL_ID")
                );
                dentists.add(dentist);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return dentists;
    }

    @Override
    public Dentist getById(int id) {
        Connection connection = null;
        Dentist dentist = null;
        String sqlGetDentistById = "SELECT * FROM DENTISTS WHERE ID = %s;".formatted(id);

        try {
            connection = h2Aux.getConnection();
            PreparedStatement psGetById = connection.prepareStatement(sqlGetDentistById);

            ResultSet result = psGetById.executeQuery();

            while (result.next()) {
                dentist = new Dentist(
                        result.getInt("ID"),
                        result.getString("LASTNAME"),
                        result.getString("FIRSTNAME"),
                        result.getInt("PROFESSIONAL_ID")
                );
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return dentist;
    }

    @Override
    public Dentist save(Dentist newDentist) {
        Connection connection = null;
        String sqlInsertDentist = "INSERT INTO DENTISTS (LASTNAME, FIRSTNAME, PROFESSIONAL_ID) VALUES (?, ?, ?);";

        try {
            connection = h2Aux.getConnection();

            PreparedStatement psInsertDentist = connection.prepareStatement(sqlInsertDentist, Statement.RETURN_GENERATED_KEYS);

            psInsertDentist.setString(1, newDentist.getLastName());
            psInsertDentist.setString(2, newDentist.getFirstName());
            psInsertDentist.setInt(3, newDentist.getProfessionalId());

            psInsertDentist.executeUpdate();

            ResultSet result = psInsertDentist.getGeneratedKeys();

            while (result.next()){
                newDentist.setId(result.getInt(1));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return newDentist;
    }

    @Override
    public Dentist update(Dentist updateDentist) {
        Connection connection = null;
        String sqlUpdateDentist = "UPDATE DENTISTS SET " +
                "LASTNAME = ?, FIRSTNAME = ?, PROFESSIONAL_ID = ?  " +
                "WHERE ID = ?;";

        try {
            connection = h2Aux.getConnection();
            PreparedStatement psUpdateDentist = connection.prepareStatement(sqlUpdateDentist);

            psUpdateDentist.setString(1, updateDentist.getLastName());
            psUpdateDentist.setString(2, updateDentist.getFirstName());
            psUpdateDentist.setInt(3, updateDentist.getProfessionalId());
            psUpdateDentist.setInt(4, updateDentist.getId());

            psUpdateDentist.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return updateDentist;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        String sqlDeletePatient = "DELETE FROM PATIENTS WHERE ID = %s;".formatted(id);

        try {
            connection = h2Aux.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(sqlDeletePatient);

            psDelete.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }
    }
}
