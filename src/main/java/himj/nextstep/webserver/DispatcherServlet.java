package himj.nextstep.webserver;

import com.google.common.collect.Lists;
import himj.nextstep.controller.Controller;
import himj.nextstep.mvc.ModelAndView;
import himj.nextstep.mvc.View;
import himj.nextstep.nmvc.AnnotationHandlerMapping;
import himj.nextstep.nmvc.HandlerExecution;
import himj.nextstep.nmvc.HandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name ="dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private List<HandlerMapping> mappings = Lists.newArrayList();

    @Override
    public void init() {
        LegacyHandlerMapping lhm = new LegacyHandlerMapping();
        lhm.initMapping();
        AnnotationHandlerMapping ahm = new AnnotationHandlerMapping("himj.nextstep");
        ahm.initialize();

        mappings.add(lhm);
        mappings.add(ahm);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        Object handler = getHandler(req);
        if(handler == null) {
            throw new IllegalArgumentException("존재하지 않는 url입니다.");
        }

        try {
            ModelAndView modelAndView = execute(handler, req, resp);
            View view = modelAndView.getView();
            view.render(modelAndView.getModel(), req, resp);
        } catch (Exception e) {
            logger.error("Exception: {}", e);
        }
    }

    private ModelAndView execute(Object handler, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(handler instanceof Controller) {
            return ((Controller) handler).execute(req, resp);
        } else {
            return ((HandlerExecution)handler).handle(req, resp);
        }
    }

    private Object getHandler(HttpServletRequest req) {
        for (HandlerMapping handlerMapping : mappings) {
            Object handler = handlerMapping.getHandler(req);
            if (handler != null) {
                return handler;
            }
        }
        return null;
    }
}