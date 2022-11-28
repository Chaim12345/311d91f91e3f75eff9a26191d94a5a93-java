package org.bouncycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
/* loaded from: classes4.dex */
public class RainbowSigner implements MessageSigner {
    private static final int MAXITS = 65536;

    /* renamed from: a  reason: collision with root package name */
    int f14563a;

    /* renamed from: b  reason: collision with root package name */
    RainbowKeyParameters f14564b;
    private ComputeInField cf = new ComputeInField();
    private SecureRandom random;
    private short[] x;

    private short[] initSign(Layer[] layerArr, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        short[] multiplyMatrix = this.cf.multiplyMatrix(((RainbowPrivateKeyParameters) this.f14564b).getInvA1(), this.cf.addVect(((RainbowPrivateKeyParameters) this.f14564b).getB1(), sArr));
        for (int i2 = 0; i2 < layerArr[0].getVi(); i2++) {
            this.x[i2] = (short) this.random.nextInt();
            short[] sArr3 = this.x;
            sArr3[i2] = (short) (sArr3[i2] & 255);
        }
        return multiplyMatrix;
    }

    private short[] makeMessageRepresentative(byte[] bArr) {
        int i2 = this.f14563a;
        short[] sArr = new short[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length) {
            sArr[i3] = bArr[i4];
            sArr[i3] = (short) (sArr[i3] & 255);
            i4++;
            i3++;
            if (i3 >= i2) {
                break;
            }
        }
        return sArr;
    }

    private short[] verifySignatureIntern(short[] sArr) {
        short[][] coeffQuadratic = ((RainbowPublicKeyParameters) this.f14564b).getCoeffQuadratic();
        short[][] coeffSingular = ((RainbowPublicKeyParameters) this.f14564b).getCoeffSingular();
        short[] coeffScalar = ((RainbowPublicKeyParameters) this.f14564b).getCoeffScalar();
        short[] sArr2 = new short[coeffQuadratic.length];
        int length = coeffSingular[0].length;
        for (int i2 = 0; i2 < coeffQuadratic.length; i2++) {
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                for (int i5 = i4; i5 < length; i5++) {
                    sArr2[i2] = GF2Field.addElem(sArr2[i2], GF2Field.multElem(coeffQuadratic[i2][i3], GF2Field.multElem(sArr[i4], sArr[i5])));
                    i3++;
                }
                sArr2[i2] = GF2Field.addElem(sArr2[i2], GF2Field.multElem(coeffSingular[i2][i4], sArr[i4]));
            }
            sArr2[i2] = GF2Field.addElem(sArr2[i2], coeffScalar[i2]);
        }
        return sArr2;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        boolean z;
        Layer[] layers = ((RainbowPrivateKeyParameters) this.f14564b).getLayers();
        int length = layers.length;
        this.x = new short[((RainbowPrivateKeyParameters) this.f14564b).getInvA2().length];
        int viNext = layers[length - 1].getViNext();
        byte[] bArr2 = new byte[viNext];
        short[] makeMessageRepresentative = makeMessageRepresentative(bArr);
        int i2 = 0;
        do {
            try {
                short[] initSign = initSign(layers, makeMessageRepresentative);
                int i3 = 0;
                for (int i4 = 0; i4 < length; i4++) {
                    short[] sArr = new short[layers[i4].getOi()];
                    short[] sArr2 = new short[layers[i4].getOi()];
                    for (int i5 = 0; i5 < layers[i4].getOi(); i5++) {
                        sArr[i5] = initSign[i3];
                        i3++;
                    }
                    short[] solveEquation = this.cf.solveEquation(layers[i4].plugInVinegars(this.x), sArr);
                    if (solveEquation == null) {
                        throw new Exception("LES is not solveable!");
                        break;
                    }
                    for (int i6 = 0; i6 < solveEquation.length; i6++) {
                        this.x[layers[i4].getVi() + i6] = solveEquation[i6];
                    }
                }
                short[] multiplyMatrix = this.cf.multiplyMatrix(((RainbowPrivateKeyParameters) this.f14564b).getInvA2(), this.cf.addVect(((RainbowPrivateKeyParameters) this.f14564b).getB2(), this.x));
                for (int i7 = 0; i7 < viNext; i7++) {
                    bArr2[i7] = (byte) multiplyMatrix[i7];
                }
                z = true;
            } catch (Exception unused) {
                z = false;
            }
            if (z) {
                break;
            }
            i2++;
        } while (i2 < 65536);
        if (i2 != 65536) {
            return bArr2;
        }
        throw new IllegalStateException("unable to generate signature - LES not solvable");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        RainbowKeyParameters rainbowKeyParameters;
        if (!z) {
            rainbowKeyParameters = (RainbowPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            this.f14564b = (RainbowPrivateKeyParameters) parametersWithRandom.getParameters();
            this.f14563a = this.f14564b.getDocLength();
        } else {
            this.random = CryptoServicesRegistrar.getSecureRandom();
            rainbowKeyParameters = (RainbowPrivateKeyParameters) cipherParameters;
        }
        this.f14564b = rainbowKeyParameters;
        this.f14563a = this.f14564b.getDocLength();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        short[] sArr = new short[bArr2.length];
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            sArr[i2] = (short) (bArr2[i2] & 255);
        }
        short[] makeMessageRepresentative = makeMessageRepresentative(bArr);
        short[] verifySignatureIntern = verifySignatureIntern(sArr);
        if (makeMessageRepresentative.length != verifySignatureIntern.length) {
            return false;
        }
        boolean z = true;
        for (int i3 = 0; i3 < makeMessageRepresentative.length; i3++) {
            z = z && makeMessageRepresentative[i3] == verifySignatureIntern[i3];
        }
        return z;
    }
}
