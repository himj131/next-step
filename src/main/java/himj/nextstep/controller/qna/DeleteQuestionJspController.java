package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.AnswerDao;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.model.Answer;
import himj.nextstep.model.Question;
import himj.nextstep.model.User;
import himj.nextstep.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteQuestionJspController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = questionDao.findById(questionId);
        User user = (User) request.getSession().getAttribute("user");

        if(!question.getWriter().equals(user.getName())) throw new RuntimeException();
        if(question.getCountOfComment() > 0) {
            AnswerDao answerDao = new AnswerDao();
            List<Answer> answers = answerDao.findAllByQuestionId(questionId);
            for(int i = 0; i < answers.size(); i ++) {
                if(!answers.get(i).getWriter().equals(question.getWriter())) throw new Exception();
            }

            questionDao.deleteById(questionId);
        }
        return jspView("redirect:/");
    }
}
