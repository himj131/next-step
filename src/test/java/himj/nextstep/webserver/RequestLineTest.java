package himj.nextstep.webserver;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestLineTest {
    @Test
    void create_method() {
        RequestLine line = new RequestLine("GET /index_.html HTTP/1.1");
        assertEquals(HttpMethod.GET, line.getMehtod());
        assertEquals("/index_.html", line.getPath());

        line = new RequestLine("POST /index_.html HTTP/1.1");
        assertEquals("/index_.html", line.getPath());
    }

    @Test
    void create_path_and_params() {
        RequestLine line = new RequestLine(
                "GET /user/create?userId=mj&password=pass HTTP/1.1");
        assertEquals(HttpMethod.GET, line.getMehtod());
        assertEquals("/user/create", line.getPath());
        Map<String, String> params = line.getParams();
        assertEquals(2, params.size());
    }
}