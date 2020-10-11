package com.epam.koretskyi.commission.db.entity;

/**
 * @author D.Koretskyi on 10.10.2020.
 */
public class UserMarks extends Entity {
    private static final long serialVersionUID = 3096589858363433927L;

    private int userId;
    private int criterionId;
    private int mark;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(int criterionId) {
        this.criterionId = criterionId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "UserMarks{" +
                "userId=" + userId +
                ", criterionId=" + criterionId +
                ", mark=" + mark +
                '}';
    }
}
