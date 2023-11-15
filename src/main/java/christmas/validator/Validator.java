package christmas.validator;

import static christmas.config.Regex.COMMA_AT_SIDE;
import static christmas.config.Regex.VALID_DAY_INPUT;
import static christmas.config.Regex.VALID_ORDER_INPUT;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidDay(String dayInput) {
        if (!isValidInteger(dayInput)) {
            return false;
        }

        // 숫자가 1부터 31 사이인지 확인
        int number = Integer.parseInt(dayInput);
        return 1 <= number && number <= 31;
    }

    private static boolean isValidInteger(String input) {
        // 문자열이 숫자로만 이루어져 있고, 길이가 2인지 확인
        Matcher matcher = Pattern.compile(VALID_DAY_INPUT.getPattern())
                .matcher(input);

        return matcher.matches();
    }

    public static boolean isValidOrder(String orderInput) {
        if (isCommaAtSide(orderInput)) {
            return false;
        }

        String[] splitInput = orderInput.split(",");
        return Arrays.stream(splitInput)
                .allMatch(Validator::isValidEachMenuFormat);
    }

    private static boolean isCommaAtSide(String orderInput) {
        Matcher matcher = Pattern.compile(COMMA_AT_SIDE.getPattern())
                .matcher(orderInput);

        return matcher.matches();
    }

    private static boolean isValidEachMenuFormat(String menu) {
        Matcher matcher = Pattern.compile(VALID_ORDER_INPUT.getPattern())
                .matcher(menu);

        return matcher.matches();
    }
}
