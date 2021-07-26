package ui.endpoints;

public enum UiEndpoint {
    REGISTER("/register"),
    CHECKOUT("/onepagecheckout");

    private final String path;

    UiEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}