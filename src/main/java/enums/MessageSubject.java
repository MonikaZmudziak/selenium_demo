package enums;

public enum MessageSubject {
    CUSTOMER_SERVICE("Customer service"), WEBMASTER("Webmaster"); // użytkownik musi wybrać jedną z tych dwóch wartości

    private String value;

    MessageSubject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
