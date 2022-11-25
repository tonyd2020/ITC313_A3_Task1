/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class FindStudentController implements Initializable {

    @FXML
    private TextField studentIDFld, studentTxtFld;
    @FXML
    private Button submit, cancel;
    @FXML
    private Label alert;

    EnrolmentModel enrolModel;

    Enrolment enrolment;
    Long id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void loadModel(EnrolmentModel enrolModel) {
        this.enrolModel = enrolModel;
    }

    @FXML
    private void onSubmit(ActionEvent event) {
        if (studentIDFld.getText().trim().equals("")) {
            alert.setTextFill(Color.RED);
            alert.setText("Student ID field cannot be blank!");
        } else if (studentIDFld.getText().trim().matches("[a-zA-Z]")) {
            alert.setTextFill(Color.RED);
            alert.setText("Please enter numeric numbers only");
            studentIDFld.setText("");
        } else {
            try {
                enrolment = enrolModel.getStudent(Long.parseLong(studentIDFld.getText().trim()));
                if (enrolment != null) {
                    studentTxtFld.setText(enrolment.getName());
                    alert.setTextFill(Color.BLUE);
                    alert.setText("Student Found");
                    cancel.setText("Exit");
                }
                else{
                alert.setTextFill(Color.RED);
                alert.setText("Student ID not found");                    
                }

            } catch (NumberFormatException e) {
                alert.setTextFill(Color.RED);
                alert.setText("Please enter numeric numbers only");
            } catch (SQLException ex) {
                alert.setTextFill(Color.RED);
                alert.setText("Database access error " + ex.getMessage());
                System.out.println(ex.getMessage());
            }

        }

    }

    @FXML
    private void onCancel(ActionEvent event) {
        studentTxtFld.getScene().getWindow().hide();
    }

    public Enrolment getEnrolment() {
        return enrolment;
    }

}
