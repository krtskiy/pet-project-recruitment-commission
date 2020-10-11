package com.epam.koretskyi.commission.db.entity;

/**
 * @author D.Koretskyi on 10.10.2020.
 */
public class Entrant extends Entity {
    private static final long serialVersionUID = 2172092490868575073L;

    private int userId;
    private int facultyId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public String toString() {
        return "Entrant{" +
                "userId=" + userId +
                ", facultyId=" + facultyId +
                '}';
    }
}
