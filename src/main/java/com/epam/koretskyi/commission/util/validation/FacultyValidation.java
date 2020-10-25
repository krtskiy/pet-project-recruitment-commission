package com.epam.koretskyi.commission.util.validation;

import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.db.entity.Faculty;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author D.Koretskyi on 25.10.2020.
 */
public class FacultyValidation {

    public static boolean validateFacultyFieldsIsEmpty(Faculty faculty) {
        return StringUtils.isAllBlank(faculty.getNameEn(), faculty.getNameUk(),
                Integer.toString(faculty.getTotalSeats()), Integer.toString(faculty.getBudgetSeats()),
                Integer.toString(faculty.getId()));
    }

    public static boolean validateFacultySeats(Faculty faculty) {
        return faculty.getTotalSeats() >= faculty.getBudgetSeats();
    }

    public static boolean validateFacultyCriteria(List<Criterion> criteria) {
        return criteria != null && !criteria.isEmpty() && criteria.size() <=4;
    }

}
