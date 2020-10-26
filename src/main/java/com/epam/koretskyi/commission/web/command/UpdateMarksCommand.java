package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.util.validation.MarksValidation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author D.Koretskyi on 26.10.2020.
 */
public class UpdateMarksCommand extends Command {
    private static final long serialVersionUID = 8353542198121062248L;

    private static final Logger LOG = Logger.getLogger(UpdateMarksCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        User user = (User) request.getSession().getAttribute("user");
        List<UserMarksBean> userMarks = DBManager.getInstance().findUserMarksByUserId(user.getId());

        for (UserMarksBean mark : userMarks) {
            String newMarkStr = request.getParameter(String.valueOf(mark.getCriterionId()));
            if (!StringUtils.isBlank(newMarkStr)) {
                int newMark = Integer.parseInt(newMarkStr);
                if (!MarksValidation.isMarkPositiveValidation(newMark) ||
                        !MarksValidation.markRangeValidation(newMark, mark.getCriterionId())) {
                    throw new AppException("Marks are not in the correct format!");
                }
                mark.setMark(newMark);
            }
        }

        DBManager.getInstance().updateUserMarks(userMarks, user.getId());

        LOG.debug("Command finished");
        return Path.COMMAND_PRIVATE_OFFICE;
    }
}
