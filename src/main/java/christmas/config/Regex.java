package christmas.config;

public enum Regex {
    DAY_INPUT_PATTERN("^[0-9]{1,2}$");

    private final String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
