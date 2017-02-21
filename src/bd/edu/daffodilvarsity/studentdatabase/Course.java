/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.daffodilvarsity.studentdatabase;

import java.io.Serializable;

/**
 *
 * @author Mushfiqus Salehin
 */
class Course implements Serializable {
    private String courseName;
    private double gradePoints;

    public Course(String courseName, double gradePoints) {
        this.courseName = courseName;
        this.gradePoints = gradePoints;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGradePoints() {
        return gradePoints;
    }
    
    
}
