package christmas.config;

public enum Regex {
    VALID_DAY_INPUT("^[0-9]{1,2}$"),
    COMMA_AT_SIDE("^,.*|.*,$"),
    VALID_ORDER_INPUT("^[^-]+-([1-9]\\d?)$");

    private final String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
