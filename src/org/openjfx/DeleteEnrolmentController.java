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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Anthony Dera Student ID: 94088030
 */
public class DeleteEnrolmentController implements Initializable {
    
    @FXML
    private TextField subjectTxtFld;
    @FXML
    private Button submit, cancel;
    @FXML
    private ComboBox<Enrolment> studentIDFld;
    @FXML
    Label alert;
    EnrolmentModel model;
    Enrolment en;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void loadModel(EnrolmentModel model, Enrolment enrolment) {
        this.model = model;
        this.en = enrolment;
        
        try {
            studentIDFld.setItems(model.getEnrolmentList());
        } catch (SQLException ex) {
            alert.setTextFill(Color.RED);
            alert.setText("Database error: " + ex.getMessage());
        }
//        studentIDFld.setPromptText("Select the Subject to be deleted");
        studentIDFld.getSelectionModel().select(en);
        studentIDFld.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            subjectTxtFld.setText(newValue.toString());
            en = newValue;
            
        });
    }
    
    @FXML
    public void onSubmit() {
        String cols[] = subjectTxtFld.getText().toString().split(" : ");
        try {
            
            model.deleteEnrolment(en);
            cancel.setText("Exit");
            alert.setTextFill(Color.BLUE);
            alert.setText("Subject deleted from the Database");
            
        } catch (SQLException ex) {
            alert.setTextFill(Color.RED);
            alert.setText("Database error: " + ex.getMessage());
        }
        
    }
    
    @FXML
    private void onCancel() {
        subjectTxtFld.getScene().getWindow().hide();
    }
}
