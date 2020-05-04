package himj.nextstep.webserver;

import himj.nextstep.controller.*;
import himj.nextstep.controller.qna.*;
import himj.nextstep.controller.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private static Map<String, Controller> mappings = new HashMap<String, Controller>();
    void initMapping(){
        mappings.put("/", new HomeController());

        /** user */
        mappings.put("/users/form", new ForwardController("/user/createForm.jsp"));
        mappings.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        mappings.put("/users", new ListUserController());
        mappings.put("/users/login", new LoginController());
        mappings.put("/users/profile", new ProfileController());
        mappings.put("/users/logout", new LogoutController());
        mappings.put("/users/create", new CreateUserController());
        mappings.put("/users/updateForm", new UpdateFormUserController());
        mappings.put("/users/update", new UpdateUserController());

        /** qna */
        mappings.put("/qna/show", new ShowController());
        mappings.put("/qna/form", new QuestionFormController());
        mappings.put("/qna/updateForm", new QuestionUpdateFormController());
        mappings.put("/api/qna/questions", new QuestionListController());
        mappings.put("/api/qna/addQuestion", new AddQuestionController());
        mappings.put("/api/qna/updateQuestion", new QuestionsUpdateController());
        mappings.put("/api/qna/addAnswer", new AddAnswerController());
        mappings.put("/api/qna/deleteAnswer", new DeleteAnswerController());
        logger.info("Initialized Request Mapping!");
    }

    public static Controller findController(String url) {
        return mappings.get(url);
    }

    void put(String url, Controller controller) {
        mappings.put(url, controller);
    }
}
