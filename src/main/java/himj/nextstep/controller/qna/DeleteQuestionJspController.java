package himj.nextstep.controller.qna;

import himj.nextstep.controller.Controller;
import himj.nextstep.mvc.ModelAndView;
import himj.nextstep.service.DeleteQuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionJspController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DeleteQuestionService service = new DeleteQuestionService();
        service.deleteQuestion(request);
        return jspView("redirect:/");
    }
}
