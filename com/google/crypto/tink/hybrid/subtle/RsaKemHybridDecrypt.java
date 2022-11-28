package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;
/* loaded from: classes2.dex */
public final class RsaKemHybridDecrypt implements HybridDecrypt {
    private final AeadFactory aeadFactory;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final RSAPrivateKey recipientPrivateKey;

    public RsaKemHybridDecrypt(RSAPrivateKey rSAPrivateKey, String str, byte[] bArr, AeadFactory aeadFactory) {
        RsaKem.d(rSAPrivateKey.getModulus());
        this.recipientPrivateKey = rSAPrivateKey;
        this.hkdfSalt = bArr;
        this.hkdfHmacAlgo = str;
        this.aeadFactory = aeadFactory;
    }

    @Override // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(byte[] bArr, byte[] bArr2) {
        int a2 = RsaKem.a(this.recipientPrivateKey.getModulus());
        if (bArr.length >= a2) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            byte[] bArr3 = new byte[a2];
            wrap.get(bArr3);
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(2, this.recipientPrivateKey);
            Aead createAead = this.aeadFactory.createAead(Hkdf.computeHkdf(this.hkdfHmacAlgo, cipher.doFinal(bArr3), this.hkdfSalt, bArr2, this.aeadFactory.getKeySizeInBytes()));
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4);
            return createAead.decrypt(bArr4, RsaKem.f9630a);
        }
        throw new GeneralSecurityException(String.format("Ciphertext must be of at least size %d bytes, but got %d", Integer.valueOf(a2), Integer.valueOf(bArr.length)));
    }
}
