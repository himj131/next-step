package himj.nextstep.webserver;

import himj.nextstep.model.User;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "user";

    public static boolean isLogined(HttpSession session) {
        Object user = session.getAttribute("user");
        if(user == null) return false;
        return true;
    }

    public static boolean isLogined(javax.servlet.http.HttpSession session) {
        Object user = session.getAttribute("user");
        if(user == null) return false;
        return true;
    }

    public static boolean isSameUser(javax.servlet.http.HttpSession session, User user) {
        if (!isLogined(session)) {
            return false;
        }

        if (user == null) {
            return false;
        }

        return user.isSameUser(getUserFromSession(session));
    }

    public static User getUserFromSession(javax.servlet.http.HttpSession session) {
        Object user = session.getAttribute(USER_SESSION_KEY);
        if (user == null) {
            return null;
        }
        return (User) user;
    }
}
