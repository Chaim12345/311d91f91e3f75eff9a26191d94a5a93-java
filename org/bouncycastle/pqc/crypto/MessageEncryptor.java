package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes4.dex */
public interface MessageEncryptor {
    void init(boolean z, CipherParameters cipherParameters);

    byte[] messageDecrypt(byte[] bArr);

    byte[] messageEncrypt(byte[] bArr);
}
