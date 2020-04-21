package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.infra.AnswerDao;
import himj.nextstep.model.Result;
import himj.nextstep.mvc.JsonView;
import himj.nextstep.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAnswerController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AnswerDao answerDao = new AnswerDao();
        Result result;
        try {
            answerDao.deleteAnswer(Long.parseLong(request.getParameter("answerId")));
            result = Result.ok();
        } catch (Exception e){
            result = Result.fail("fail");
        }
        setAttribute(request, result);

        return new JsonView();
    }

    private void setAttribute(HttpServletRequest request, Result result) {
        request.setAttribute("status", result.isStatus());
        request.setAttribute("message", result.getMessage());
    }
}
