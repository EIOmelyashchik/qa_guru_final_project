package ui.testData;

public enum Country {
    BELARUS("Belarus", "11"),
    RUSSIA("Russia", "66"),
    UKRAINE("Ukraine", "78"),
    ;

    String name;
    String id;

    Country(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
