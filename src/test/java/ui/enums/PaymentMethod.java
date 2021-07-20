package ui.enums;

public enum PaymentMethod {
    CASH_ON_DELIVERY("Cash On Delivery", "paymentmethod_0"),
    CHECK_MONEY_ORDER("Check / Money Order", "paymentmethod_1"),
    CREDIT_CARD("Credit Card", "paymentmethod_2"),
    PURCHASE_ORDER("Purchase Order", "paymentmethod_3"),
    ;

    String name;
    String id;

    PaymentMethod(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
