package himj.nextstep.controller.user;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.UserDao;
import himj.nextstep.webserver.UserSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

//@WebServlet("/users")
public class ListUserController implements Controller {
    private static final Logger log =
            LoggerFactory.getLogger(ListUserController.class);
    UserDao userDao = new UserDao();
//    @Override
//    public vgoid service(HttpRequest request, HttpResponse response) {
//        if(!isLogin(request.getSession())) {
//            response.sendRedirect("/user/login.html");
//            return;
//        }
//
//        Collection<User> users = DataBase.findAll();
//        StringBuilder sb = new StringBuilder();
//        sb.append("<table>");
//        for(User user: users) {
//            sb.append("<tr>");
//            sb.append("<td>"+user.getUserId()+"</td>");
//            sb.append("<td>"+user.getName()+"</td>");
//            sb.append("<td>"+user.getEmail()+"</td>");
//            sb.append("</tr>");
//        }
//        sb.append("</table>");
//
//        response.forwardBody(sb.toString());
//    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/users/loginForm";
        }

        request.setAttribute("users", userDao.findAll());
        return "/user/list.jsp";
    }
}
