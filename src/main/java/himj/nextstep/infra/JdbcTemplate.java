package himj.nextstep.infra;

import himj.nextstep.config.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void executeUpdate(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            preparedStatementSetter.setValues(pstmt);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }


    public List queryForList(String sql,
                             PreparedStatementSetter preparedStatementSetter,
                             RowMapper rowMapper) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            preparedStatementSetter.setValues(pstmt);

            rs = pstmt.executeQuery();
            List<Object> results = new ArrayList<>();
            while(rs.next()) {
                results.add(rowMapper.mapRow(rs));
            }
            return results;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
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
