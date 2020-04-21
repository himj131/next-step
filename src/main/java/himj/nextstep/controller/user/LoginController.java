package himj.nextstep.controller.user;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.UserDao;
import himj.nextstep.model.User;
import himj.nextstep.mvc.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginController implements Controller {
    UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        User user = userDao.findByUserId(request.getParameter("userId"));
        if(user != null) {
            if(user.login(request.getParameter("password"))){
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return jspView("redirect:/users");
            } else {
                return jspView("/user/login_failed.jsp");
            }
        } else {
            return jspView("/user/login_failed.jsp");
        }
    }
}
