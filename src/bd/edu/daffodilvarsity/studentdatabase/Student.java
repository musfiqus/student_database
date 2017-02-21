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
public class Student implements Serializable {
    private String fullName;
    private String idNum;
    private String fatherName;
    private String motherName;
    private String gender;
    private Course A;
    private Course B;
    private Course C;
    private Course D;
    private Course E;
    private Course F;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Course getA() {
        return A;
    }

    public void setA(Course A) {
        this.A = A;
    }

    public Course getB() {
        return B;
    }

    public void setB(Course B) {
        this.B = B;
    }

    public Course getC() {
        return C;
    }

    public void setC(Course C) {
        this.C = C;
    }

    public Course getD() {
        return D;
    }

    public void setD(Course D) {
        this.D = D;
    }

    public Course getE() {
        return E;
    }

    public void setE(Course E) {
        this.E = E;
    }

    public Course getF() {
        return F;
    }

    public void setF(Course F) {
        this.F = F;
    }

    
}
