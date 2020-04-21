package himj.nextstep.controller.user;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.UserDao;
import himj.nextstep.model.User;
import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.ModelAndView;
import himj.nextstep.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserController implements Controller {
    UserDao userDao = new UserDao();
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        userDao.update(user);

        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("user");

        if(loginedUser.getUserId().equals(user.getUserId())) {
            return jspView("redirect:/users");
        } else {
            throw new IllegalArgumentException("올바른 사용자가 아닙니다!");
        }
    }
}
