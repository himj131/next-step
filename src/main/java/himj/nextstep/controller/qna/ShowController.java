package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.AnswerDao;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long questionId = Long.valueOf(request.getParameter("questionId"));
        QuestionDao questionDao = QuestionDao.getInstance();
        AnswerDao answerDao = AnswerDao.getInstance();

        return jspView("/qna/show.jsp")
                .addObject("question", questionDao.findById(questionId))
                .addObject("answer", answerDao.findAllByQuestionId(questionId));
    }
}
