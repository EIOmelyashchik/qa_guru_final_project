package ui.enums;

public enum CheckoutTab {
    BILLING_ADDRESS("Billing Address", "opc-billing"),
    SHIPPING_ADDRESS("Shipping Address", "opc-shipping"),
    SHIPPING_METHOD("Shipping Method", "opc-shipping_method"),
    PAYMENT_METHOD("Payment Method", "opc-payment_method"),
    PAYMENT_INFORMATION("Payment Information", "opc-payment_info"),
    ;

    String name;
    String id;

    CheckoutTab(String name, String id) {
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
