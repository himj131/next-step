package himj.nextstep.infra;

import himj.nextstep.model.Answer;

import java.sql.*;
import java.util.List;

public class AnswerDao {
    public Answer insert(Answer answer) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setTimestamp(3, new Timestamp(answer.getTimeFromCreateDate()));
            pstmt.setLong(4, answer.getQuestionId());
            return pstmt;
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.executeUpdate(psc, keyHolder);
        return findById(keyHolder.getId());
    }

    public Answer findById(long answerId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getLong("questionId"));
            }
        };

        return jdbcTemplate.queryForObject(sql, rm, answerId);
    }

    public List<Answer> findAllByQuestionId(long questionId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
                + "order by answerId desc";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        questionId);
            }
        };

        return jdbcTemplate.queryForList(sql, rm, questionId);
    }
}
