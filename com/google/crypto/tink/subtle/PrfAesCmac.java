package com.google.crypto.tink.subtle;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
@Immutable
/* loaded from: classes2.dex */
public final class PrfAesCmac implements Prf {
    private final SecretKey keySpec;
    private byte[] subKey1;
    private byte[] subKey2;

    public PrfAesCmac(byte[] bArr) {
        Validators.validateAesKeySize(bArr.length);
        this.keySpec = new SecretKeySpec(bArr, "AES");
        generateSubKeys();
    }

    private void generateSubKeys() {
        Cipher instance = instance();
        instance.init(1, this.keySpec);
        byte[] b2 = AesUtil.b(instance.doFinal(new byte[16]));
        this.subKey1 = b2;
        this.subKey2 = AesUtil.b(b2);
    }

    private static Cipher instance() {
        return EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] bArr, int i2) {
        if (i2 <= 16) {
            Cipher instance = instance();
            instance.init(1, this.keySpec);
            int max = Math.max(1, (int) Math.ceil(bArr.length / 16.0d));
            byte[] xor = max * 16 == bArr.length ? Bytes.xor(bArr, (max - 1) * 16, this.subKey1, 0, 16) : Bytes.xor(AesUtil.a(Arrays.copyOfRange(bArr, (max - 1) * 16, bArr.length)), this.subKey2);
            byte[] bArr2 = new byte[16];
            for (int i3 = 0; i3 < max - 1; i3++) {
                bArr2 = instance.doFinal(Bytes.xor(bArr2, 0, bArr, i3 * 16, 16));
            }
            return Arrays.copyOf(instance.doFinal(Bytes.xor(xor, bArr2)), i2);
        }
        throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
    }
}
