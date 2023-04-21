package page.enums;

public enum Operator implements IEnum {
    MULTIPLY("BtnMult"),
    DIVIDE("BtnDiv"),
    ADD("BtnPlus"),
    CLEAR("BtnClear"),
    EQUAL("BtnCalc");

    public final String value;

    Operator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
