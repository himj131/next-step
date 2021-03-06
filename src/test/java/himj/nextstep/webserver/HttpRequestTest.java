package himj.nextstep.webserver;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestTest {
    private String testDirectory = "./src/test/resources/";

    @Test
    void request_GET() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory +
                "Http_GET.txt"));

        HttpRequest request = new HttpRequest(in);

        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals("/user/create", request.getPath());
        assertEquals("keep-alive", request.getHeader("Connection"));
        assertEquals("mj", request.getParameter("userId"));
    }

    @Test
    void request_POST() throws Exception {
        InputStream in = new FileInputStream(new File(testDirectory +
                "Http_POST.txt"));

        HttpRequest request = new HttpRequest(in);

        assertEquals(HttpMethod.POST, request.getMethod());
        assertEquals("/user/create", request.getPath());
        assertEquals("keep-alive", request.getHeader("Connection"));
        assertEquals("mj", request.getParameter("userId"));
    }
}