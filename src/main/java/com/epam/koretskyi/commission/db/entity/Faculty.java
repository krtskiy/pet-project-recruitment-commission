package com.epam.koretskyi.commission.db.entity;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class Faculty extends Entity {
    private static final long serialVersionUID = 8715704204956161957L;

    private int id;
    private String nameEn;
    private String nameUk;
    private int totalSeats;
    private int budgetSeats;

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUk() {
        return nameUk;
    }

    public void setNameUk(String nameUk) {
        this.nameUk = nameUk;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getBudgetSeats() {
        return budgetSeats;
    }

    public void setBudgetSeats(int budgetSeats) {
        this.budgetSeats = budgetSeats;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "nameEn='" + nameEn + '\'' +
                ", nameUk='" + nameUk + '\'' +
                ", totalSeats=" + totalSeats +
                ", budgetSeats=" + budgetSeats +
                '}';
    }
}
