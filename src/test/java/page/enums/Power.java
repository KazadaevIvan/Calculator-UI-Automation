package page.enums;

public enum Power implements IEnum {
    SQRT("BtnSqrt");
    public final String value;

    Power(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
