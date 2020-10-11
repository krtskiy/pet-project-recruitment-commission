package com.epam.koretskyi.commission.db.entity;

/**
 * @author D.Koretskyi on 10.10.2020.
 */
public class Criterion extends Entity {
    private static final long serialVersionUID = 5324970528413169785L;

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
        return "Criteria{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameUk='" + nameUk + '\'' +
                '}';
    }
}
