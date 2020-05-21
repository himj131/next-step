package core.di;

import java.util.Objects;

public class Hour {
    private int hour;

    public Hour(int hour) {
        this.hour = hour;
    }

    public String getMessage() {
        if(hour < 12) return "오전";
        return "오후";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hour hour1 = (Hour) o;
        return hour == hour1.hour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour);
    }
}
