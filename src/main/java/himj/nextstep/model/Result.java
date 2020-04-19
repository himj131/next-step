package himj.nextstep.model;

public class Result {
    private boolean status;
    private String message;

    public Result(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Result(boolean status) {
        this(status, "");
    }

    public static Result ok() {
        return new Result(true);
    }

    public static Result fail(String msg) {
        return new Result(false, msg);
    }

    @Override
    public String toString() {
        return "Result [status=" + status + ", message=" + message + "]";
    }
}
