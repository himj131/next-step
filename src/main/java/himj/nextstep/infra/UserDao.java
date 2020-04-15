package himj.nextstep.infra;

import himj.nextstep.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(sql);
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };

        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";
        jdbcTemplate.executeUpdate(sql);
    }

    public void delete(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
            }
        };
        String sql = "DELETE FROM USERS WHERE userid = ?";
        jdbcTemplate.executeUpdate(sql);
    }

    public List<User> findAll() throws SQLException {
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            protected Object mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }

            @Override
            protected void setParameters(PreparedStatement pstmt) {
            }
        };
        String sql = "SELECT * from USERS";
        return (List<User>)jdbcTemplate.queryForList(sql);
    }

    public User findByUserId(String userId) throws SQLException {
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            protected User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }

            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
        return (User)jdbcTemplate.queryForObject(sql);
    }
}
