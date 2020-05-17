package himj.nextstep.util;

import java.util.Calendar;

public class DateMessageProvider {
    public String getDateMessage() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if (hour < 12) {
            return "오전";
        } else {
            return "오후";
        }
    }
}
