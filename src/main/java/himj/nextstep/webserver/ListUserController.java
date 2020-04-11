package himj.nextstep.webserver;

import himj.nextstep.db.DataBase;
import himj.nextstep.model.User;
import himj.nextstep.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

//@WebServlet("/users")
public class ListUserController implements Controller {
    private static final Logger log =
            LoggerFactory.getLogger(ListUserController.class);
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
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/users/loginForm";
        }

        request.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
}
