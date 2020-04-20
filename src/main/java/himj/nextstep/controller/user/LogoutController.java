package himj.nextstep.controller.user;

import himj.nextstep.controller.Controller;
import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new JspView("redirect:/");
    }
}
