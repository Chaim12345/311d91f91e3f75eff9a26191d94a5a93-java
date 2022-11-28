package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public class SupplementalDataEntry {

    /* renamed from: a  reason: collision with root package name */
    protected int f14828a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f14829b;

    public SupplementalDataEntry(int i2, byte[] bArr) {
        this.f14828a = i2;
        this.f14829b = bArr;
    }

    public byte[] getData() {
        return this.f14829b;
    }

    public int getDataType() {
        return this.f14828a;
    }
}
