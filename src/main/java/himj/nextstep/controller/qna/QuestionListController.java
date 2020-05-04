package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.model.Question;
import himj.nextstep.model.User;
import himj.nextstep.mvc.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class QuestionListController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(QuestionListController.class);

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();
        return jsonView()
                .addObject("questoins", questions);
    }
}
