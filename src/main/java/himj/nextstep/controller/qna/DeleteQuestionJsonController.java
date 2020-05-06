package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.AnswerDao;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.model.Answer;
import himj.nextstep.model.Question;
import himj.nextstep.model.Result;
import himj.nextstep.model.User;
import himj.nextstep.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteQuestionJsonController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = questionDao.findById(questionId);
        User user = (User) request.getSession().getAttribute("user");
        Result result = new Result(true, "deleted nothing");

        if(!question.getWriter().equals(user.getName())) throw new RuntimeException();
        if(question.getCountOfComment() > 0) {
            AnswerDao answerDao = new AnswerDao();
            List<Answer> answers = answerDao.findAllByQuestionId(questionId);
            for(int i = 0; i < answers.size(); i ++) {
                if(!answers.get(i).getWriter().equals(question.getWriter())) throw new Exception();
            }

            try {
                questionDao.deleteById(questionId);
                result = Result.ok();
            } catch (Exception e){
                result = Result.fail("fail");
            }
        }

        return jsonView()
                .addObject("status", result.isStatus())
                .addObject("message", result.getMessage());
    }
}
