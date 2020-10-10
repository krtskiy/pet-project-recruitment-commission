package com.epam.koretskyi.commission.db.entity;

/**
 * @author D.Koretskyi on 09.10.2020.
 */
public class Subject extends Entity {
    private static final long serialVersionUID = -3244832625070501333L;

    private int id;
    private String nameEn;
    private String nameUk;

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

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameUk='" + nameUk + '\'' +
                '}';
    }
}
