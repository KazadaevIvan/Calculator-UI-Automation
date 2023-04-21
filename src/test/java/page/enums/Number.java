package page.enums;

public enum Number implements IEnum {
    ONE("Btn1"),
    THREE("Btn3"),
    FOUR("Btn4"),
    FIVE("Btn5"),
    EIGHT("Btn8"),
    NINE("Btn9"),
    ZERO("Btn0");

    public final String value;

    Number(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
