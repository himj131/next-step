package himj.nextstep.nmvc;

import himj.nextstep.common.RequestMapping;
import himj.nextstep.common.RequestMethod;
import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @RequestMapping("/users/findUserId")
    public ModelAndView findUserId(HttpServletRequest reuqest, HttpServletResponse response) {
        logger.debug("findUserId");
        return new ModelAndView(new JspView("/users/list.jsp"));
    }

    @RequestMapping(value="/users", method= RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("save");
        return new ModelAndView(new JspView("redirect:/users"));
    }

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public ModelAndView users(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("users");
        return new ModelAndView(new JspView("/users/list.jsp"));
    }

    @RequestMapping(value="/users/show", method= RequestMethod.GET)
    public ModelAndView showUsers(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("showUsers");
        return new ModelAndView(new JspView("/users/show.jsp"));
    }
}
