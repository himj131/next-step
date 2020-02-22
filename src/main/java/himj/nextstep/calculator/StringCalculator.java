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
        int total = 0;
        if(str.trim().isEmpty()) return 0;
        String[] values;
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(str);
        if(matcher.find()) {
            String separator = matcher.group(1);
            values = matcher.group(2).split(separator);
        } else {
            values = str.split(",|:");
        }
        for (String value : values) {
            int intValue = Integer.parseInt(value);
            total += intValue;
            if (intValue < 0) throw new RuntimeException();
        }

        return total;
    }
}
