package himj.nextstep.calculator;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  - 문자열 계산기
 *  - 쉼표(,) 또는 콜론(:) 구분자로 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
 *  - 커스텀 구분자 지정가능하다. 문자열 앞부분의 "//"와 "\n"사이에 위치하는 문자는 커스텀 구분자로 사용한다.
 *      예) "//;\n1;2;3" -> 커스텀 구분자는 세미콜론(;) 이며 결과는 6
 *  - 문자열 계산기에 음수를 전달할 경우에는 RuntimeException 예외처리할것
 * */
public class StringCalculator {

    public int add(String str) {
        if(isBlank(str)) return 0;
        return sum(toInts(splitString(str)));
    }

    private String[] splitString(String str) {
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(str);
        if(matcher.find()) {
            String separator = matcher.group(1);
            return matcher.group(2).split(separator);
        }
        return str.split(",|:");
    }

    private boolean isBlank(String str) {
        return (str == null || str.trim().isEmpty());
    }

    private int sum(int[] values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    private int[] toInts (String[] values) {
        int[] ints = new int[values.length];
        for(int i = 0; i < ints.length; i++) {
            ints[i] = toPositive(values[i]);
         }
        return ints;
    }

    private int toPositive(String value) {
        int number = Integer.parseInt(value);
        if(number < 0) throw new RuntimeException();
        return number;
    }
}
