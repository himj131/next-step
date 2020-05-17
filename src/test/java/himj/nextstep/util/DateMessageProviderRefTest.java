package himj.nextstep.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class DateMessageProviderRefTest {
    @Test
    void 오전() {
        DateMessageProviderRef providerRef = new DateMessageProviderRef();
        String dateMesage = providerRef.getDateMesage(createCurrentDate(1));
        assertEquals("오전", dateMesage);
    }

    @Test
    void 오후() {
        DateMessageProviderRef providerRef = new DateMessageProviderRef();
        String dateMesage = providerRef.getDateMesage(createCurrentDate(16));
        assertEquals("오후", dateMesage);
    }

    private Calendar createCurrentDate(int hourOfDay) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        return cal;
    }
}