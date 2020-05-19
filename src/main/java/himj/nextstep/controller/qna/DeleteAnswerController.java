//package himj.nextstep.controller.qna;
//
//import himj.nextstep.controller.Controller;
//import himj.nextstep.infra.AnswerDao;
//import himj.nextstep.model.Result;
//import himj.nextstep.mvc.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class DeleteAnswerController implements Controller {
//    @Override
//    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        AnswerDao answerDao = AnswerDao.getInstance();
//        Result result;
//        try {
//            answerDao.deleteAnswer(Long.parseLong(request.getParameter("answerId")));
//            result = Result.ok();
//        } catch (Exception e){
//            result = Result.fail("fail");
//        }
//
//        return jsonView()
//                .addObject("status", result.isStatus())
//                .addObject("message", result.getMessage());
//    }
//}
