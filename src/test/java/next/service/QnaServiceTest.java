package next.service;

import com.google.common.collect.Lists;
import next.CannotDeleteException;
import next.dao.MockAnswerDao;
import next.dao.MockQuestionDao;
import next.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static next.model.QuestionTest.newQuestion;
import static next.model.UserTest.newUser;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Question question = newQuestion(1L, "mj");
        when(mockQuestionDao.findById(1L)).thenReturn(question);
        assertThrows(CannotDeleteException.class, () -> {
            qnaService.deleteQuestion(1L, newUser("userId"));
        });
    }

    @Test
    void deleteQuestion() throws CannotDeleteException {
        Question question = newQuestion(1L, "mj");
        when(mockQuestionDao.findById(1L)).thenReturn(question);
        qnaService.deleteQuestion(1L, newUser("mj"));
    }
}