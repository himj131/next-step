package himj.nextstep.controller.user;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.UserDao;
import himj.nextstep.model.User;
import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    UserDao userDao = new UserDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        User user = userDao.findByUserId(request.getParameter("userId"));
        if(user != null) {
            if(user.login(request.getParameter("password"))){
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return new JspView("redirect:/users");
            } else {
                return new JspView("/user/login_failed.jsp");
            }
        } else {
            return new JspView("/user/login_failed.jsp");
        }
    }
}
