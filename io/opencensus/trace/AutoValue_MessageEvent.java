package io.opencensus.trace;

import io.opencensus.trace.MessageEvent;
import java.util.Objects;
/* loaded from: classes3.dex */
final class AutoValue_MessageEvent extends MessageEvent {
    private final long compressedMessageSize;
    private final long messageId;
    private final MessageEvent.Type type;
    private final long uncompressedMessageSize;

    /* loaded from: classes3.dex */
    static final class Builder extends MessageEvent.Builder {
        private Long compressedMessageSize;
        private Long messageId;
        private MessageEvent.Type type;
        private Long uncompressedMessageSize;

        @Override // io.opencensus.trace.MessageEvent.Builder
        MessageEvent.Builder a(long j2) {
            this.messageId = Long.valueOf(j2);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public MessageEvent.Builder b(MessageEvent.Type type) {
            Objects.requireNonNull(type, "Null type");
            this.type = type;
            return this;
        }

        @Override // io.opencensus.trace.MessageEvent.Builder
        public MessageEvent build() {
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
                return new AutoValue_MessageEvent(this.type, this.messageId.longValue(), this.uncompressedMessageSize.longValue(), this.compressedMessageSize.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // io.opencensus.trace.MessageEvent.Builder
        public MessageEvent.Builder setCompressedMessageSize(long j2) {
            this.compressedMessageSize = Long.valueOf(j2);
            return this;
        }

        @Override // io.opencensus.trace.MessageEvent.Builder
        public MessageEvent.Builder setUncompressedMessageSize(long j2) {
            this.uncompressedMessageSize = Long.valueOf(j2);
            return this;
        }
    }

    private AutoValue_MessageEvent(MessageEvent.Type type, long j2, long j3, long j4) {
        this.type = type;
        this.messageId = j2;
        this.uncompressedMessageSize = j3;
        this.compressedMessageSize = j4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MessageEvent) {
            MessageEvent messageEvent = (MessageEvent) obj;
            return this.type.equals(messageEvent.getType()) && this.messageId == messageEvent.getMessageId() && this.uncompressedMessageSize == messageEvent.getUncompressedMessageSize() && this.compressedMessageSize == messageEvent.getCompressedMessageSize();
        }
        return false;
    }

    @Override // io.opencensus.trace.MessageEvent
    public long getCompressedMessageSize() {
        return this.compressedMessageSize;
    }

    @Override // io.opencensus.trace.MessageEvent
    public long getMessageId() {
        return this.messageId;
    }

    @Override // io.opencensus.trace.MessageEvent
    public MessageEvent.Type getType() {
        return this.type;
    }

    @Override // io.opencensus.trace.MessageEvent
    public long getUncompressedMessageSize() {
        return this.uncompressedMessageSize;
    }

    public int hashCode() {
        long j2 = this.messageId;
        long j3 = this.uncompressedMessageSize;
        long j4 = this.compressedMessageSize;
        return (int) ((((int) ((((int) (((this.type.hashCode() ^ 1000003) * 1000003) ^ (j2 ^ (j2 >>> 32)))) * 1000003) ^ (j3 ^ (j3 >>> 32)))) * 1000003) ^ (j4 ^ (j4 >>> 32)));
    }

    public String toString() {
        return "MessageEvent{type=" + this.type + ", messageId=" + this.messageId + ", uncompressedMessageSize=" + this.uncompressedMessageSize + ", compressedMessageSize=" + this.compressedMessageSize + "}";
    }
}
