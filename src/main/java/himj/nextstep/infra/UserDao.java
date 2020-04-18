package himj.nextstep.infra;

import himj.nextstep.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter setter = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(sql, setter);
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter setter = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";
        jdbcTemplate.executeUpdate(sql, setter);
    }

    public void delete(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter setter = pstmt -> pstmt.setString(1, user.getUserId());
        String sql = "DELETE FROM USERS WHERE userid = ?";
        jdbcTemplate.executeUpdate(sql, setter);
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        RowMapper<User> rowMapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );

        PreparedStatementSetter setter = pstmt -> {
        };
        String sql = "SELECT * from USERS";
        return jdbcTemplate.queryForList(sql, setter, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        RowMapper<User> mapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );

        PreparedStatementSetter setter = pstmt -> pstmt.setString(1, userId);
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
        return jdbcTemplate.queryForObject(sql, setter, mapper);
    }
}
