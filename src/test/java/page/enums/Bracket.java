package page.enums;

public enum Bracket implements IEnum {
    ROUND_BRACKET_LEFT("BtnParanL"),
    ROUND_BRACKET_RIGHT("BtnParanR");

    public final String value;

    Bracket(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
