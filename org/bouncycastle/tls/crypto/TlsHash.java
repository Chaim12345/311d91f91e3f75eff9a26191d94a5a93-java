package org.bouncycastle.tls.crypto;
/* loaded from: classes4.dex */
public interface TlsHash {
    byte[] calculateHash();

    TlsHash cloneHash();

    void reset();

    void update(byte[] bArr, int i2, int i3);
}
