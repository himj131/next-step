package himj.nextstep.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
//    void service(HttpRequest request, HttpResponse response);
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
