package himj.nextstep.controller;

import himj.nextstep.mvc.JsonView;
import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
//    void service(HttpRequest request, HttpResponse response);
    ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

    default ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }

    default ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
