package himj.nextstep.controller.user;

import himj.nextstep.controller.Controller;
import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormUserController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new JspView("/user/updateForm.jsp");
    }
}
