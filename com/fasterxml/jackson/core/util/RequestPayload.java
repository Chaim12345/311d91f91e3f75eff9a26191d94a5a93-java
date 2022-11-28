package com.fasterxml.jackson.core.util;

import java.io.IOException;
import java.io.Serializable;
/* loaded from: classes.dex */
public class RequestPayload implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f5244a;

    /* renamed from: b  reason: collision with root package name */
    protected CharSequence f5245b;

    /* renamed from: c  reason: collision with root package name */
    protected String f5246c;

    public RequestPayload(CharSequence charSequence) {
        if (charSequence == null) {
            throw new IllegalArgumentException();
        }
        this.f5245b = charSequence;
    }

    public RequestPayload(byte[] bArr, String str) {
        if (bArr == null) {
            throw new IllegalArgumentException();
        }
        this.f5244a = bArr;
        this.f5246c = (str == null || str.isEmpty()) ? "UTF-8" : "UTF-8";
    }

    public Object getRawPayload() {
        byte[] bArr = this.f5244a;
        return bArr != null ? bArr : this.f5245b;
    }

    public String toString() {
        byte[] bArr = this.f5244a;
        if (bArr != null) {
            try {
                return new String(bArr, this.f5246c);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        return this.f5245b.toString();
    }
}
