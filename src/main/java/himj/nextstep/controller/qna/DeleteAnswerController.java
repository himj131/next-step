package himj.nextstep.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import himj.nextstep.controller.Controller;
import himj.nextstep.infra.AnswerDao;
import himj.nextstep.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AnswerDao answerDao = new AnswerDao();
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            answerDao.deleteAnswer(Long.parseLong(request.getParameter("answerId")));
            out.print(mapper.writeValueAsString(Result.ok()));
        } catch (Exception e){
            out.print(mapper.writeValueAsString( Result.fail("fail")));
        }
        return null;
    }
}
