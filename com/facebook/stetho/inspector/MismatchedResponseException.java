package com.facebook.stetho.inspector;
/* loaded from: classes.dex */
public class MismatchedResponseException extends MessageHandlingException {
    public long mRequestId;

    public MismatchedResponseException(long j2) {
        super("Response for request id " + j2 + ", but no such request is pending");
        this.mRequestId = j2;
    }

    public long getRequestId() {
        return this.mRequestId;
    }
}
