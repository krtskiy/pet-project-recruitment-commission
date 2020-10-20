package com.epam.koretskyi.commission.db.entity;

/**
 * Interface for entities that store a name in several languages in the database.
 * Passed as a parameter to the custom tag localizable:name
 *
 * @author D.Koretskyi on 16.10.2020.
 */
public interface Localizable {

    public String getNameUk();

    public String getNameEn();

}
