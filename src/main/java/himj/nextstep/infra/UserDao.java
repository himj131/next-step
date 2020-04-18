package himj.nextstep.infra;

import himj.nextstep.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";
        jdbcTemplate.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
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
        String sql = "SELECT * from USERS";
        return jdbcTemplate.queryForList(sql, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        RowMapper<User> mapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
        return jdbcTemplate.queryForObject(sql, mapper, userId);
    }
}
