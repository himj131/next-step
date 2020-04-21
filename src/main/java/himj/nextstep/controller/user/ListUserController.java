package himj.nextstep.controller.user;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.UserDao;
import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.ModelAndView;
import himj.nextstep.mvc.View;
import himj.nextstep.webserver.UserSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ListUserController implements Controller {
    UserDao userDao = new UserDao();
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

        return jspView("/user/list.jsp")
                .addObject("users", userDao.findAll());
    }
}
