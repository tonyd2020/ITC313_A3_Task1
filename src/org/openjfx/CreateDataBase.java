/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        sql = new String[]{"create schema if not exists gradeprocessing"
                + " default character set utf8",
            "use gradeprocessing",
            "create table if not exists "
            + "gradeprocessing.java2 ("
            + "id int not null,"
            + "name varchar(45) not null,"
            + "quiz double null,"
            + "a1 double null,"
            + "a2 double null,"
            + "exam double null,"
            + "cumulativeMark double null,"
            + "grade varchar(2) null,"
            + "primary key (id),"
            + "unique index id_UNIQUE (id asc) visible)"
            + "engine = InnoDB; "};

        SQLconnect dbConnect = new SQLconnect();

        try {
            connection = dbConnect.open();
            for (String s : sql) {
                prepStatement = connection.prepareStatement(s);
                prepStatement.executeUpdate();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
