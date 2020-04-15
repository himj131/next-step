package himj.nextstep.config;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConnectionManagerTest {
    @Test
    void connection() {
        Connection con = ConnectionManager.getConnection();
        assertNotNull(con);
    }
}