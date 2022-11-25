/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Anthony Dera Student ID: 94088030
 */
public class SQLconnect {

    private final String userName = "root", password = "admin";
    private final String dbms = "mysql";
    private final String serverName = "localhost";
    private final String portNumber = "3306";
    private final String dbName = "gradeprocessing";

    public Connection open() throws SQLException {

        Connection conn = null;
//        Properties connectionProps = new Properties();
//        connectionProps.put("dbName", this.dbName);
//        connectionProps.put("user", this.userName);
//        connectionProps.put("password", this.password);
//
//            conn = DriverManager.getConnection(
//                    "jdbc:" + this.dbms + "://"
//                    + this.serverName
//                    + ":" + this.portNumber + "/",
//                    connectionProps);

        String url = "jdbc:sqlite:g:/sqllite/db/gradeprocessing.db";
        conn = DriverManager.getConnection(url);

        return conn;
    }



}
