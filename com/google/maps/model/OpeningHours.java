package com.google.maps.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class OpeningHours implements Serializable {
    private static final long serialVersionUID = 1;
    public Boolean openNow;
    public Period[] periods;
    public Boolean permanentlyClosed;
    public String[] weekdayText;

    /* loaded from: classes2.dex */
    public static class Period implements Serializable {
        private static final long serialVersionUID = 1;
        public OpenClose close;
        public OpenClose open;

        /* loaded from: classes2.dex */
        public static class OpenClose implements Serializable {
            private static final long serialVersionUID = 1;
            public DayOfWeek day;
            public LocalTime time;

            /* loaded from: classes2.dex */
            public enum DayOfWeek {
                SUNDAY("Sunday"),
                MONDAY("Monday"),
                TUESDAY("Tuesday"),
                WEDNESDAY("Wednesday"),
                THURSDAY("Thursday"),
                FRIDAY("Friday"),
                SATURDAY("Saturday"),
                UNKNOWN("Unknown");
                
                private final String name;

                DayOfWeek(String str) {
                    this.name = str;
                }

                public String getName() {
                    return this.name;
                }
            }

            public String toString() {
                return String.format("%s %s", this.day, this.time);
            }
        }

        public String toString() {
            return String.format("%s - %s", this.open, this.close);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[OpeningHours:");
        Boolean bool = this.permanentlyClosed;
        if (bool != null && bool.booleanValue()) {
            sb.append(" permanentlyClosed");
        }
        Boolean bool2 = this.openNow;
        if (bool2 != null && bool2.booleanValue()) {
            sb.append(" openNow");
        }
        sb.append(" ");
        sb.append(Arrays.toString(this.periods));
        return sb.toString();
    }
}
