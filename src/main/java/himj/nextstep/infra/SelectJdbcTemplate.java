package himj.nextstep.infra;

import himj.nextstep.config.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate {
    public List queryForList(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setParameters(pstmt);

            rs = pstmt.executeQuery();
            List<Object> results = new ArrayList<>();
            while(rs.next()) {
                results.add(mapRow(rs));
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

    public Object queryForObject(String sql) throws SQLException {
        List results =  queryForList(sql);
        if(results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    protected abstract Object mapRow(ResultSet rs) throws SQLException;

    protected abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}
