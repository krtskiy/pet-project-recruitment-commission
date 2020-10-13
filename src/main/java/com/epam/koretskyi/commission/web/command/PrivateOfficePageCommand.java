package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.bean.UserFacultiesBean;
import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.*;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author D.Koretskyi on 01.10.2020.
 */
public class PrivateOfficePageCommand extends Command {
    private static final long serialVersionUID = -4290326623176370114L;

    private static final Logger LOG = Logger.getLogger(PrivateOfficePageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        List<UserMarksBean> userMarks = manager.findUserMarksByUserId(userId);
        request.setAttribute("userMarks", userMarks);
        LOG.trace("Set the request attribute: userMarks --> " + userMarks);

        List<UserFacultiesBean> userFaculties = manager.findUserFacultiesByUserId(userId);
        request.setAttribute("userFaculties", userFaculties);
        LOG.trace("Set the request attribute: userFaculties --> " + userFaculties);

        LOG.debug("Command finished");
        return Path.PAGE_PRIVATE_OFFICE;
    }
}
