package ui.enums;

public enum CreditCard {
    VISA("Visa", "4111 1111 4555 1142", "Ivan Ivanov", "12/2022", "123"),
    MASTER_CARD("Master card", "5555 3412 4444 1115", "Ivan Petrov", "06/2022", "123"),
    DISCOVER("Discover", "6011 1111 1111 1117", "Ivan Sergeev", "12/2023", "123"),
    AMEX("Amex", "374251018720018", "Ivan Loktev", "01/2024", "123"),
    ;

    String type;
    String cardNumber;
    String cardHolder;
    String expirationDate;
    String code;

    CreditCard(String type, String cardNumber, String cardHolder, String expirationDate, String code) {
        this.type = type;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "type='" + type + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
