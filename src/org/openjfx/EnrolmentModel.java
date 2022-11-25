/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Anthony Dera Student ID: 94088030
 */
public class EnrolmentModel {

    private PreparedStatement prepStatement;
    private Statement statement;
    private Connection connection;

    public EnrolmentModel() throws SQLException {
        SQLconnect dbConnect = new SQLconnect();
        connection = dbConnect.open();
    }

    public ObservableList<Enrolment> getEnrolmentList() throws SQLException {
        ObservableList<Enrolment> s = FXCollections.observableArrayList();
        String queryStr = "select * from java2";
        ResultSet result;
        prepStatement = connection.prepareStatement(queryStr);
        result = prepStatement.executeQuery();
        while (result.next()) {
            s.add(
                    new Enrolment(result.getLong("id"),
                            result.getString("name"),
                            result.getDouble("quiz"),
                            result.getDouble("a1"),
                            result.getDouble("a2"),
                            result.getDouble("exam"),
                            result.getDouble("cumulativeMark"),
                            result.getString("grade")
                    ));
        }
        return s;
    }

    public Enrolment getStudent(Long id) throws SQLException {

        Enrolment student = null;
        String queryStr = "select * from java2 where id=?";
        prepStatement = connection.prepareStatement(queryStr);
        prepStatement.setLong(1, id);
        prepStatement.executeQuery();
        ResultSet result = prepStatement.executeQuery();
        while (result.next()) {
            student = new Enrolment(result.getLong("id"), result.getString("name"));
        }
        return student;
    }

    public ObservableList<Enrolment> getEnrolmentListbyStudent(Long id) throws SQLException {
        ObservableList<Enrolment> enrolments = FXCollections.observableArrayList();
        String queryStr;
        queryStr = "select * from java2 where id=?";

        ResultSet result;
        prepStatement = connection.prepareStatement(queryStr);
        prepStatement.setLong(1, id);
        result = prepStatement.executeQuery();
        while (result.next()) {
            enrolments.add(new Enrolment(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getDouble("quiz"),
                    result.getDouble("a1"),
                    result.getDouble("a2"),
                    result.getDouble("exam"),
                    result.getDouble("cumulativeMark"),
                    result.getString("grade")
            ));
        }
        return enrolments;
    }

    public void updateEnrolment(Enrolment enrolment) throws SQLException {
        String queryStr = "update java2 set name=?,quiz=?,a1=?,a2=?,exam=?,cumulativeMark=?,grade=? where id = ?";
        enrolment.calculate();
        prepStatement = connection.prepareStatement(queryStr);
        prepStatement.setString(1, enrolment.getName());
        prepStatement.setDouble(2, enrolment.getQuiz());
        prepStatement.setDouble(3, enrolment.getA1());
        prepStatement.setDouble(4, enrolment.getA2());
        prepStatement.setDouble(5, enrolment.getExam());
        prepStatement.setDouble(6, enrolment.getCumulativeMark());
        prepStatement.setString(7, enrolment.getGrade());
        prepStatement.setLong(8, enrolment.getId());
        prepStatement.executeUpdate();

    }

    public void deleteEnrolment(Enrolment enrolment) throws SQLException {
        String queryStr = "delete from java2 where id=?";
        prepStatement = connection.prepareStatement(queryStr);
        prepStatement.setLong(1, enrolment.getId());
        prepStatement.executeUpdate();
    }

    public void addEnrolment(Enrolment enrolment) throws SQLException {
        String queryStr = "insert into java2 (id,quiz,a1,a2,exam,cumulativeMark,grade,name) values (?,?,?,?,?,?,?,?)";
        prepStatement = connection.prepareStatement(queryStr);
        prepStatement.setLong(1, enrolment.getId());
        prepStatement.setDouble(2, enrolment.getQuiz());
        prepStatement.setDouble(3, enrolment.getA1());
        prepStatement.setDouble(4, enrolment.getA2());
        prepStatement.setDouble(5, enrolment.getExam());
        prepStatement.setDouble(6, enrolment.getCumulativeMark());
        prepStatement.setString(7, enrolment.getGrade());
        prepStatement.setString(8, enrolment.getName());
        prepStatement.executeUpdate();
    }

    public void close() throws SQLException {
        connection.close();
    }
}
