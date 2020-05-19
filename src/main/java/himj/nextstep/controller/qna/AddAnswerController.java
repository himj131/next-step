//package himj.nextstep.controller.qna;
//
//import himj.nextstep.controller.Controller;
//import himj.nextstep.infra.AnswerDao;
//import himj.nextstep.infra.JdbcQuestionDao;
//import himj.nextstep.model.Answer;
//import himj.nextstep.model.Question;
//import himj.nextstep.mvc.ModelAndView;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class AddAnswerController implements Controller {
//    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
//
//    @Override
//    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Answer answer = new Answer(
//                request.getParameter("writer"),
//                request.getParameter("contents"),
//                Long.parseLong(request.getParameter("questionId"))
//        );
//        log.debug("answer : {}", answer);
//
//        AnswerDao answerDao = AnswerDao.getInstance();
//        Answer savedAnswer = answerDao.insert(answer);
//
//        JdbcQuestionDao questionDao = JdbcQuestionDao.getInstance();
//        Question question = questionDao.findById(Long.parseLong(request.getParameter("questionId")));
//        questionDao.updateAnswerCount(question);
//
//        return jsonView()
//                .addObject("answer", savedAnswer);
//    }
//}
