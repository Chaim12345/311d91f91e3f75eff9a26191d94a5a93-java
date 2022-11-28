package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.ExchangePair;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class NHSecretKeyProcessor {
    private final Xof xof;

    /* loaded from: classes4.dex */
    public static class PartyUBuilder {
        private final AsymmetricCipherKeyPair aKp;
        private final NHAgreement agreement;
        private byte[] sharedInfo;
        private boolean used;

        public PartyUBuilder(SecureRandom secureRandom) {
            NHAgreement nHAgreement = new NHAgreement();
            this.agreement = nHAgreement;
            this.sharedInfo = null;
            this.used = false;
            NHKeyPairGenerator nHKeyPairGenerator = new NHKeyPairGenerator();
            nHKeyPairGenerator.init(new KeyGenerationParameters(secureRandom, 2048));
            AsymmetricCipherKeyPair generateKeyPair = nHKeyPairGenerator.generateKeyPair();
            this.aKp = generateKeyPair;
            nHAgreement.init(generateKeyPair.getPrivate());
        }

        public NHSecretKeyProcessor build(byte[] bArr) {
            if (this.used) {
                throw new IllegalStateException("builder already used");
            }
            this.used = true;
            return new NHSecretKeyProcessor(this.agreement.calculateAgreement(new NHPublicKeyParameters(bArr)), this.sharedInfo);
        }

        public byte[] getPartA() {
            return ((NHPublicKeyParameters) this.aKp.getPublic()).getPubData();
        }

        public PartyUBuilder withSharedInfo(byte[] bArr) {
            this.sharedInfo = Arrays.clone(bArr);
            return this;
        }
    }

    /* loaded from: classes4.dex */
    public static class PartyVBuilder {

        /* renamed from: a  reason: collision with root package name */
        protected final SecureRandom f14558a;
        private byte[] sharedInfo = null;
        private byte[] sharedSecret = null;
        private boolean used = false;

        public PartyVBuilder(SecureRandom secureRandom) {
            this.f14558a = secureRandom;
        }

        public NHSecretKeyProcessor build() {
            if (this.used) {
                throw new IllegalStateException("builder already used");
            }
            this.used = true;
            return new NHSecretKeyProcessor(this.sharedSecret, this.sharedInfo);
        }

        public byte[] getPartB(byte[] bArr) {
            ExchangePair generateExchange = new NHExchangePairGenerator(this.f14558a).generateExchange(new NHPublicKeyParameters(bArr));
            this.sharedSecret = generateExchange.getSharedValue();
            return ((NHPublicKeyParameters) generateExchange.getPublicKey()).getPubData();
        }

        public PartyVBuilder withSharedInfo(byte[] bArr) {
            this.sharedInfo = Arrays.clone(bArr);
            return this;
        }
    }

    private NHSecretKeyProcessor(byte[] bArr, byte[] bArr2) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        this.xof = sHAKEDigest;
        sHAKEDigest.update(bArr, 0, bArr.length);
        if (bArr2 != null) {
            sHAKEDigest.update(bArr2, 0, bArr2.length);
        }
        Arrays.fill(bArr, (byte) 0);
    }

    private static void xor(byte[] bArr, byte[] bArr2) {
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
    }

    public byte[] processKey(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        this.xof.doFinal(bArr2, 0, length);
        xor(bArr, bArr2);
        Arrays.fill(bArr2, (byte) 0);
        return bArr;
    }
}
