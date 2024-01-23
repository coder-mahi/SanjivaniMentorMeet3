package com.kulswaminitech.sanjivanimentormeet;

public class FetchStudentClass {
    String enrollmentNo,fyRoll,syRoll,tyRoll,NameOfStudent,emailId,contact,parentNo,category,gender,batch,address1,Mentor,fatherName,motherName,bloodGroup;

    public FetchStudentClass(String enrollmentNo, String fyRoll, String syRoll, String tyRoll,
                             String nameOfStudent, String emailId, String contact, String parentNo,
                             String category, String gender, String batch, String address1, String mentor,
                             String fatherName, String motherName, String bloodGroup) {
        this.enrollmentNo = enrollmentNo;
        this.fyRoll = fyRoll;
        this.syRoll = syRoll;
        this.tyRoll = tyRoll;
        this.NameOfStudent = nameOfStudent;
        this.emailId = emailId;
        this.contact = contact;
        this.parentNo = parentNo;
        this.category = category;
        this.gender = gender;
        this.batch = batch;
        this.address1 = address1;
        this.Mentor = mentor;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.bloodGroup = bloodGroup;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public String getFyRoll() {
        return fyRoll;
    }

    public String getSyRoll() {
        return syRoll;
    }

    public String getTyRoll() {
        return tyRoll;
    }

    public String getNameOfStudent() {
        return NameOfStudent;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getContact() {
        return contact;
    }

    public String getParentNo() {
        return parentNo;
    }

    public String getCategory() {
        return category;
    }

    public String getGender() {
        return gender;
    }

    public String getBatch() {
        return batch;
    }

    public String getAddress1() {
        return address1;
    }

    public String getMentor() {
        return Mentor;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
}
