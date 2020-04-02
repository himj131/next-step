package himj.nextstep.webserver;

import himj.nextstep.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestLine {
    private static final Logger log = LoggerFactory.getLogger(RequestLine.class);

    private HttpMethod method;
    private String path;
    private Map<String, String> params = new HashMap<String, String>();

    public RequestLine(String requestLine) {
        log.debug("request line : {}", requestLine);
        String[] splited = requestLine.split(" ");
        if(splited.length != 3) {
            throw new IllegalArgumentException(requestLine+ "이 형식에 맞지 않습니다.");
        }

        method = HttpMethod.valueOf(splited[0]);
        if(method.isPost()) {
            path = splited[1];
            return;
        }

        int index = splited[1].indexOf("?");
        if(index == -1) {
            path = splited[1];
        } else {
            path = splited[1].substring(0, index);
            params = HttpRequestUtils.parseQueryString(splited[1].substring(index+1));
        }
    }

    public HttpMethod getMehtod(){
        return method;
    }

    public String getPath(){
        return path;
    }

    public Map<String, String> getParams() {
        return params;
    }
}