package himj.nextstep.webserver;

import himj.nextstep.db.DataBase;
import himj.nextstep.model.User;
import himj.nextstep.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

public class ListUserController implements Controller {
    private static final Logger log =
            LoggerFactory.getLogger(ListUserController.class);
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        if(!isLogin(request.getSession())) {
            response.sendRedirect("/user/login.html");
            return;
        }

        Collection<User> users = DataBase.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        for(User user: users) {
            sb.append("<tr>");
            sb.append("<td>"+user.getUserId()+"</td>");
            sb.append("<td>"+user.getName()+"</td>");
            sb.append("<td>"+user.getEmail()+"</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");

        response.forwardBody(sb.toString());
    }

    private boolean isLogin(HttpSession session) {
        Object user = session.getAttribute("user");
        if(user == null) return false;
        return true;
    }
}
