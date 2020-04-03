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
        if(!isLogin(request.getHeader("Cookie"))) {
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

    private boolean isLogin(String line) {
        String[] headerTokens = line.split(":");
        Map<String, String> cookies = HttpRequestUtils.parseCookies(headerTokens[1].trim());
        String value = cookies.get("loginId");
        if(value == null) {
            return false;
        }
        return Boolean.parseBoolean(value);
    }
}
