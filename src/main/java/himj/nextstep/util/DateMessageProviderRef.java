package himj.nextstep.util;

import java.util.Calendar;

public class DateMessageProviderRef {
    public String getDateMesage(Calendar now) {
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if (hour < 12) {
            return "오전";
        } else {
            return "오후";
        }
    }
}
