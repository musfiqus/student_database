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

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setGradePoints(double gradePoints) {
        this.gradePoints = gradePoints;
    }
    
    public String getCourseName() {
        return courseName;
    }

    public double getGradePoints() {
        return gradePoints;
    }
    
    
}
