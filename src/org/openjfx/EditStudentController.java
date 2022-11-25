/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Anthony Dera Student ID: 94088030
 */
public class EditStudentController implements Initializable {

    @FXML
    private TextField studentTxtFld;
    @FXML
    private Button submit;
    @FXML
    private ComboBox<Enrolment> studentIDFld;
    @FXML
    private Button cancel;
    @FXML
    private Label alert;
    EnrolmentModel model;
    Enrolment student;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentIDFld.setPromptText("Select the Student to be edited");
        try {
            model = new EnrolmentModel();
            studentIDFld.setItems(model.getEnrolmentList());
            alert.setTextFill(Color.BLUE);
        } catch (SQLException ex) {
            alert.setTextFill(Color.RED);
            alert.setText("Database error: " + ex.getMessage());
        }
        studentIDFld.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            student = newValue;
            studentTxtFld.setText(newValue.getName());
        });
    }

    @FXML
    private void onSubmit(ActionEvent event) {
        String st = studentTxtFld.getText();
        if (st == null || st=="") {
            alert.setTextFill(Color.RED);
            alert.setText("Name field cannot be Empty " );
        } else if(!checkName(st)) {
            alert.setTextFill(Color.RED);
            alert.setText("Name cannot contain numerals " );
        }else{
            try {
                student.setName(studentTxtFld.getText());
                model.updateEnrolment(student);
                cancel.setText("Exit");
                alert.setTextFill(Color.BLUE);
                alert.setText("Updated successful.");
            } catch (SQLException ex) {
                alert.setTextFill(Color.RED);
                alert.setText("Database error: " + ex.getMessage());
            }            
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        studentTxtFld.getScene().getWindow().hide();
        return;
    }

    public boolean checkName(String value) {
        if (value == null || value == "") {
            return false;
        } else if (Pattern.compile("[0-9]").matcher(value).find()) {
            return false;
        } else {
            return true;
        }
    }
}
