package com.dh.beTFI.dentalPractices.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class h2Aux {
    public static void setUpConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./tfiDentalPractices;INIT=RUNSCRIPT FROM 'dental-practices-h2.sql'",
                    "sa", "sa");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./tfiDentalPractices", "sa", "sa");
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
