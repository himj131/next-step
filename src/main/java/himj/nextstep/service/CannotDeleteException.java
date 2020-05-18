package himj.nextstep.service;

public class CannotDeleteException extends Throwable {
    private static final long serialVersionUID = 1L;

    public CannotDeleteException(String message) {
        super(message);
    }
}
