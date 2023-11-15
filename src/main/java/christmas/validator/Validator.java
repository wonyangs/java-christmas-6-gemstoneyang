package christmas.validator;

import static christmas.config.Regex.DAY_INPUT_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidDay(String day) {
        // 문자열이 숫자로만 이루어져 있고, 길이가 2인지 확인
        Pattern pattern = Pattern.compile(DAY_INPUT_PATTERN.getPattern());
        Matcher matcher = pattern.matcher(day);
        if (!matcher.matches()) {
            return false;
        }

        // 숫자가 1부터 31 사이인지 확인
        int number = Integer.parseInt(day);
        return 1 <= number && number <= 31;
    }
}
