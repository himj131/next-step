package himj.nextstep.webserver;

import himj.nextstep.model.User;
import himj.nextstep.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private Map<String ,String> headers = new HashMap<String, String>();
    private Map<String ,String> params = new HashMap<String, String>();
    private RequestLine requestLine;


    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

    public HttpRequest(InputStream in) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = br.readLine();
            if(line == null) return;

            requestLine = new RequestLine(line);

            line = br.readLine();
            while(!line.equals("")) {
                log.debug("header : {}", line);
                String[] tokens = line.split(":");
                headers.put(tokens[0].trim(), tokens[1].trim());
                line = br.readLine();
            }

            if(HttpMethod.POST.equals(requestLine.getMehtod())){
                String body = util.IOUtils.readData(br,
                        Integer.parseInt(headers.get("Content-Length")));
                params = HttpRequestUtils.parseQueryString(body);
            } else {
                params = requestLine.getParams();
            }
        }catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public HttpMethod getMethod() {
        return requestLine.getMehtod();
    }

    public String getPath() {
        return requestLine.getPath();
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public String getParameter(String name) {
        return params.get(name);
    }

    public HttpCookie getCookies() {
        return new HttpCookie(getHeader("Cookie"));
    }

    public HttpSession getSession() {
        return HttpSessions.getSession(getCookies().getCookie("JSESSIONID"));
    }

    public void setAttribute(String users, Object obj) {

    }
}
