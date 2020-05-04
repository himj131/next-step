package himj.nextstep.controller;

import himj.nextstep.infra.QuestionDao;
import himj.nextstep.model.Question;
import himj.nextstep.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class HomeController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();

        return jspView("home.jsp")
                .addObject("questions", questions);
    }
}
