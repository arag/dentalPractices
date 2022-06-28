package com.dh.beTFI.dentalPractices.repository.patientRepository;

import com.dh.beTFI.dentalPractices.repository.IRepository;
import com.dh.beTFI.dentalPractices.repository.addressRepository.AddressRepositoryH2;
import com.dh.beTFI.dentalPractices.repository.h2Aux;
import com.dh.beTFI.dentalPractices.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PatientRepositoryH2 implements IRepository<Patient> {

    private AddressRepositoryH2 addressRepositoryH2;

    @Autowired
    public PatientRepositoryH2(AddressRepositoryH2 addressRepositoryH2) {
        this.addressRepositoryH2 = addressRepositoryH2;
    }

    @Override
    public List<Patient> getAll() {
        Connection connection = null;
        List<Patient> patients = new ArrayList<>();
        String sqlSelectAllPatients = "SELECT * FROM PATIENTS";

        try {
            connection = h2Aux.getConnection();
            PreparedStatement psGetAll = connection.prepareStatement(sqlSelectAllPatients);
            ResultSet result = psGetAll.executeQuery();
            while (result.next()) {
                Patient patient = new Patient(
                        result.getInt("ID"),
                        result.getString("LASTNAME"),
                        result.getString("FIRSTNAME"),
                        result.getString("EMAIL"),
                        result.getInt("DNI"),
                        result.getDate("ADMISSION_DATE").toLocalDate(),
                        addressRepositoryH2.getById(result.getInt("ADDRESS_ID"))
                );
                patients.add(patient);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return patients;
    }

    @Override
    public Patient getById(int id) {
        Connection connection = null;
        Patient patient = null;
        String sqlGetPatientById = "SELECT * FROM PATIENTS WHERE ID = ?";

        try {
            connection = h2Aux.getConnection();
            PreparedStatement psGetById = connection.prepareStatement(sqlGetPatientById);
            psGetById.setInt(1, id);
            ResultSet result = psGetById.executeQuery();
            while (result.next()) {
                patient = new Patient(
                        result.getInt("ID"),
                        result.getString("LASTNAME"),
                        result.getString("FIRSTNAME"),
                        result.getString("EMAIL"),
                        result.getInt("DNI"),
                        result.getDate("ADMISSION_DATE").toLocalDate(),
                        addressRepositoryH2.getById(result.getInt("ADDRESS_ID"))
                );
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return patient;
    }

    @Override
    public Patient save(Patient newPatient) {
        Connection connection = null;
        String sqlInsertPatient = "INSERT INTO PATIENTS (LASTNAME, FIRSTNAME, EMAIL, DNI, ADMISSION_DATE, ADDRESS_ID)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = h2Aux.getConnection();

            addressRepositoryH2.save(newPatient.getAddress());

            PreparedStatement psInsertPatient = connection.prepareStatement(sqlInsertPatient, Statement.RETURN_GENERATED_KEYS);

            psInsertPatient.setString(1, newPatient.getLastName());
            psInsertPatient.setString(2, newPatient.getFirstName());
            psInsertPatient.setString(3, newPatient.getEmail());
            psInsertPatient.setInt(4, newPatient.getDni());
            psInsertPatient.setDate(5, Date.valueOf(newPatient.getAdmissionDate()));
            psInsertPatient.setInt(6, newPatient.getAddress().getId());

            psInsertPatient.executeUpdate();

            ResultSet result = psInsertPatient.getGeneratedKeys();

            while (result.next()){
                newPatient.setId(result.getInt(1));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return newPatient;
    }

    @Override
    public Patient update(Patient updatePatient) {
        Connection connection = null;
        String sqlUpdatePatient = "UPDATE PATIENTS SET " +
                "LASTNAME = ?, FIRSTNAME = ?, EMAIL = ?, DNI = ?, ADMISSION_DATE = ?, ADDRESS_ID = ?  " +
                "WHERE ID = ?;";

        try {
            connection = h2Aux.getConnection();
            PreparedStatement psUpdatePatient = connection.prepareStatement(sqlUpdatePatient);

            psUpdatePatient.setString(1, updatePatient.getLastName());
            psUpdatePatient.setString(2, updatePatient.getFirstName());
            psUpdatePatient.setString(3, updatePatient.getEmail());
            psUpdatePatient.setInt(4, updatePatient.getDni());
            psUpdatePatient.setDate(5, Date.valueOf(updatePatient.getAdmissionDate()));
            psUpdatePatient.setInt(6, updatePatient.getAddress().getId());
            psUpdatePatient.setInt(7, updatePatient.getId());

            psUpdatePatient.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return updatePatient;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        String sqlDeletePatient = "DELETE FROM PATIENTS WHERE ID = %s;".formatted(id);

        try {
            connection = h2Aux.getConnection();

            Patient patient = getById(id);
            addressRepositoryH2.delete(patient.getAddress().getId());

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
