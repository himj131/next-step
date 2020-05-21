package next.service;

import next.CannotDeleteException;
import next.dao.MockAnswerDao;
import next.dao.MockQuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static next.model.UserTest.newUser;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
    @Mock
    private MockQuestionDao mockQuestionDao;
    @Mock
    private MockAnswerDao mockAnswerDao;

    private QnaService qnaService;

    @BeforeEach
    void setUp() {
        qnaService = new QnaService(mockQuestionDao, mockAnswerDao);
    }

    @Test
    void delteQuestion_없는질문() {
        when(mockQuestionDao.findById(1L)).thenReturn(null);
        assertThrows(CannotDeleteException.class, () -> {
            qnaService.deleteQuestion(1L, newUser("userId"));
        });
    }

    @Test
    void deleteQuestion_다른사용자() {
        User user = newUser("mj");
        Question question = new Question(1L, user.getUserId(), "title",
                "contents", new Date(), 0) {
            public boolean canDelete(User user, List<Answer> answers) throws CannotDeleteException {
                throw new CannotDeleteException("삭제할 수 없음");
            };
        };
        when(mockQuestionDao.findById(1L)).thenReturn(question);
        assertThrows(CannotDeleteException.class, () -> {
            qnaService.deleteQuestion(1L, newUser("userId"));
        });
    }

    @Test
    void deleteQuestion() throws CannotDeleteException {
        User user = newUser("mj");
        Question question = new Question(1L, user.getUserId(), "title",
                "contents", new Date(), 0) {
            public boolean canDelete(User user, List<Answer> answers) throws CannotDeleteException{
                return true;
            };
        };
        when(mockQuestionDao.findById(1L)).thenReturn(question);
        qnaService.deleteQuestion(1L, newUser("mj"));
        verify(mockQuestionDao).delete(question.getQuestionId());
    }
}