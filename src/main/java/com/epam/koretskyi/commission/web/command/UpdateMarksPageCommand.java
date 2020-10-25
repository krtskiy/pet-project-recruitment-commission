package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author D.Koretskyi on 26.10.2020.
 */
public class UpdateMarksPageCommand extends Command {
    private static final long serialVersionUID = 5890080508129431146L;

    private static final Logger LOG = Logger.getLogger(UpdateMarksPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        User user = (User) request.getSession().getAttribute("user");
        List<UserMarksBean> userMarks = DBManager.getInstance().findUserMarksByUserId(user.getId());
        request.setAttribute("userMarks", userMarks);
        LOG.trace("Set the request attribute: userMarks --> " + userMarks);

        LOG.debug("Command finished");
        return Path.PAGE_UPDATE_MARKS;
    }
}
