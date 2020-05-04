package himj.nextstep.infra;

import himj.nextstep.model.Question;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    public List<Question> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
                + "order by questionId desc";

        RowMapper<Question> rowMapper = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
                rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
        return jdbcTemplate.queryForList(sql, rowMapper);
    }

    public Question findById(long questionId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
                + "WHERE questionId = ?";

        RowMapper<Question> rm = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }

    public Question insert(Question question) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
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
}
