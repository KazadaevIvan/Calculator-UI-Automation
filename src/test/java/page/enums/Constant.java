package page.enums;

public enum Constant implements IEnum {
    PI("BtnPi");
    public final String value;

    Constant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
