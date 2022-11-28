package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class ApiException extends Exception {
    @NonNull
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    protected final Status f5620a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ApiException(@NonNull Status status) {
        super(r3.toString());
        int statusCode = status.getStatusCode();
        String statusMessage = status.getStatusMessage() != null ? status.getStatusMessage() : "";
        StringBuilder sb = new StringBuilder(String.valueOf(statusMessage).length() + 13);
        sb.append(statusCode);
        sb.append(": ");
        sb.append(statusMessage);
        this.f5620a = status;
    }

    @NonNull
    public Status getStatus() {
        return this.f5620a;
    }

    public int getStatusCode() {
        return this.f5620a.getStatusCode();
    }

    @Nullable
    @Deprecated
    public String getStatusMessage() {
        return this.f5620a.getStatusMessage();
    }
}
