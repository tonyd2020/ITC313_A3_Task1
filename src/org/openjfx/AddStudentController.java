/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Anthony Dera Student ID: 94088030
 */
public class AddStudentController implements Initializable {

    @FXML
    private TextField studentTxtFld;
    @FXML
    private Button submit, cancel;
    @FXML
    private TextField studentIDFld;
    @FXML
    Label alert;

    EnrolmentModel enrolModel;
    Enrolment enrol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void onSubmit() {
        if (checkID(studentIDFld.getText())) {
            enrol = null;
            alert.setTextFill(Color.RED);
            alert.setText("Please use Numeric Characters only for this field");
        } else if (studentIDFld.getText().length() > 8) {
            enrol = null;
            alert.setTextFill(Color.RED);
            alert.setText("Student ID cannot exceed 8 Digits");
        } else if (studentTxtFld.getText().equals("")) {
            enrol = null;
            alert.setTextFill(Color.RED);
            alert.setText("Student Name field cannot be empty");
        } else {
            try {
                enrol = new Enrolment(Long.parseLong(studentIDFld.getText().trim()), studentTxtFld.getText().trim());
            } catch (NumberFormatException e) {
                enrol = null;
                alert.setTextFill(Color.RED);
                alert.setText("Student ID must be Numeric only");
            }
            try {
                enrolModel.addEnrolment(enrol);
                alert.setTextFill(Color.BLUE);
                alert.setText("Record created");
            } catch (SQLException ex) {
                alert.setTextFill(Color.RED);
                alert.setText("Error adding the Student to the Database " + ex.getMessage());
            }
        }
    }

    public void loadModel(EnrolmentModel enrolModel) {
        this.enrolModel = enrolModel;
    }

    @FXML
    private void onCancel() {
        studentTxtFld.getScene().getWindow().hide();
    }

    public boolean checkID(String value) {
        if (value == null || value == "") {
            return false;
        } else if (!value.matches("^.*[^0-9].*$")) {
            return false;
        } else {
            return true;
        }
    }
}
