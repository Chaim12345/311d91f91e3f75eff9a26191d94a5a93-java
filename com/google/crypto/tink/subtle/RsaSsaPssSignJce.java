package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.subtle.EngineWrapper;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import org.bouncycastle.crypto.signers.PSSSigner;
/* loaded from: classes2.dex */
public final class RsaSsaPssSignJce implements PublicKeySign {
    private static final String RAW_RSA_ALGORITHM = "RSA/ECB/NOPADDING";
    private final Enums.HashType mgf1Hash;
    private final RSAPrivateCrtKey privateKey;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssSignJce(RSAPrivateCrtKey rSAPrivateCrtKey, Enums.HashType hashType, Enums.HashType hashType2, int i2) {
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPrivateCrtKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPrivateCrtKey.getPublicExponent());
        this.privateKey = rSAPrivateCrtKey;
        this.publicKey = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent()));
        this.sigHash = hashType;
        this.mgf1Hash = hashType2;
        this.saltLength = i2;
    }

    private byte[] emsaPssEncode(byte[] bArr, int i2) {
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] digest = engineFactory.digest(bArr);
        int digestLength = engineFactory.getDigestLength();
        int i3 = ((i2 - 1) / 8) + 1;
        int i4 = this.saltLength;
        if (i3 >= digestLength + i4 + 2) {
            byte[] randBytes = Random.randBytes(i4);
            int i5 = digestLength + 8;
            byte[] bArr2 = new byte[this.saltLength + i5];
            System.arraycopy(digest, 0, bArr2, 8, digestLength);
            System.arraycopy(randBytes, 0, bArr2, i5, randBytes.length);
            byte[] digest2 = engineFactory.digest(bArr2);
            int i6 = (i3 - digestLength) - 1;
            byte[] bArr3 = new byte[i6];
            int i7 = this.saltLength;
            bArr3[((i3 - i7) - digestLength) - 2] = 1;
            System.arraycopy(randBytes, 0, bArr3, ((i3 - i7) - digestLength) - 1, randBytes.length);
            byte[] mgf1 = SubtleUtil.mgf1(digest2, i6, this.mgf1Hash);
            byte[] bArr4 = new byte[i6];
            for (int i8 = 0; i8 < i6; i8++) {
                bArr4[i8] = (byte) (bArr3[i8] ^ mgf1[i8]);
            }
            for (int i9 = 0; i9 < (i3 * 8) - i2; i9++) {
                int i10 = i9 / 8;
                bArr4[i10] = (byte) ((~(1 << (7 - (i9 % 8)))) & bArr4[i10]);
            }
            int i11 = digestLength + i6;
            byte[] bArr5 = new byte[i11 + 1];
            System.arraycopy(bArr4, 0, bArr5, 0, i6);
            System.arraycopy(digest2, 0, bArr5, i6, digest2.length);
            bArr5[i11] = PSSSigner.TRAILER_IMPLICIT;
            return bArr5;
        }
        throw new GeneralSecurityException("encoding error");
    }

    private byte[] rsasp1(byte[] bArr) {
        EngineFactory<EngineWrapper.TCipher, Cipher> engineFactory = EngineFactory.CIPHER;
        Cipher engineFactory2 = engineFactory.getInstance(RAW_RSA_ALGORITHM);
        engineFactory2.init(2, this.privateKey);
        byte[] doFinal = engineFactory2.doFinal(bArr);
        Cipher engineFactory3 = engineFactory.getInstance(RAW_RSA_ALGORITHM);
        engineFactory3.init(1, this.publicKey);
        if (new BigInteger(1, bArr).equals(new BigInteger(1, engineFactory3.doFinal(doFinal)))) {
            return doFinal;
        }
        throw new RuntimeException("Security bug: RSA signature computation error");
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) {
        return rsasp1(emsaPssEncode(bArr, this.publicKey.getModulus().bitLength() - 1));
    }
}
