//package himj.nextstep.controller.qna;
//
//import himj.nextstep.controller.Controller;
//import himj.nextstep.infra.JdbcQuestionDao;
//import himj.nextstep.model.Question;
//import himj.nextstep.mvc.ModelAndView;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class AddQuestionController implements Controller {
//    private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);
//
//    @Override
//    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Question question = new Question(
//                request.getParameter("writer"),
//                request.getParameter("title"),
//                request.getParameter("contents")
//        );
//        log.debug("question : {}", question);
//
//        JdbcQuestionDao questionDao = JdbcQuestionDao.getInstance();
//        questionDao.insert(question);
//
//        return jspView("redirect:/");
//    }
//}
