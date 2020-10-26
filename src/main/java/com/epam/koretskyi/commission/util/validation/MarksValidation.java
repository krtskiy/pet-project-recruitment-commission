package com.epam.koretskyi.commission.util.validation;

import com.epam.koretskyi.commission.db.entity.UserMark;

import java.util.List;

/**
 * @author D.Koretskyi on 25.10.2020.
 */
public class MarksValidation {

    public static boolean allMarksRangeValidation(List<UserMark> marks) {
        boolean validate = true;

        for (UserMark mark : marks) {
            if (mark.getCriterionId() == 1) {
                if (mark.getMark() > 12 || mark.getMark() < 1) {
                    validate = false;
                }
            } else {
                if (mark.getMark() > 200 || mark.getMark() < 100) {
                    validate = false;
                }
            }
        }

        return validate;
    }

    public static boolean isAllPositiveValidation(List<UserMark> userMarks) {
        boolean validate = true;

        for (UserMark mark : userMarks) {
            if (mark.getMark() <= 0) {
                validate = false;
                break;
            }
        }

        return validate;
    }

    public static boolean isMarkPositiveValidation(int mark) {
        return mark > 0;
    }

    public static boolean markRangeValidation(int mark, int criterionId) {
        boolean validate = true;

        if (criterionId == 1) {
            if (mark > 12 || mark < 1) {
                validate = false;
            }
        } else {
            if (mark > 200 || mark < 100) {
                validate = false;
            }
        }

        return validate;
    }
}
