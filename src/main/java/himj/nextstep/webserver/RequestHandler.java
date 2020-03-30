package himj.nextstep.webserver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.DatabaseMetaData;
import java.util.Map;

import himj.nextstep.db.DataBase;
import himj.nextstep.model.User;
import himj.nextstep.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = br.readLine();
            if(line == null) return;
            String[] splited = line.split(" ");
            int contentLength = 0;
            while (!line.equals("")) {
                log.debug("header : {}", line);
                line = br.readLine();

                if(line.contains("Content-Length")) {
                    contentLength = getContentLength(line);
                }
            }
            String path = splited[1];
            if("/user/create".equals(path)) {
                String body = util.IOUtils.readData(br, contentLength);
                Map<String, String> params = HttpRequestUtils.parseQueryString(body);
                User user = new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
                log.debug("User: {}", user);
                DataBase.addUser(user);
                DataOutputStream dos = new DataOutputStream(out);
                response302Header(dos, "/index.html");
            } else if("/user/login".equals(path)) {
                String body = util.IOUtils.readData(br, contentLength);
                Map<String, String> params = HttpRequestUtils.parseQueryString(body);
                User user = DataBase.findUserById(params.get("userId"));
                DataOutputStream dos = new DataOutputStream(out);
                if(user != null && user.getPassword().equals(params.get("password"))) {
                    response302LoginSuccessHeader(dos);
                } else {

                    response302LoginFailHeader(dos);
                }

            } else {
                DataOutputStream dos = new DataOutputStream(out);
                byte[] body = Files.readAllBytes(new File("./webapp", path).toPath());
                response200Header(dos, body.length);
                responseBody(dos, body);
            }





        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response302LoginFailHeader(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: /user/login_failed.html \r\n");
            dos.writeBytes("Set-Cookie: logined=false \r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private int getContentLength(String line) {
        String[] headerTockens = line.split(":");
        return Integer.parseInt(headerTockens[1].trim());
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response302LoginSuccessHeader(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: /index.html \r\n");
            dos.writeBytes("Set-Cookie: logined=true \r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response302Header(DataOutputStream dos, String url) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: " + url + " \r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
