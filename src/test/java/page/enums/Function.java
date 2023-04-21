package page.enums;

public enum Function implements IEnum {
    COS("BtnCos");

    public final String value;

    Function(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
