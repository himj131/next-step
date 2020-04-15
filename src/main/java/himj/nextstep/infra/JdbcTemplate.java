package himj.nextstep.infra;

import himj.nextstep.config.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {
    public void insert(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setParameters(pstmt);
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

    protected abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}
