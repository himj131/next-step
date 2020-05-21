package core.di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HourTest {
    @Test
    void 오전() {
        Hour hour = new Hour(11);
        assertEquals("오전", hour.getMessage());
    }

    @Test
    void 오후() {
        Hour hour = new Hour(16);
        assertEquals("오후", hour.getMessage());
    }
}