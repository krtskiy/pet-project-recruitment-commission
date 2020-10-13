package com.epam.koretskyi.commission.db.bean;

import com.epam.koretskyi.commission.db.entity.Entity;
import com.epam.koretskyi.commission.db.entity.UserMark;

import java.util.List;

/**
 * @author D.Koretskyi on 12.10.2020.
 */
public class FacultyApplicationsBean extends Entity {
    private static final long serialVersionUID = 4489430004235774335L;

    private int userId;
    private String userName;
    private String userSurname;
    private List<UserMarksBean> userMarks;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public List<UserMarksBean> getUserMarks() {
        return userMarks;
    }

    public void setUserMarks(List<UserMarksBean> userMarks) {
        this.userMarks = userMarks;
    }

    @Override
    public String toString() {
        return "FacultyApplicationsBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userMarks=" + userMarks +
                '}';
    }

    public int sumOfMarks() {
        int sum = 0;
        for (UserMarksBean userMark : userMarks) {
            sum += userMark.getMark();
        }
        return sum;
    }

}
