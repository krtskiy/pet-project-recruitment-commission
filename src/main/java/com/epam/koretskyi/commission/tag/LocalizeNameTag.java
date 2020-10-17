package com.epam.koretskyi.commission.tag;

import com.epam.koretskyi.commission.db.entity.Localizable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag class.
 * Tag prints the name of the entity on the page in accordance with the current locale.
 * Entity must implement <code>Localizable</code> interface.
 *
 * @author D.Koretskyi on 15.10.2020.
 * @see com.epam.koretskyi.commission.db.entity.Localizable
 */
public class LocalizeNameTag extends TagSupport {
    private static final long serialVersionUID = -7532173997044764508L;

    private String currentLocaleName;
    private Localizable localizable;

    public String getCurrentLocaleName() {
        return currentLocaleName;
    }

    public void setCurrentLocaleName(String currentLocaleName) {
        this.currentLocaleName = currentLocaleName;
    }

    public Localizable getLocalizable() {
        return localizable;
    }

    public void setLocalizable(Localizable localizable) {
        this.localizable = localizable;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            if ("uk".equals(currentLocaleName)) {
                out.write(localizable.getNameUk());
            }
            if ("en".equals(currentLocaleName)) {
                out.write(localizable.getNameEn());
            }
            if ("".equals(currentLocaleName)) {
                out.write(localizable.getNameEn());
            }
        } catch (IOException e) {
            throw new JspException("Error in Localizer tag", e);
        }

        return SKIP_BODY;
    }


}
