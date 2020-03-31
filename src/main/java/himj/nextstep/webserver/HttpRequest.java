package himj.nextstep.webserver;

import himj.nextstep.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String path;
    private Map<String ,String> headers = new HashMap<String, String>();
    private Map<String ,String> params = new HashMap<String, String>();

    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    public HttpRequest(InputStream in) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = br.readLine();
            if(line == null) return;
            processRequestLine(line);

            line = br.readLine();
            while(!line.equals("")) {
                log.debug("header : {}", line);
                String[] tokens = line.split(":");
                headers.put(tokens[0].trim(), tokens[1].trim());
                line = br.readLine();
            }

            if("POST".equals(method)){
                String body = util.IOUtils.readData(br,
                        Integer.parseInt(headers.get("Content-Length")));
                params = HttpRequestUtils.parseQueryString(body);
            }
        }catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private boolean processRequestLine(String requestLine) {
        log.debug("request line : {}", requestLine);
        String[] splited = requestLine.split(" ");
        method = splited[0];
        if("POST".equals(method)) {
            path = splited[1];
            return true;
        }

        int index = splited[1].indexOf("?");
        if(index == -1) {
            path = splited[1];
        } else {
            path = splited[1].substring(0, index);
            params = HttpRequestUtils.parseQueryString(splited[1].substring(index+1));
        }
        return false;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public String getParameter(String name) {
        return params.get(name);
    }
}
