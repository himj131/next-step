package himj.nextstep.infra;

import himj.nextstep.config.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void executeUpdate(String sql, PreparedStatementSetter preparedStatementSetter) throws DataAccessException {
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            preparedStatementSetter.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }


    public <T> List<T> queryForList(String sql,
                             PreparedStatementSetter preparedStatementSetter,
                             RowMapper<T> rowMapper) throws SQLException {
        ResultSet rs;
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            preparedStatementSetter.setValues(pstmt);

            rs = pstmt.executeQuery();
            List<T> results = new ArrayList<>();
            while(rs.next()) {
                results.add(rowMapper.mapRow(rs));
            }
            return results;
        }
    }

    public <T> T queryForObject(String sql,
                                 PreparedStatementSetter preparedStatementSetter,
                                 RowMapper<T> rowMapper) throws SQLException {
        List<T> results =  queryForList(sql, preparedStatementSetter, rowMapper);
        if(results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }
}
