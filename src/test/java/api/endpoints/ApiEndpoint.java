package api.endpoints;

public enum ApiEndpoint {
    BOOKS("/Books"),
    BOOK("/Book"),

    USER("/User");

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
