package com.epam.koretskyi.commission.tag;

import com.epam.koretskyi.commission.db.entity.Faculty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author D.Koretskyi on 15.10.2020.
 */
public class LocalizeFacultyNameTag extends TagSupport {
    private static final long serialVersionUID = -7532173997044764508L;

    private String currentLocaleName;
    private Faculty faculty;

    public String getCurrentLocaleName() {
        return currentLocaleName;
    }

    public void setCurrentLocaleName(String currentLocaleName) {
        this.currentLocaleName = currentLocaleName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            if ("uk".equals(currentLocaleName)) {
                out.write(faculty.getNameUk());
            }
            if ("en".equals(currentLocaleName)) {
                out.write(faculty.getNameEn());
            }
            if ("".equals(currentLocaleName)) {
                out.write(faculty.getNameEn());
            }
        } catch (IOException e) {
            throw new JspException("Error in localizeFaculty tag", e);
        }

        return SKIP_BODY;
    }


}
