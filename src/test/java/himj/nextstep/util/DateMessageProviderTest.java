package himj.nextstep.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateMessageProviderTest {
    @Test
    void 오전() {
        DateMessageProvider dateProvider = new DateMessageProvider();
        assertEquals("오전", dateProvider.getDateMessage());
    }

    @Test
    void 오후() {
        DateMessageProvider dateProvider = new DateMessageProvider();
        assertEquals("오후", dateProvider.getDateMessage());
    }
}