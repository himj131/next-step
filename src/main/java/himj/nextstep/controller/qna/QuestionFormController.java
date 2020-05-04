package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.model.Question;
import himj.nextstep.model.User;
import himj.nextstep.mvc.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuestionFormController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(QuestionFormController.class);

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) return jspView("redirect:/users/loginForm");

        return jspView("/qna/form.jsp")
                .addObject("user", user);
    }
}
