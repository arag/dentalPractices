package com.dh.beTFI.dentalPractices.repository.addressRepository;

import com.dh.beTFI.dentalPractices.repository.IRepository;
import com.dh.beTFI.dentalPractices.repository.h2Aux;
import com.dh.beTFI.dentalPractices.model.Address;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class AddressRepositoryH2 implements IRepository<Address> {
    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public Address getById(int id) {
        Connection connection = null;
        Address address = null;
        String sqlGetAddressById = "SELECT * FROM ADDRESSES WHERE ID = ?";

        try {
            connection = h2Aux.getConnection();
            PreparedStatement psGetById = connection.prepareStatement(sqlGetAddressById);
            psGetById.setInt(1, id);
            ResultSet result = psGetById.executeQuery();
            while (result.next()) {
                address = new Address(
                        result.getInt("ID"),
                        result.getString("STREET"),
                        result.getInt("NUMBER"),
                        result.getString("CITY"),
                        result.getString("PROVINCE")
                );
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return address;
    }

    @Override
    public Address save(Address newAddress) {
        Connection connection = null;
        String sqlInsertAddress = "INSERT INTO ADDRESSES (STREET, NUMBER, CITY, PROVINCE)" +
                " VALUES (?, ?, ?, ?)";

        try {
            connection = h2Aux.getConnection();

            PreparedStatement psInsertAddress = connection.prepareStatement(sqlInsertAddress, Statement.RETURN_GENERATED_KEYS);

            psInsertAddress.setString(1, newAddress.getStreet());
            psInsertAddress.setInt(2, newAddress.getNumber());
            psInsertAddress.setString(3, newAddress.getCity());
            psInsertAddress.setString(4, newAddress.getProvince());

            psInsertAddress.executeUpdate();

            ResultSet result = psInsertAddress.getGeneratedKeys();

            while (result.next()){
                newAddress.setId(result.getInt(1));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            h2Aux.closeConnection(connection);
        }

        return newAddress;
    }

    @Override
    public Address update(Address entity) {
        return null;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        String sqlDeleteAddress = "DELETE FROM ADDRESSES WHERE ID = %s;".formatted(id);

        try {
            connection = h2Aux.getConnection();

            PreparedStatement psDelete = connection.prepareStatement(sqlDeleteAddress);

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
