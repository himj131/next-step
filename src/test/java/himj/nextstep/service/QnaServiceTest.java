package himj.nextstep.service;

import himj.nextstep.infra.MockAnswerDao;
import himj.nextstep.infra.MockQuestionDao;
import himj.nextstep.model.Question;
import himj.nextstep.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QnaServiceTest {
    private QnaService qnaService;
    private MockQuestionDao questionDao;
    private MockAnswerDao answerDao;

    @BeforeEach
    void setUp() {
        questionDao = new MockQuestionDao();
        answerDao = new MockAnswerDao();
        qnaService = QnaService.getInstance(questionDao, answerDao);
    }

    @Test
    public void deleteQuestion_없는_질문() {
        assertThrows(CannotDeleteException.class, () -> {
            qnaService.deleteQuestion(1L, getUser("userId"));
        });
    }

    @Test
    public void deleteQuestion_다른사용자() throws Exception, CannotDeleteException {
        Question question = new Question(1L,"id", "title", "contents", new Date(), 0);
        questionDao.insert(question);

        assertThrows(CannotDeleteException.class, () ->{
            qnaService.deleteQuestion(1L, getUser("id2"));
        });
    }

    @Test
    public void deleteQuestion_삭제() throws SQLException, CannotDeleteException {
        Question question = new Question(1L,"id", "title", "contents", new Date(), 0);
        questionDao.insert(question);
        qnaService.deleteQuestion(1L, getUser("id"));
    }

    private User getUser(String id) {
        return new User(id, "pwd", "name", "mail@mail.com");
    }
}