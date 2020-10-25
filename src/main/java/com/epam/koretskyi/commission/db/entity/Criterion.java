package com.epam.koretskyi.commission.db.entity;

import java.util.Objects;

/**
 * @author D.Koretskyi on 10.10.2020.
 */
public class Criterion extends Entity implements Localizable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Criterion criterion = (Criterion) o;

        if (id != criterion.id) return false;
        if (!Objects.equals(nameEn, criterion.nameEn)) return false;
        return Objects.equals(nameUk, criterion.nameUk);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameUk != null ? nameUk.hashCode() : 0);
        return result;
    }
}
