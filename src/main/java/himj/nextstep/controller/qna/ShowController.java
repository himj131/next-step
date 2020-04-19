package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.AnswerDao;
import himj.nextstep.infra.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long questionId = Long.valueOf(request.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        request.setAttribute("question", questionDao.findById(questionId));
        request.setAttribute("answer", answerDao.findAllByQuestionId(questionId));

        return "/qna/show.jsp";
    }
}
