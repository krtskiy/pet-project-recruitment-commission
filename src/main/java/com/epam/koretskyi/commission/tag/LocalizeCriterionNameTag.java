package com.epam.koretskyi.commission.tag;

import com.epam.koretskyi.commission.db.entity.Criterion;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author D.Koretskyi on 15.10.2020.
 */
public class LocalizeCriterionNameTag extends TagSupport {
    private static final long serialVersionUID = -6730360161785711579L;

    private String currentLocaleName;
    private Criterion criterion;

    public String getCurrentLocaleName() {
        return currentLocaleName;
    }

    public void setCurrentLocaleName(String currentLocaleName) {
        this.currentLocaleName = currentLocaleName;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            if ("uk".equals(currentLocaleName)) {
                out.write(criterion.getNameUk());
            }
            if ("en".equals(currentLocaleName)) {
                out.write(criterion.getNameEn());
            }
            if ("".equals(currentLocaleName)) {
                out.write(criterion.getNameEn());
            }
        } catch (IOException e) {
            throw new JspException("Error in localizeCriterion tag", e);
        }

        return SKIP_BODY;
    }
}
