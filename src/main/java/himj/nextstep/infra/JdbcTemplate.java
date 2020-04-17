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


    public List queryForList(String sql,
                             PreparedStatementSetter preparedStatementSetter,
                             RowMapper rowMapper) throws SQLException {
        ResultSet rs;
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            preparedStatementSetter.setValues(pstmt);

            rs = pstmt.executeQuery();
            List<Object> results = new ArrayList<>();
            while(rs.next()) {
                results.add(rowMapper.mapRow(rs));
            }
            return results;
        }
    }

    public Object queryForObject(String sql,
                                 PreparedStatementSetter preparedStatementSetter,
                                 RowMapper rowMapper) throws SQLException {
        List results =  queryForList(sql, preparedStatementSetter, rowMapper);
        if(results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }
}
