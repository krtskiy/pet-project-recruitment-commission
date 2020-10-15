package com.epam.koretskyi.commission.db.bean;

import com.epam.koretskyi.commission.db.entity.Entity;

/**
 * @author D.Koretskyi on 12.10.2020.
 */
public class UserFacultiesBean extends Entity {
    private static final long serialVersionUID = -3326034759772839007L;

    private int facultyId;
    private int facultyStatusId;
    private String facultyNameEn;
    private String facultyNameUk;

    public String getFacultyNameEn() {
        return facultyNameEn;
    }

    public void setFacultyNameEn(String facultyNameEn) {
        this.facultyNameEn = facultyNameEn;
    }

    public String getFacultyNameUk() {
        return facultyNameUk;
    }

    public void setFacultyNameUk(String facultyNameUk) {
        this.facultyNameUk = facultyNameUk;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getFacultyStatusId() {
        return facultyStatusId;
    }

    public void setFacultyStatusId(int facultyStatusId) {
        this.facultyStatusId = facultyStatusId;
    }

    @Override
    public String toString() {
        return "UserFacultiesBean{" +
                "facultyId=" + facultyId +
                ", facultyNameEn='" + facultyNameEn + '\'' +
                ", facultyNameUk='" + facultyNameUk + '\'' +
                '}';
    }
}
