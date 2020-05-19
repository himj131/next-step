package himj.nextstep.service;


import himj.nextstep.infra.AnswerDao;
import himj.nextstep.infra.QuestionDao;
import himj.nextstep.model.Answer;
import himj.nextstep.model.Question;
import himj.nextstep.model.User;

import java.sql.SQLException;
import java.util.List;

public class QnaService {
    private static QnaService qnaService;

    private QuestionDao questionDao;
    private AnswerDao answerDao;

    private QnaService(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    public static QnaService getInstance(QuestionDao questionDao, AnswerDao answerDao) {
        if (qnaService == null) {
            qnaService = new QnaService(questionDao, answerDao);
        }
        return qnaService;
    }

    public Question findById(long questionId) throws SQLException {
        return questionDao.findById(questionId);
    }

    public List<Answer> findAllByQuestionId(long questionId) throws SQLException {
        return answerDao.findAllByQuestionId(questionId);
    }

    public void deleteQuestion(long questionId, User user) throws CannotDeleteException, SQLException {
        Question question = questionDao.findById(questionId);
        if (question == null) {
            throw new CannotDeleteException("존재하지 않는 질문입니다.");
        }

        if (!question.isSameUser(user)) {
            throw new CannotDeleteException("다른 사용자가 쓴 글을 삭제할 수 없습니다.");
        }

        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
        if (answers.isEmpty()) {
            questionDao.delete(questionId);
            return;
        }

        boolean canDelete = true;
        for (Answer answer : answers) {
            String writer = question.getWriter();
            if (!writer.equals(answer.getWriter())) {
                canDelete = false;
                break;
            }
        }

        if (!canDelete) {
            throw new CannotDeleteException("다른 사용자가 추가한 댓글이 존재해 삭제할 수 없습니다.");
        }

        questionDao.delete(questionId);
    }
}
