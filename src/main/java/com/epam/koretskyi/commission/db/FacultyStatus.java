package com.epam.koretskyi.commission.db;


/**
 * @author D.Koretskyi on 13.10.2020.
 */
public enum FacultyStatus {

    OPENED, FINALIZED;

    public String getName() {
        return name().toLowerCase();
    }
}
