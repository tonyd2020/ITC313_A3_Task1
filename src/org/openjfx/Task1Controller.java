/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author Anthony Dera Student ID: 94088030
 */
public class Task1Controller implements Initializable {

    @FXML
    private TableView<Enrolment> table;
    @FXML
    TableColumn<Enrolment, Long> idCol;
    @FXML
    TableColumn<Enrolment, String> nameCol;
    @FXML
    TableColumn<Enrolment, Double> quizCol, a1Col, a2Col, examCol, totalCol, gradeCol;
    @FXML
    Button addBtn, cancelBtn;
    @FXML
    Label alert, subjectFocusFld;
    @FXML
    TextField idText, nameText;
    @FXML
    MenuItem createMenuItem, studentEnrolMenuItem, deleteMenuItem, studentEditMenuItem,
            studentDeleteMenuItem;

    static ObservableList<Enrolment> enrolments = FXCollections.emptyObservableList();
    private Enrolment isFocus;

    EnrolmentModel enrolModel;

    public Task1Controller() {
        // Load Models
        try {
            enrolModel = new EnrolmentModel();
        } catch (SQLException ex) {
            Logger.getLogger(Task1Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Empt tables" + ex.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTable();
    }

    public void showTable() {
        try {
            table.setItems(enrolModel.getEnrolmentList());
        } catch (SQLException ex) {
            Logger.getLogger(Task1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // Event Handler for modifying cell content using a double-click
        nameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Enrolment, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, String> t) {
                isFocus = t.getTableView().getItems().get(t.getTablePosition().getRow());
                String newVal = t.getNewValue();
                try {
                    if (!checkName(newVal)) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Only Alpha characters allowed for the name field");
                    } else {
                        isFocus.setName(newVal);
                        alert.setTextFill(Color.BLUE);
                        alert.setText("Database updated successfully");
                    }
                    enrolModel.updateEnrolment(isFocus);
                    t.getTableView().setItems(enrolModel.getEnrolmentList());

                } catch (SQLException ex) {
                    alert.setTextFill(Color.RED);
                    alert.setText("Error updating the Database " + ex.getMessage());
                }
            }
        }
        );

        quizCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() {
            @Override
            public Double fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (NumberFormatException ex) {
                    return Double.NaN;
                }
            }
        }));

        // Event Handler for warning the user
        quizCol.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<Enrolment, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, Double> t) {
                cellEditWarning(t);
            }
        });

        quizCol.setOnEditCancel(new EventHandler() {
            @Override
            public void handle(Event event) {
                alert.setTextFill(Color.BLUE);
                alert.setText("");
            }

        });
        // Event Handler for modifying cell content using a double-click
        quizCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Enrolment, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, Double> t) {
                isFocus = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Double newVal = t.getNewValue();
                try {
                    if (!checkValue(newVal)) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Only values between 0 and 100 are allowed");
                    } else if (newVal.isNaN()) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Please use numeric values only");
                    } else {
                        isFocus.setQuiz(newVal);
                        isFocus.calculate();
                        alert.setTextFill(Color.BLUE);
                        alert.setText("Database updated successfully");
                    }
                    enrolModel.updateEnrolment(isFocus);
                    t.getTableView().setItems(enrolModel.getEnrolmentList());

                } catch (SQLException ex) {
                    alert.setTextFill(Color.RED);
                    alert.setText("Error updating the Database " + ex.getMessage());
                }
            }
        }
        );

        a1Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() {
            @Override
            public Double fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (NumberFormatException ex) {
                    return Double.NaN;
                }
            }
        }));

        a1Col.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<Enrolment, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, Double> t) {
                cellEditWarning(t);
            }
        });

        a1Col.setOnEditCancel(new EventHandler() {
            @Override
            public void handle(Event event) {
                alert.setTextFill(Color.BLUE);
                alert.setText("");
            }

        });

        a1Col.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Enrolment, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, Double> t) {
                isFocus = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Double newVal = t.getNewValue();
                try {
                    if (!checkValue(newVal)) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Only values between 0 and 100 are allowed");
                    } else if (newVal.isNaN()) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Please use numeric values only");
                    } else {
                        isFocus.setA1(newVal);
                        isFocus.calculate();
                        alert.setTextFill(Color.BLUE);
                        alert.setText("Database updated successfully");
                    }
                    enrolModel.updateEnrolment(isFocus);
                    t.getTableView().setItems(enrolModel.getEnrolmentList());

                } catch (SQLException ex) {
                    alert.setTextFill(Color.RED);
                    alert.setText("Error updating the Database " + ex.getMessage());
                }

            }
        }
        );

        a2Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() {
            @Override
            public Double fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (NumberFormatException ex) {
                    return Double.NaN;
                }
            }
        }));

        a2Col.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<Enrolment, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, Double> t) {
                cellEditWarning(t);
            }
        });

        a2Col.setOnEditCancel(new EventHandler() {
            @Override
            public void handle(Event event) {
                alert.setTextFill(Color.BLUE);
                alert.setText("");
            }

        });
        // Event Handler for modifying cell content using a double-click
        a2Col.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Enrolment, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, Double> t) {
                isFocus = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Double newVal = t.getNewValue();
                try {
                    if (!checkValue(newVal)) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Only values between 0 and 100 are allowed");
                    } else if (newVal.isNaN()) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Please use numeric values only");
                    } else {
                        isFocus.setA2(newVal);
                        isFocus.calculate();
                        alert.setTextFill(Color.BLUE);
                        alert.setText("Database updated successfully");
                    }
                    enrolModel.updateEnrolment(isFocus);
                    t.getTableView().setItems(enrolModel.getEnrolmentList());

                } catch (SQLException ex) {
                    alert.setTextFill(Color.RED);
                    alert.setText("Error updating the Database " + ex.getMessage());
                }
            }
        }
        );

        examCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() {
            @Override
            public Double fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (NumberFormatException ex) {
                    return Double.NaN;
                }
            }
        }));

        examCol.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<Enrolment, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, Double> t) {
                cellEditWarning(t);
            }
        });

        examCol.setOnEditCancel(new EventHandler() {
            @Override
            public void handle(Event event) {
                alert.setTextFill(Color.BLUE);
                alert.setText("");
            }
        });
        // Event Handler for modifying cell content using a double-click
        examCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Enrolment, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Enrolment, Double> t) {
                isFocus = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Double newVal = t.getNewValue();
                try {
                    if (!checkValue(newVal)) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Only values between 0 and 100 are allowed");
                    } else if (newVal.isNaN()) {
                        newVal = t.getOldValue();
                        alert.setTextFill(Color.RED);
                        alert.setText("Please use numeric values only");
                    } else {
                        isFocus.setExam(newVal);
                        isFocus.calculate();
                        alert.setTextFill(Color.BLUE);
                        alert.setText("Database updated successfully");
                    }
                    enrolModel.updateEnrolment(isFocus);
                    t.getTableView().setItems(enrolModel.getEnrolmentList());

                } catch (SQLException ex) {
                    alert.setTextFill(Color.RED);
                    alert.setText("Error updating the Database " + ex.getMessage());
                }
            }
        }
        );
    }

    // Validate the values entered
    private boolean checkValue(double value) {
        if (value < 0 || value > 100) {
            return false;
        } else {
            return true;
        }
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

    private void cellEditWarning(TableColumn.CellEditEvent<Enrolment, Double> t) {
        alert.setTextFill(Color.RED);
        alert.setText("Only numbers allowed for the " + t.getTableColumn().getText() + " column");
    }

    // Create PoP up window to Add a Student
    public void addNewStudent() {
        Stage inputStage = new Stage();
        FXMLLoader inputLoader = new FXMLLoader(getClass().getResource("AddStudent.fxml"));
        Scene inputScene;
        try {
            inputScene = new Scene(inputLoader.load());
            inputStage.initOwner(Main.getStage());
            inputStage.setScene(inputScene);
        } catch (IOException e) {
            System.out.println(e);
        }
        // Pass the Data model to the PoP up window
        inputLoader.<AddStudentController>getController().loadModel(enrolModel);
        inputStage.showAndWait();
        showTable();

    }

    // Create PoP up window to Edit a Student
    public void editStudent() {
        Stage inputStage = new Stage();
        FXMLLoader inputLoader = new FXMLLoader(getClass().getResource("EditStudent.fxml"));
        Scene inputScene;
        try {
            inputScene = new Scene(inputLoader.load());
            inputStage.initOwner(Main.getStage());
            inputStage.setScene(inputScene);
        } catch (IOException e) {
            System.out.println(e);
        }
        inputStage.showAndWait();
        showTable();
    }

    // Create PoP up window to Delete a Student
    public void deleteStudent() {
        Stage inputStage = new Stage();
        FXMLLoader inputLoader = new FXMLLoader(getClass().getResource("DeleteEnrolment.fxml"));
        Scene deleteScene;
        try {
            deleteScene = new Scene(inputLoader.load());
            inputStage.initOwner(Main.getStage());
            inputStage.setScene(deleteScene);
        } catch (IOException e) {
            System.out.println(e);
        }
        inputLoader.<DeleteEnrolmentController>getController().loadModel(enrolModel, isFocus);
        inputStage.showAndWait();
        showTable();
    }

    // Create PoP up window to Find a Student
    public void FindStudent() {
        Stage enrolStage = new Stage();
        FXMLLoader inputLoader2 = new FXMLLoader(getClass().getResource("FindStudent.fxml"));
        Scene enrolScene;
        try {
            enrolScene = new Scene(inputLoader2.load());
            enrolStage.initOwner(Main.getStage());
            enrolStage.setScene(enrolScene);

        } catch (IOException e) {
            System.out.println(e);
        }
        inputLoader2.<FindStudentController>getController().loadModel(enrolModel);
        enrolStage.showAndWait();
        Enrolment e = inputLoader2.<FindStudentController>getController().getEnrolment();
        table.getSelectionModel().select(e);

    }

    // Create PoP up window for the About screen
    public void about() {
        Stage enrolStage = new Stage();
        FXMLLoader inputLoader2 = new FXMLLoader(getClass().getResource("About.fxml"));
        Scene enrolScene;
        try {
            enrolScene = new Scene(inputLoader2.load());
            enrolStage.initOwner(Main.getStage());
            enrolStage.setScene(enrolScene);

        } catch (IOException e) {
            System.out.println(e);
        }
        enrolStage.showAndWait();
        showTable();

    }

    public void onExit() {
        try {
            enrolModel.close();
        } catch (SQLException ex) {
            alert.setTextFill(Color.RED);
            alert.setText("Error closing the Database Connection" + ex.getMessage());
        }
        System.exit(0);
    }
}
