package api.endpoints;

public enum ApiEndpoint {
    BOOKS("/Books"),
    BOOK("/Book"),

    USER("/User"),
    GENERATE_TOKEN("/GenerateToken"),
    USER_BY_ID("/User/{user}")
    ;

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
