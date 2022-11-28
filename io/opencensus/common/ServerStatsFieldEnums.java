package io.opencensus.common;

import java.util.TreeMap;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public final class ServerStatsFieldEnums {
    private static final int TOTALSIZE = computeTotalSize();

    /* loaded from: classes3.dex */
    public enum Id {
        SERVER_STATS_LB_LATENCY_ID(0),
        SERVER_STATS_SERVICE_LATENCY_ID(1),
        SERVER_STATS_TRACE_OPTION_ID(2);
        
        private static final TreeMap<Integer, Id> map = new TreeMap<>();
        private final int value;

        static {
            Id[] values;
            for (Id id : values()) {
                map.put(Integer.valueOf(id.value), id);
            }
        }

        Id(int i2) {
            this.value = i2;
        }

        @Nullable
        public static Id valueOf(int i2) {
            return map.get(Integer.valueOf(i2));
        }

        public int value() {
            return this.value;
        }
    }

    /* loaded from: classes3.dex */
    public enum Size {
        SERVER_STATS_LB_LATENCY_SIZE(8),
        SERVER_STATS_SERVICE_LATENCY_SIZE(8),
        SERVER_STATS_TRACE_OPTION_SIZE(1);
        
        private final int value;

        Size(int i2) {
            this.value = i2;
        }

        public int value() {
            return this.value;
        }
    }

    private ServerStatsFieldEnums() {
    }

    private static int computeTotalSize() {
        int i2 = 0;
        for (Size size : Size.values()) {
            i2 = i2 + size.value() + 1;
        }
        return i2;
    }

    public static int getTotalSize() {
        return TOTALSIZE;
    }
}
