package himj.nextstep.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface View {
    void render(HttpServletRequest requestm, HttpServletResponse response) throws IOException, ServletException;
}
