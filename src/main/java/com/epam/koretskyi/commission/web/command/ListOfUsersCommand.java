package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Entity;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author D.Koretskyi on 02.10.2020.
 */
public class ListOfUsersCommand extends Command {
    private static final long serialVersionUID = -4846815477949543290L;

    private static final Logger LOG = Logger.getLogger(ListOfUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        // get users list
        List<User> users = DBManager.getInstance().findAllUsers();
        LOG.trace("Found in DB: count of users --> " + users.size());


        String sortParam = request.getParameter("sort");
        request.setAttribute("sortParam", sortParam);
        // sort users by id
        if ("id".equals(sortParam)) {
            users.sort(Comparator.comparingInt(Entity::getId));
            LOG.trace("Users sorted by id in ascending order");
        }
        // define collator to sort by Ukrainian alphabet correctly
        Collator uaCollator = Collator.getInstance(new Locale("uk", "UA"));
        // sort users by name asc
        if ("nameAsc".equals(sortParam)) {
            users.sort((o1, o2) -> uaCollator.compare(o1.getName(), o2.getName()));
            LOG.trace("Users sorted by name in ascending order");
        }
        // sort users by name desc
        if ("nameDesc".equals(sortParam)) {
            users.sort((o1, o2) -> uaCollator.compare(o2.getName(), o1.getName()));
            LOG.trace("Users sorted by name in descending order");
        }
        // sort users by email asc
        if ("emailAsc".equals(sortParam)) {
            users.sort(Comparator.comparing(User::getEmail));
            LOG.trace("Users sorted by email in ascending order");
        }
        // sort users by email desc
        if ("emailDesc".equals(sortParam)) {
            users.sort(Comparator.comparing(User::getEmail).reversed());
            LOG.trace("Users sorted by email in descending order");
        }
        // banned first
        if ("banStatus".equals(sortParam)) {
            users.sort(Comparator.comparingInt(User::getStatusId).reversed());
        }

        // split users across multiple pages (5 per page)
        int totalPages = users.size() / 5;
        if ((users.size() % 5) != 0) {
            totalPages += 1;
        }
        request.setAttribute("totalPages", totalPages);

        int limit = Integer.parseInt(request.getParameter("page"));
        if (limit == 1) {
            users = users.stream().skip(0).limit(5).collect(Collectors.toList());
        }
        users = users.stream().skip((limit - 1) * 5).limit(5).collect(Collectors.toList());

        // put users list to the request
        request.setAttribute("users", users);
        LOG.trace("Set the request attribute: users --> " + users);

        LOG.debug("Command finished");

        return Path.PAGE_ADMIN_USERS;
    }
}
