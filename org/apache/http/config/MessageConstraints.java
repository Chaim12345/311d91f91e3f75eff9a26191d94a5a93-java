package org.apache.http.config;

import org.apache.http.util.Args;
/* loaded from: classes3.dex */
public class MessageConstraints implements Cloneable {
    public static final MessageConstraints DEFAULT = new Builder().build();
    private final int maxHeaderCount;
    private final int maxLineLength;

    /* loaded from: classes3.dex */
    public static class Builder {
        private int maxLineLength = -1;
        private int maxHeaderCount = -1;

        Builder() {
        }

        public MessageConstraints build() {
            return new MessageConstraints(this.maxLineLength, this.maxHeaderCount);
        }

        public Builder setMaxHeaderCount(int i2) {
            this.maxHeaderCount = i2;
            return this;
        }

        public Builder setMaxLineLength(int i2) {
            this.maxLineLength = i2;
            return this;
        }
    }

    MessageConstraints(int i2, int i3) {
        this.maxLineLength = i2;
        this.maxHeaderCount = i3;
    }

    public static Builder copy(MessageConstraints messageConstraints) {
        Args.notNull(messageConstraints, "Message constraints");
        return new Builder().setMaxHeaderCount(messageConstraints.getMaxHeaderCount()).setMaxLineLength(messageConstraints.getMaxLineLength());
    }

    public static Builder custom() {
        return new Builder();
    }

    public static MessageConstraints lineLen(int i2) {
        return new MessageConstraints(Args.notNegative(i2, "Max line length"), -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageConstraints clone() {
        return (MessageConstraints) super.clone();
    }

    public int getMaxHeaderCount() {
        return this.maxHeaderCount;
    }

    public int getMaxLineLength() {
        return this.maxLineLength;
    }

    public String toString() {
        return "[maxLineLength=" + this.maxLineLength + ", maxHeaderCount=" + this.maxHeaderCount + "]";
    }
}
