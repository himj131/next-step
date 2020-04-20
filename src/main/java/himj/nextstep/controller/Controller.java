package himj.nextstep.controller;

import himj.nextstep.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
//    void service(HttpRequest request, HttpResponse response);
    View execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
