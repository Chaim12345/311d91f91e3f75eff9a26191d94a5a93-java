package com.google.crypto.tink.subtle;
/* loaded from: classes2.dex */
public final class ImmutableByteArray {
    private final byte[] data;

    private ImmutableByteArray(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        this.data = bArr2;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
    }

    public static ImmutableByteArray of(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return of(bArr, 0, bArr.length);
    }

    public static ImmutableByteArray of(byte[] bArr, int i2, int i3) {
        return new ImmutableByteArray(bArr, i2, i3);
    }

    public byte[] getBytes() {
        byte[] bArr = this.data;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public int getLength() {
        return this.data.length;
    }
}
