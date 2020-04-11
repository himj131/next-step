package himj.nextstep.webserver;

public class UserSessionUtils {
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
}
