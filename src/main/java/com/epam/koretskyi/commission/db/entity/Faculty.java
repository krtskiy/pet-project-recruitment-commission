package com.epam.koretskyi.commission.db.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class Faculty extends Entity implements Localizable {
    private static final long serialVersionUID = 8715704204956161957L;

    private int id;
    private String nameEn;
    private String nameUk;
    private int totalSeats;
    private int budgetSeats;
    private List<Criterion> criteria = new ArrayList<>();
    private int statusId;

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

    public List<Criterion> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterion> criteria) {
        this.criteria = criteria;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameUk='" + nameUk + '\'' +
                ", totalSeats=" + totalSeats +
                ", budgetSeats=" + budgetSeats +
                ", criteria=" + criteria +
                ", statusId=" + statusId +
                '}';
    }

}
