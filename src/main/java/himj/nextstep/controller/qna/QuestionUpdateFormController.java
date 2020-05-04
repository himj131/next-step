package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.infra.UserDao;
import himj.nextstep.model.Question;
import himj.nextstep.model.User;
import himj.nextstep.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionUpdateFormController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        UserDao userDao = new UserDao();

        User user = (User) request.getSession().getAttribute("user");
        if(user == null) return jspView("redirect:/users/loginForm");

        User loginedUser = userDao.findByUserId(user.getUserId());

        Question question = questionDao.findById(Long.parseLong(request.getParameter("questionId")));

        if(!question.getWriter().equals(loginedUser.getName())) {
            return jspView("redirect:/users/loginForm");
        }
        return jspView("/qna/updateForm.jsp")
                .addObject("question", question);
    }
}
