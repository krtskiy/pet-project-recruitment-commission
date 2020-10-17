package com.epam.koretskyi.commission.db;


/**
 * Constants, which determine whether the enrollment to the faculty is closed or open
 *
 * @author D.Koretskyi on 13.10.2020.
 */
public enum FacultyStatus {

    OPENED, FINALIZED;

    public String getName() {
        return name().toLowerCase();
    }
}
