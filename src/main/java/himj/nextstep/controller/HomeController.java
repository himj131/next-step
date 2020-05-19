//package himj.nextstep.controller;
//
//import himj.nextstep.infra.JdbcQuestionDao;
//import himj.nextstep.model.Question;
//import himj.nextstep.mvc.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.sql.SQLException;
//import java.util.List;
//
//public class HomeController implements Controller {
//
//    @Override
//    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
//        JdbcQuestionDao questionDao = JdbcQuestionDao.getInstance();
//        List<Question> questions = questionDao.findAll();
//
//        return jspView("home.jsp")
//                .addObject("questions", questions);
//    }
//}
