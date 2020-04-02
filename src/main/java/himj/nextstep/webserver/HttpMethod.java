package himj.nextstep.webserver;

public enum HttpMethod {
    POST, GET;

    public boolean isPost(){
        return this == POST;
    }
}
