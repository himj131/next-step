package himj.nextstep.webserver;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

class HttpResponseTest {
    private String testDirectory = "./src/test/resources/";

    @Test
    void responseForward() throws Exception {
        HttpResponse response = new HttpResponse(createOutputStream("Http_Forward.txt"));
        response.forward("/index_.html");
    }

    @Test
    void responseRedirect() throws Exception {
        HttpResponse response =
                new HttpResponse(createOutputStream("Http_Redirect.txt"));
        response.sendRedirect("/index_.html");
    }

    @Test
    void responseCookies() throws Exception {
        HttpResponse response =
                new HttpResponse(createOutputStream("Http_Cookie.txt"));
        response.addHeader("Set-Cookie", "logined=true");
        response.sendRedirect("/index_.html");
    }

    private OutputStream createOutputStream(String fileName) throws Exception {
        return new FileOutputStream(new File(testDirectory + fileName));
    }
}