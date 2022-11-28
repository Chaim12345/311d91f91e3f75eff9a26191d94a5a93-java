package io.opencensus.trace;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.NetworkEvent;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Deprecated
@Immutable
/* loaded from: classes3.dex */
final class AutoValue_NetworkEvent extends NetworkEvent {
    private final long compressedMessageSize;
    private final Timestamp kernelTimestamp;
    private final long messageId;
    private final NetworkEvent.Type type;
    private final long uncompressedMessageSize;

    /* loaded from: classes3.dex */
    static final class Builder extends NetworkEvent.Builder {
        private Long compressedMessageSize;
        private Timestamp kernelTimestamp;
        private Long messageId;
        private NetworkEvent.Type type;
        private Long uncompressedMessageSize;

        @Override // io.opencensus.trace.NetworkEvent.Builder
        NetworkEvent.Builder a(long j2) {
            this.messageId = Long.valueOf(j2);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public NetworkEvent.Builder b(NetworkEvent.Type type) {
            Objects.requireNonNull(type, "Null type");
            this.type = type;
            return this;
        }

        @Override // io.opencensus.trace.NetworkEvent.Builder
        public NetworkEvent build() {
            String str = "";
            if (this.type == null) {
                str = " type";
            }
            if (this.messageId == null) {
                str = str + " messageId";
            }
            if (this.uncompressedMessageSize == null) {
                str = str + " uncompressedMessageSize";
            }
            if (this.compressedMessageSize == null) {
                str = str + " compressedMessageSize";
            }
            if (str.isEmpty()) {
                return new AutoValue_NetworkEvent(this.kernelTimestamp, this.type, this.messageId.longValue(), this.uncompressedMessageSize.longValue(), this.compressedMessageSize.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // io.opencensus.trace.NetworkEvent.Builder
        public NetworkEvent.Builder setCompressedMessageSize(long j2) {
            this.compressedMessageSize = Long.valueOf(j2);
            return this;
        }

        @Override // io.opencensus.trace.NetworkEvent.Builder
        public NetworkEvent.Builder setKernelTimestamp(@Nullable Timestamp timestamp) {
            this.kernelTimestamp = timestamp;
            return this;
        }

        @Override // io.opencensus.trace.NetworkEvent.Builder
        public NetworkEvent.Builder setUncompressedMessageSize(long j2) {
            this.uncompressedMessageSize = Long.valueOf(j2);
            return this;
        }
    }

    private AutoValue_NetworkEvent(@Nullable Timestamp timestamp, NetworkEvent.Type type, long j2, long j3, long j4) {
        this.kernelTimestamp = timestamp;
        this.type = type;
        this.messageId = j2;
        this.uncompressedMessageSize = j3;
        this.compressedMessageSize = j4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NetworkEvent) {
            NetworkEvent networkEvent = (NetworkEvent) obj;
            Timestamp timestamp = this.kernelTimestamp;
            if (timestamp != null ? timestamp.equals(networkEvent.getKernelTimestamp()) : networkEvent.getKernelTimestamp() == null) {
                if (this.type.equals(networkEvent.getType()) && this.messageId == networkEvent.getMessageId() && this.uncompressedMessageSize == networkEvent.getUncompressedMessageSize() && this.compressedMessageSize == networkEvent.getCompressedMessageSize()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // io.opencensus.trace.NetworkEvent
    public long getCompressedMessageSize() {
        return this.compressedMessageSize;
    }

    @Override // io.opencensus.trace.NetworkEvent
    @Nullable
    public Timestamp getKernelTimestamp() {
        return this.kernelTimestamp;
    }

    @Override // io.opencensus.trace.NetworkEvent
    public long getMessageId() {
        return this.messageId;
    }

    @Override // io.opencensus.trace.NetworkEvent
    public NetworkEvent.Type getType() {
        return this.type;
    }

    @Override // io.opencensus.trace.NetworkEvent
    public long getUncompressedMessageSize() {
        return this.uncompressedMessageSize;
    }

    public int hashCode() {
        Timestamp timestamp = this.kernelTimestamp;
        int hashCode = timestamp == null ? 0 : timestamp.hashCode();
        long j2 = this.messageId;
        long j3 = this.uncompressedMessageSize;
        long j4 = this.compressedMessageSize;
        return (int) ((((int) ((((int) (((((hashCode ^ 1000003) * 1000003) ^ this.type.hashCode()) * 1000003) ^ (j2 ^ (j2 >>> 32)))) * 1000003) ^ (j3 ^ (j3 >>> 32)))) * 1000003) ^ (j4 ^ (j4 >>> 32)));
    }

    public String toString() {
        return "NetworkEvent{kernelTimestamp=" + this.kernelTimestamp + ", type=" + this.type + ", messageId=" + this.messageId + ", uncompressedMessageSize=" + this.uncompressedMessageSize + ", compressedMessageSize=" + this.compressedMessageSize + "}";
    }
}
