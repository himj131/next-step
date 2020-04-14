package himj.nextstep.webserver;

import himj.nextstep.db.DataBase;
import himj.nextstep.infra.UserDao;
import himj.nextstep.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CreateUserController implements Controller {
    private static final Logger log =
            LoggerFactory.getLogger(CreateUserController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email")
        );

        UserDao userDao = new UserDao();
        try {
            userDao.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.debug("User: {}", user);

        return "redirect:/users";
    }
}
