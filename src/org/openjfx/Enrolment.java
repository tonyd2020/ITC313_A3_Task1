/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Anthony Dera Student ID: 94088030
 */
public class Enrolment implements Comparable<Enrolment> {

    private final LongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleDoubleProperty quiz = new SimpleDoubleProperty(0.0);
    private final SimpleDoubleProperty a1 = new SimpleDoubleProperty(0.0);
    private final SimpleDoubleProperty a2 = new SimpleDoubleProperty(0.0);
    private final SimpleDoubleProperty exam = new SimpleDoubleProperty(0.0);
    private final SimpleDoubleProperty cumulativeMark = new SimpleDoubleProperty(0.0);
    private final SimpleStringProperty grade = new SimpleStringProperty("FL");

    public Enrolment() {

    }

    public Enrolment(Long id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public Enrolment(
            Long studentId,
            String subjectId,
            double quiz,
            double a1,
            double a2,
            double exam,
            double cumulativeMark,
            String grade) {
        this(studentId, subjectId);
        this.quiz.set(quiz);
        this.a1.set(a1);
        this.a2.set(a2);
        this.exam.set(exam);
        this.cumulativeMark.set(cumulativeMark);
        this.grade.set(grade);
    }

    public Long getId() {
        return this.id.get();
    }

    public String getName() {
        return name.get();
    }

    public double getQuiz() {
        return quiz.get();
    }

    public void setQuiz(double quiz) {
        this.quiz.set(quiz);
    }

    public double getA1() {
        return a1.get();
    }

    public void setA1(double a1) {
        this.a1.set(a1);
    }

    public double getA2() {
        return a2.get();
    }

    public void setA2(double a2) {
        this.a2.set(a2);
    }

    public double getExam() {
        return exam.get();
    }

    public void setExam(double exam) {
        this.exam.set(exam);

    }

    // Set the Decimal places
    public double getCumulativeMark() {
        DecimalFormat format = new DecimalFormat("##.00");
        format.setRoundingMode(RoundingMode.UP);
        return Double.valueOf(format.format(this.cumulativeMark.get()));
    }

    public String getGrade() {
        if (cumulativeMark.get() >= 85.0) {
            grade.set("HD");
        } else if (cumulativeMark.get() >= 75.0) {
            grade.set("DI");
        } else if (cumulativeMark.get() >= 65.0) {
            grade.set("CR");
        } else if (cumulativeMark.get() >= 50.0) {
            grade.set("PS");
        } else {
            grade.set("FL");
        }
        return grade.getValue();
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

//    public String getSubjectId() {
//        return subject_Id.get();
//    }
    public void setName(String name) {
        this.name.set(name);
    }

    public void calculate() {
        this.cumulativeMark.set((quiz.get() * 0.05) + (a1.get() * 0.15) + (a2.get() * 0.2) + (exam.get() * 0.6));
        if (cumulativeMark.get() >= 85.0) {
            grade.set("HD");
        } else if (cumulativeMark.get() >= 75.0) {
            grade.set("DI");
        } else if (cumulativeMark.get() >= 65.0) {
            grade.set("CR");
        } else if (cumulativeMark.get() >= 50.0) {
            grade.set("PS");
        } else {
            grade.set("FL");
        }
    }

    public String toString() {
        return String.valueOf(this.id.get());
    }

    @Override
    public int compareTo(Enrolment o) {
        if (this.id.get() > o.id.get())
            return 1;
        else if(this.id.get() == o.id.get())
            return 0;
        else return -1;
    }
}
