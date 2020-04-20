package himj.nextstep.controller;

import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new JspView("redirect:index.jsp");
    }
}
