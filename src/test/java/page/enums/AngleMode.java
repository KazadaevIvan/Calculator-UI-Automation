package page.enums;

public enum AngleMode implements IEnum {
    RAD("trigorad");
    public final String value;

    AngleMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
