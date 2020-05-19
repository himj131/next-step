package himj.nextstep.infra;

import himj.nextstep.model.Answer;

import java.sql.SQLException;
import java.util.List;

public interface AnswerDao {
    Answer insert(Answer answer) throws SQLException;

    Answer findById(long answerId) throws SQLException;

    List<Answer> findAllByQuestionId(long questionId) throws SQLException;

    long deleteAnswer(long answerId);
}
