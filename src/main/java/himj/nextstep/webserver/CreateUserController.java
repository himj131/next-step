package himj.nextstep.webserver;

import himj.nextstep.db.DataBase;
import himj.nextstep.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
    private static final Logger log =
            LoggerFactory.getLogger(CreateUserController.class);
//    @Override
//    public void service(HttpRequest request, HttpResponse response) {
//        User user = new User(
//                request.getParameter("userId"),
//                request.getParameter("password"),
//                request.getParameter("name"),
//                request.getParameter("email")
//        );
//
//        log.debug("User: {}", user);
//        DataBase.addUser(user);
//        response.sendRedirect("/index_.html");
//    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email")
        );

        log.debug("User: {}", user);
        DataBase.addUser(user);
        return "redirect:/";
    }
}
