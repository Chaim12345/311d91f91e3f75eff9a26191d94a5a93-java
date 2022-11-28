package io.opencensus.trace;

import io.opencensus.common.Timestamp;
import io.opencensus.internal.Utils;
import io.opencensus.trace.AutoValue_NetworkEvent;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Deprecated
@Immutable
/* loaded from: classes3.dex */
public abstract class NetworkEvent extends BaseMessageEvent {

    @Deprecated
    /* loaded from: classes3.dex */
    public static abstract class Builder {
        abstract Builder a(long j2);

        public abstract NetworkEvent build();

        public abstract Builder setCompressedMessageSize(long j2);

        public abstract Builder setKernelTimestamp(@Nullable Timestamp timestamp);

        @Deprecated
        public Builder setMessageSize(long j2) {
            return setUncompressedMessageSize(j2);
        }

        public abstract Builder setUncompressedMessageSize(long j2);
    }

    /* loaded from: classes3.dex */
    public enum Type {
        SENT,
        RECV
    }

    public static Builder builder(Type type, long j2) {
        return new AutoValue_NetworkEvent.Builder().b((Type) Utils.checkNotNull(type, "type")).a(j2).setUncompressedMessageSize(0L).setCompressedMessageSize(0L);
    }

    public abstract long getCompressedMessageSize();

    @Nullable
    public abstract Timestamp getKernelTimestamp();

    public abstract long getMessageId();

    @Deprecated
    public long getMessageSize() {
        return getUncompressedMessageSize();
    }

    public abstract Type getType();

    public abstract long getUncompressedMessageSize();
}
