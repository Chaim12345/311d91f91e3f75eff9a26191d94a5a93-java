package io.opencensus.common;

import io.opencensus.common.ServerStatsFieldEnums;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes3.dex */
public final class ServerStatsEncoding {
    public static final byte CURRENT_VERSION = 0;

    /* renamed from: io.opencensus.common.ServerStatsEncoding$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10952a;

        static {
            int[] iArr = new int[ServerStatsFieldEnums.Id.values().length];
            f10952a = iArr;
            try {
                iArr[ServerStatsFieldEnums.Id.SERVER_STATS_LB_LATENCY_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10952a[ServerStatsFieldEnums.Id.SERVER_STATS_SERVICE_LATENCY_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10952a[ServerStatsFieldEnums.Id.SERVER_STATS_TRACE_OPTION_ID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private ServerStatsEncoding() {
    }

    public static ServerStats parseBytes(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        if (wrap.hasRemaining()) {
            byte b2 = wrap.get();
            if (b2 > 0 || b2 < 0) {
                throw new ServerStatsDeserializationException("Invalid ServerStats version: " + ((int) b2));
            }
            long j2 = 0;
            byte b3 = 0;
            long j3 = 0;
            while (wrap.hasRemaining()) {
                ServerStatsFieldEnums.Id valueOf = ServerStatsFieldEnums.Id.valueOf(wrap.get() & 255);
                if (valueOf == null) {
                    wrap.position(wrap.limit());
                } else {
                    int i2 = AnonymousClass1.f10952a[valueOf.ordinal()];
                    if (i2 == 1) {
                        j2 = wrap.getLong();
                    } else if (i2 == 2) {
                        j3 = wrap.getLong();
                    } else if (i2 == 3) {
                        b3 = wrap.get();
                    }
                }
            }
            try {
                return ServerStats.create(j2, j3, b3);
            } catch (IllegalArgumentException e2) {
                throw new ServerStatsDeserializationException("Serialized ServiceStats contains invalid values: " + e2.getMessage());
            }
        }
        throw new ServerStatsDeserializationException("Serialized ServerStats buffer is empty");
    }

    public static byte[] toBytes(ServerStats serverStats) {
        ByteBuffer allocate = ByteBuffer.allocate(ServerStatsFieldEnums.getTotalSize() + 1);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put((byte) 0);
        allocate.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_LB_LATENCY_ID.value());
        allocate.putLong(serverStats.getLbLatencyNs());
        allocate.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_SERVICE_LATENCY_ID.value());
        allocate.putLong(serverStats.getServiceLatencyNs());
        allocate.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_TRACE_OPTION_ID.value());
        allocate.put(serverStats.getTraceOption());
        return allocate.array();
    }
}
