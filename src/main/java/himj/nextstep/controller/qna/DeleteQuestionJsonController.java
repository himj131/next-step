package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.model.Result;
import himj.nextstep.mvc.ModelAndView;
import himj.nextstep.service.DeleteQuestionService;
import himj.nextstep.service.QnaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionJsonController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new Result(true, "deleted nothing");
        //DeleteQuestionService service = new DeleteQuestionService();
        //QnaService service = QnaService.getInstance();
        QnaService service = QnaService.getInstance(QuestionDao.getInstance(), AnswerDao.getInstance());

        try {
            service.deleteQuestion(request);
        } catch (Exception e){
            result = Result.fail("fail");
        }

        return jsonView()
                .addObject("status", result.isStatus())
                .addObject("message", result.getMessage());
    }
}
