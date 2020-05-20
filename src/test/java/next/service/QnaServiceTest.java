package next.service;

import next.CannotDeleteException;
import next.dao.MockAnswerDao;
import next.dao.MockQuestionDao;
import next.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static next.model.QuestionTest.newQuestion;
import static next.model.UserTest.newUser;
import static org.junit.jupiter.api.Assertions.*;

class QnaServiceTest {
    private QnaService qnaService;
    private MockQuestionDao mockQuestionDao;
    private MockAnswerDao mockAnswerDao;

    @BeforeEach
    void setUp() {
        mockQuestionDao = new MockQuestionDao();
        mockAnswerDao = new MockAnswerDao();
        qnaService = new QnaService(mockQuestionDao, mockAnswerDao);
    }

    @Test
    void delteQuestion_없는질문() {
        assertThrows(CannotDeleteException.class, () -> {
            qnaService.deleteQuestion(1L, newUser("userId"));
        });
    }

    @Test
    void deleteQuestion_다른사용자() {
        Question quesiton = newQuestion(1L, "mj");
        mockQuestionDao.insert(quesiton);
        assertThrows(CannotDeleteException.class, () -> {
            qnaService.deleteQuestion(1L, newUser("userId"));
        });
    }

    @Test
    void deleteQuestion() throws CannotDeleteException {
        Question quesiton = newQuestion(1L, "mj");
        mockQuestionDao.insert(quesiton);
        qnaService.deleteQuestion(1L, newUser("mj"));
    }
}