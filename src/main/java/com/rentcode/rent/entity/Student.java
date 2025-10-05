package com.rentcode.rent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {
    @Column(name = "admission_no", unique = true)
    private String admissionNo;
    private String college;

    public String getAdmissionNo() { return admissionNo; }
    public void setAdmissionNo(String admissionNo) { this.admissionNo = admissionNo; }
    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }
}
