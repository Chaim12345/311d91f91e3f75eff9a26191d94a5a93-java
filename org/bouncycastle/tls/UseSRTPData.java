package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public class UseSRTPData {

    /* renamed from: a  reason: collision with root package name */
    protected int[] f14910a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f14911b;

    public UseSRTPData(int[] iArr, byte[] bArr) {
        if (TlsUtils.isNullOrEmpty(iArr) || iArr.length >= 32768) {
            throw new IllegalArgumentException("'protectionProfiles' must have length from 1 to (2^15 - 1)");
        }
        if (bArr == null) {
            bArr = TlsUtils.EMPTY_BYTES;
        } else if (bArr.length > 255) {
            throw new IllegalArgumentException("'mki' cannot be longer than 255 bytes");
        }
        this.f14910a = iArr;
        this.f14911b = bArr;
    }

    public byte[] getMki() {
        return this.f14911b;
    }

    public int[] getProtectionProfiles() {
        return this.f14910a;
    }
}
