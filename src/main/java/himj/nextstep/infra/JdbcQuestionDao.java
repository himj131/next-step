package himj.nextstep.infra;

import himj.nextstep.model.Question;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class JdbcQuestionDao implements QuestionDao {
    private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    private static JdbcQuestionDao jdbcQuestionDao;

    private JdbcQuestionDao() {
    }

    public static JdbcQuestionDao getInstance() {
        if (jdbcQuestionDao == null) {
            jdbcQuestionDao = new JdbcQuestionDao();
        }
        return jdbcQuestionDao;
    }

    @Override
    public List<Question> findAll() throws SQLException {
        String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
                + "order by questionId desc";

        RowMapper<Question> rowMapper = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
                rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
        return jdbcTemplate.queryForList(sql, rowMapper);
    }

    @Override
    public Question findById(long questionId) throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
                + "WHERE questionId = ?";

        RowMapper<Question> rm = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }

    @Override
    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents," +
                "createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, new Timestamp(question.getTimeFromCreateDate()));
            pstmt.setInt(5, 0);
            return pstmt;
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.executeUpdate(psc, keyHolder);
        return findById(keyHolder.getId());
    }

    @Override
    public void updateAnswerCount(Question question) {
        String sql = "UPDATE QUESTIONS set countOfAnswer = ? where questionId = ?";
        jdbcTemplate.executeUpdate(sql, question.getCountOfComment() + 1, question.getQuestionId());
    }

    @Override
    public void update(long questionId, Question question) {
        String sql = "UPDATE QUESTIONS set writer = ?, title = ?, contents = ? where questionId = ?";
        jdbcTemplate.executeUpdate(sql,
                                        question.getWriter(),
                                        question.getTitle(),
                                        question.getContents(),
                                        questionId);
    }

    @Override
    public void deleteById(long questionId) {
        String sql = "DELETE QUESTIONS where questionId = ?";
        jdbcTemplate.executeUpdate(sql, questionId);
    }

    @Override
    public void delete(long questionId) {
        String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
        jdbcTemplate.executeUpdate(sql, questionId);
    }
}
