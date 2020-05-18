package himj.nextstep.service;

import himj.nextstep.infra.AnswerDao;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.model.Answer;
import himj.nextstep.model.Question;
import himj.nextstep.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteQuestionService {
    public void deleteQuestion(HttpServletRequest request) throws Exception {
        QuestionDao questionDao = QuestionDao.getInstance();
        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = questionDao.findById(questionId);
        User user = (User) request.getSession().getAttribute("user");

        if(!question.getWriter().equals(user.getName())) throw new RuntimeException();
        if(question.getCountOfComment() > 0) {
            AnswerDao answerDao = AnswerDao.getInstance();
            List<Answer> answers = answerDao.findAllByQuestionId(questionId);
            for(int i = 0; i < answers.size(); i ++) {
                if(!answers.get(i).getWriter().equals(question.getWriter())) throw new Exception();
            }
        }
        questionDao.deleteById(questionId);
    }
}
