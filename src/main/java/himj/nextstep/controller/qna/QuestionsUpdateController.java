package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.model.Question;
import himj.nextstep.mvc.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionsUpdateController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(QuestionsUpdateController.class);

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        Question question = new Question(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents")
        );

        log.debug("question : {}", question);

        QuestionDao questionDao = QuestionDao.getInstance();
        questionDao.update(Long.parseLong(request.getParameter("questionId")), question);

        return jspView("redirect:/");
    }
}
