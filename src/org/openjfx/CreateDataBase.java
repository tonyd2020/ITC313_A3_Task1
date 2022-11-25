/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tony RMIT
 */
public class CreateDataBase {

    private static String[] sql;
    private static PreparedStatement prepStatement;

    private static Connection connection;

    public static void create() {

        String sql = "CREATE TABLE IF NOT EXISTS java2 (\n"
                        + "id INT NOT NULL PRIMARY KEY,\n"
                        + "name text not null,\n"
                        + "quiz double null,\n"
                        + "a1 double null,\n"
                        + "a2 double null,\n"
                        + "exam double null,\n"
                        + "cumulativeMark double null,\n"
                        + "grade text\n"
                        + ");";


        SQLconnect dbConnect = new SQLconnect();

        try {
            connection = dbConnect.open();
//            for (String s : sql) {
//                prepStatement = connection.prepareStatement(s);
//                prepStatement.executeUpdate();
//            }

            prepStatement = connection.prepareStatement(sql);
            prepStatement.executeUpdate();
//            Statement statement = connection.createStatement();
//            statement.executeQuery(sql);

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
