package org.bouncycastle.pqc.crypto.lms;

import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
import org.bouncycastle.pqc.crypto.ExhaustedPrivateKeyException;
/* loaded from: classes4.dex */
class HSS {

    /* loaded from: classes4.dex */
    static class PlaceholderLMSPrivateKey extends LMSPrivateKeyParameters {
        public PlaceholderLMSPrivateKey(LMSigParameters lMSigParameters, LMOtsParameters lMOtsParameters, int i2, byte[] bArr, int i3, byte[] bArr2) {
            super(lMSigParameters, lMOtsParameters, i2, bArr, i3, bArr2);
        }

        @Override // org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters
        LMOtsPrivateKey c() {
            throw new RuntimeException("placeholder only");
        }

        @Override // org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters
        public LMSPublicKeyParameters getPublicKey() {
            throw new RuntimeException("placeholder only");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(HSSPrivateKeyParameters hSSPrivateKeyParameters) {
        synchronized (hSSPrivateKeyParameters) {
            if (hSSPrivateKeyParameters.getIndex() >= hSSPrivateKeyParameters.a()) {
                StringBuilder sb = new StringBuilder();
                sb.append("hss private key");
                sb.append(hSSPrivateKeyParameters.f() ? " shard" : "");
                sb.append(" is exhausted");
                throw new ExhaustedPrivateKeyException(sb.toString());
            }
            int l2 = hSSPrivateKeyParameters.getL();
            List b2 = hSSPrivateKeyParameters.b();
            int i2 = l2;
            while (true) {
                int i3 = i2 - 1;
                if (((LMSPrivateKeyParameters) b2.get(i3)).getIndex() != (1 << ((LMSPrivateKeyParameters) b2.get(i3)).getSigParameters().getH())) {
                    while (i2 < l2) {
                        hSSPrivateKeyParameters.g(i2);
                        i2++;
                    }
                } else if (i3 == 0) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("hss private key");
                    sb2.append(hSSPrivateKeyParameters.f() ? " shard" : "");
                    sb2.append(" is exhausted the maximum limit for this HSS private key");
                    throw new ExhaustedPrivateKeyException(sb2.toString());
                } else {
                    i2 = i3;
                }
            }
        }
    }

    public static HSSPrivateKeyParameters generateHSSKeyPair(HSSKeyGenerationParameters hSSKeyGenerationParameters) {
        int i2;
        byte[] bArr;
        int depth = hSSKeyGenerationParameters.getDepth();
        LMSPrivateKeyParameters[] lMSPrivateKeyParametersArr = new LMSPrivateKeyParameters[depth];
        LMSSignature[] lMSSignatureArr = new LMSSignature[hSSKeyGenerationParameters.getDepth() - 1];
        byte[] bArr2 = new byte[32];
        hSSKeyGenerationParameters.getRandom().nextBytes(bArr2);
        byte[] bArr3 = new byte[16];
        hSSKeyGenerationParameters.getRandom().nextBytes(bArr3);
        byte[] bArr4 = new byte[0];
        int i3 = 0;
        long j2 = 1;
        while (i3 < depth) {
            if (i3 == 0) {
                lMSPrivateKeyParametersArr[i3] = new LMSPrivateKeyParameters(hSSKeyGenerationParameters.getLmsParameters()[i3].getLMSigParam(), hSSKeyGenerationParameters.getLmsParameters()[i3].getLMOTSParam(), 0, bArr3, 1 << hSSKeyGenerationParameters.getLmsParameters()[i3].getLMSigParam().getH(), bArr2);
                i2 = i3;
                bArr = bArr4;
            } else {
                i2 = i3;
                bArr = bArr4;
                lMSPrivateKeyParametersArr[i2] = new PlaceholderLMSPrivateKey(hSSKeyGenerationParameters.getLmsParameters()[i3].getLMSigParam(), hSSKeyGenerationParameters.getLmsParameters()[i3].getLMOTSParam(), -1, bArr, 1 << hSSKeyGenerationParameters.getLmsParameters()[i3].getLMSigParam().getH(), bArr);
            }
            j2 *= 1 << hSSKeyGenerationParameters.getLmsParameters()[i2].getLMSigParam().getH();
            i3 = i2 + 1;
            bArr4 = bArr;
        }
        if (j2 == 0) {
            j2 = LongCompanionObject.MAX_VALUE;
        }
        return new HSSPrivateKeyParameters(hSSKeyGenerationParameters.getDepth(), Arrays.asList(lMSPrivateKeyParametersArr), Arrays.asList(lMSSignatureArr), 0L, j2);
    }

    public static HSSSignature generateSignature(int i2, LMSContext lMSContext) {
        return new HSSSignature(i2 - 1, lMSContext.f(), LMS.generateSign(lMSContext));
    }

    public static HSSSignature generateSignature(HSSPrivateKeyParameters hSSPrivateKeyParameters, byte[] bArr) {
        LMSPrivateKeyParameters lMSPrivateKeyParameters;
        LMSSignedPubKey[] lMSSignedPubKeyArr;
        int l2 = hSSPrivateKeyParameters.getL();
        synchronized (hSSPrivateKeyParameters) {
            a(hSSPrivateKeyParameters);
            List b2 = hSSPrivateKeyParameters.b();
            List d2 = hSSPrivateKeyParameters.d();
            int i2 = l2 - 1;
            lMSPrivateKeyParameters = (LMSPrivateKeyParameters) hSSPrivateKeyParameters.b().get(i2);
            lMSSignedPubKeyArr = new LMSSignedPubKey[i2];
            int i3 = 0;
            while (i3 < i2) {
                int i4 = i3 + 1;
                lMSSignedPubKeyArr[i3] = new LMSSignedPubKey((LMSSignature) d2.get(i3), ((LMSPrivateKeyParameters) b2.get(i4)).getPublicKey());
                i3 = i4;
            }
            hSSPrivateKeyParameters.e();
        }
        LMSContext g2 = lMSPrivateKeyParameters.generateLMSContext().g(lMSSignedPubKeyArr);
        g2.update(bArr, 0, bArr.length);
        return generateSignature(l2, g2);
    }

    public static void incrementIndex(HSSPrivateKeyParameters hSSPrivateKeyParameters) {
        synchronized (hSSPrivateKeyParameters) {
            a(hSSPrivateKeyParameters);
            hSSPrivateKeyParameters.e();
            ((LMSPrivateKeyParameters) hSSPrivateKeyParameters.b().get(hSSPrivateKeyParameters.getL() - 1)).d();
        }
    }

    public static boolean verifySignature(HSSPublicKeyParameters hSSPublicKeyParameters, HSSSignature hSSSignature, byte[] bArr) {
        int i2 = hSSSignature.getlMinus1();
        int i3 = i2 + 1;
        if (i3 != hSSPublicKeyParameters.getL()) {
            return false;
        }
        LMSSignature[] lMSSignatureArr = new LMSSignature[i3];
        LMSPublicKeyParameters[] lMSPublicKeyParametersArr = new LMSPublicKeyParameters[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            lMSSignatureArr[i4] = hSSSignature.getSignedPubKey()[i4].getSignature();
            lMSPublicKeyParametersArr[i4] = hSSSignature.getSignedPubKey()[i4].getPublicKey();
        }
        lMSSignatureArr[i2] = hSSSignature.getSignature();
        LMSPublicKeyParameters lMSPublicKey = hSSPublicKeyParameters.getLMSPublicKey();
        for (int i5 = 0; i5 < i2; i5++) {
            if (!LMS.verifySignature(lMSPublicKey, lMSSignatureArr[i5], lMSPublicKeyParametersArr[i5].toByteArray())) {
                return false;
            }
            try {
                lMSPublicKey = lMSPublicKeyParametersArr[i5];
            } catch (Exception e2) {
                throw new IllegalStateException(e2.getMessage(), e2);
            }
        }
        return LMS.verifySignature(lMSPublicKey, lMSSignatureArr[i2], bArr);
    }
}
