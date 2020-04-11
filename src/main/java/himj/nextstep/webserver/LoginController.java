package himj.nextstep.webserver;

import himj.nextstep.db.DataBase;
import himj.nextstep.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = DataBase.findUserById(request.getParameter("userId"));
        if(user != null) {
            if(user.login(request.getParameter("password"))){
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return "redirect:/users";
            } else {
                return "/user/login_failed.html";
            }
        } else {
            return "/user/login_failed.html";
        }
    }
}
