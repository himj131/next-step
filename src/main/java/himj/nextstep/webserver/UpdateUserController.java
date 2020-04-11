package himj.nextstep.webserver;

import himj.nextstep.db.DataBase;
import himj.nextstep.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        DataBase.addUser(user);

        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("user");

        if(loginedUser.getUserId().equals(user.getUserId())) {
            return "redirect:/users";
        } else {
            throw new IllegalArgumentException("올바른 사용자가 아닙니다!");
        }
    }
}
