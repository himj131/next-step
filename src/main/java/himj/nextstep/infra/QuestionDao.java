package himj.nextstep.infra;

import himj.nextstep.model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDao {
    List<Question> findAll() throws SQLException;

    Question findById(long questionId) throws SQLException;

    Question insert(Question question) throws SQLException;

    void updateAnswerCount(Question question);

    void update(long questionId, Question question);

    void deleteById(long questionId);

    void delete(long questionId);
}
