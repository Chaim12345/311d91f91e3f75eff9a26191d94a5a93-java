package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public interface Xof extends ExtendedDigest {
    int doFinal(byte[] bArr, int i2, int i3);

    int doOutput(byte[] bArr, int i2, int i3);
}
