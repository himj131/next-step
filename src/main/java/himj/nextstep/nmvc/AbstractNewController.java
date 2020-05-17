package himj.nextstep.nmvc;

import himj.nextstep.mvc.JsonView;
import himj.nextstep.mvc.JspView;
import himj.nextstep.mvc.ModelAndView;

public abstract class AbstractNewController {
    protected ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
