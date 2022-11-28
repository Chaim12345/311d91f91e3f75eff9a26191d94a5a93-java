package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes4.dex */
class LMS {
    public static LMSPrivateKeyParameters generateKeys(LMSigParameters lMSigParameters, LMOtsParameters lMOtsParameters, int i2, byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr2.length < lMSigParameters.getM()) {
            throw new IllegalArgumentException("root seed is less than " + lMSigParameters.getM());
        }
        return new LMSPrivateKeyParameters(lMSigParameters, lMOtsParameters, i2, bArr, 1 << lMSigParameters.getH(), bArr2);
    }

    public static LMSSignature generateSign(LMSContext lMSContext) {
        return new LMSSignature(lMSContext.c().getQ(), LM_OTS.lm_ots_generate_signature(lMSContext.c(), lMSContext.d(), lMSContext.a()), lMSContext.e(), lMSContext.b());
    }

    public static LMSSignature generateSign(LMSPrivateKeyParameters lMSPrivateKeyParameters, byte[] bArr) {
        LMSContext generateLMSContext = lMSPrivateKeyParameters.generateLMSContext();
        generateLMSContext.update(bArr, 0, bArr.length);
        return generateSign(generateLMSContext);
    }

    public static boolean verifySignature(LMSPublicKeyParameters lMSPublicKeyParameters, LMSContext lMSContext) {
        LMSSignature lMSSignature = (LMSSignature) lMSContext.getSignature();
        LMSigParameters parameter = lMSSignature.getParameter();
        int h2 = parameter.getH();
        byte[][] y = lMSSignature.getY();
        byte[] lm_ots_validate_signature_calculate = LM_OTS.lm_ots_validate_signature_calculate(lMSContext);
        int q2 = (1 << h2) + lMSSignature.getQ();
        byte[] i2 = lMSPublicKeyParameters.getI();
        Digest a2 = DigestUtil.a(parameter.getDigestOID());
        int digestSize = a2.getDigestSize();
        byte[] bArr = new byte[digestSize];
        a2.update(i2, 0, i2.length);
        LmsUtils.e(q2, a2);
        LmsUtils.d((short) -32126, a2);
        a2.update(lm_ots_validate_signature_calculate, 0, lm_ots_validate_signature_calculate.length);
        a2.doFinal(bArr, 0);
        int i3 = 0;
        while (q2 > 1) {
            if ((q2 & 1) == 1) {
                a2.update(i2, 0, i2.length);
                LmsUtils.e(q2 / 2, a2);
                LmsUtils.d((short) -31869, a2);
                a2.update(y[i3], 0, y[i3].length);
                a2.update(bArr, 0, digestSize);
            } else {
                a2.update(i2, 0, i2.length);
                LmsUtils.e(q2 / 2, a2);
                LmsUtils.d((short) -31869, a2);
                a2.update(bArr, 0, digestSize);
                a2.update(y[i3], 0, y[i3].length);
            }
            a2.doFinal(bArr, 0);
            q2 /= 2;
            i3++;
        }
        return lMSPublicKeyParameters.b(bArr);
    }

    public static boolean verifySignature(LMSPublicKeyParameters lMSPublicKeyParameters, LMSSignature lMSSignature, byte[] bArr) {
        LMSContext a2 = lMSPublicKeyParameters.a(lMSSignature);
        LmsUtils.b(bArr, a2);
        return verifySignature(lMSPublicKeyParameters, a2);
    }

    public static boolean verifySignature(LMSPublicKeyParameters lMSPublicKeyParameters, byte[] bArr, byte[] bArr2) {
        LMSContext generateLMSContext = lMSPublicKeyParameters.generateLMSContext(bArr);
        LmsUtils.b(bArr2, generateLMSContext);
        return verifySignature(lMSPublicKeyParameters, generateLMSContext);
    }
}
