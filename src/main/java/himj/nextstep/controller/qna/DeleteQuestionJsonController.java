package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.model.Result;
import himj.nextstep.mvc.ModelAndView;
import himj.nextstep.service.DeleteQuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionJsonController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new Result(true, "deleted nothing");
        DeleteQuestionService service = new DeleteQuestionService();
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
