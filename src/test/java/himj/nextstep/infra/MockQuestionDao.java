package himj.nextstep.infra;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import himj.nextstep.model.Question;

import java.util.List;
import java.util.Map;

public class MockQuestionDao implements QuestionDao {
    private Map<Long, Question> questions = Maps.newHashMap();

    @Override
    public Question insert(Question question) {
        return questions.put(question.getQuestionId(), question);
    }

    @Override
    public void updateAnswerCount(Question question) {

    }

    @Override
    public void update(long questionId, Question question) {

    }

    @Override
    public void deleteById(long questionId) {

    }

    @Override
    public List<Question> findAll() {
        return Lists.newArrayList(questions.values());
    }

    @Override
    public Question findById(long questionId) {
        return questions.get(questionId);
    }

    @Override
    public void delete(long questionId) {
        questions.remove(questionId);
    }
}
