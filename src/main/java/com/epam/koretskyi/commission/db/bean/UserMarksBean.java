package com.epam.koretskyi.commission.db.bean;

import com.epam.koretskyi.commission.db.entity.Entity;

/**
 * @author D.Koretskyi on 12.10.2020.
 */
public class UserMarksBean extends Entity {
    private static final long serialVersionUID = 7716098285962417038L;

    private int criterionId;
    private String criterionNameEn;
    private String criterionNameUk;
    private int mark;

    public String getCriterionNameEn() {
        return criterionNameEn;
    }

    public void setCriterionNameEn(String criterionNameEn) {
        this.criterionNameEn = criterionNameEn;
    }

    public String getCriterionNameUk() {
        return criterionNameUk;
    }

    public void setCriterionNameUk(String criterionNameUk) {
        this.criterionNameUk = criterionNameUk;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(int criterionId) {
        this.criterionId = criterionId;
    }

    @Override
    public String toString() {
        return "UserMarksBean{" +
                "criterionId=" + criterionId +
                ", criterionNameEn='" + criterionNameEn + '\'' +
                ", criterionNameUk='" + criterionNameUk + '\'' +
                ", mark=" + mark +
                '}';
    }
}
