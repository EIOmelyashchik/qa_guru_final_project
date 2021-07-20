package ui.endpoints;

public enum ApiEndpoint {
    MAIN("/"),
    LOGIN("/login"),
    REGISTER("/register"),
    ADD_TO_CART("/addproducttocart"),
    SAVE_BILLING("checkout/OpcSaveBilling")
    ;

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String addPath(String additionalPath) {
        return path + additionalPath;
    }
}
