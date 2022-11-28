package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
/* loaded from: classes4.dex */
public class GMSSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.3";
    private int[] K;
    private byte[][] currentRootSigs;
    private byte[][] currentSeeds;
    private GMSSDigestProvider digestProvider;
    private GMSSParameters gmssPS;
    private GMSSKeyGenerationParameters gmssParams;
    private GMSSRandom gmssRandom;
    private int[] heightOfTrees;
    private boolean initialized = false;
    private int mdLength;
    private Digest messDigestTree;
    private byte[][] nextNextSeeds;
    private int numLayer;
    private int[] otsIndex;

    public GMSSKeyPairGenerator(GMSSDigestProvider gMSSDigestProvider) {
        this.digestProvider = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.messDigestTree = digest;
        this.mdLength = digest.getDigestSize();
        this.gmssRandom = new GMSSRandom(this.messDigestTree);
    }

    private AsymmetricCipherKeyPair genKeyPair() {
        int i2;
        int i3;
        if (!this.initialized) {
            initializeDefault();
        }
        int i4 = this.numLayer;
        byte[][][] bArr = new byte[i4][];
        byte[][][] bArr2 = new byte[i4 - 1][];
        Treehash[][] treehashArr = new Treehash[i4];
        Treehash[][] treehashArr2 = new Treehash[i4 - 1];
        Vector[] vectorArr = new Vector[i4];
        Vector[] vectorArr2 = new Vector[i4 - 1];
        Vector[][] vectorArr3 = new Vector[i4];
        int i5 = 1;
        Vector[][] vectorArr4 = new Vector[i4 - 1];
        int i6 = 0;
        while (true) {
            i2 = this.numLayer;
            if (i6 >= i2) {
                break;
            }
            Vector[][] vectorArr5 = vectorArr4;
            bArr[i6] = (byte[][]) Array.newInstance(byte.class, this.heightOfTrees[i6], this.mdLength);
            int[] iArr = this.heightOfTrees;
            treehashArr[i6] = new Treehash[iArr[i6] - this.K[i6]];
            if (i6 > 0) {
                int i7 = i6 - 1;
                bArr2[i7] = (byte[][]) Array.newInstance(byte.class, iArr[i6], this.mdLength);
                treehashArr2[i7] = new Treehash[this.heightOfTrees[i6] - this.K[i6]];
            }
            vectorArr[i6] = new Vector();
            if (i6 > 0) {
                vectorArr2[i6 - 1] = new Vector();
            }
            i6++;
            vectorArr4 = vectorArr5;
        }
        Vector[][] vectorArr6 = vectorArr4;
        byte[][] bArr3 = (byte[][]) Array.newInstance(byte.class, i2, this.mdLength);
        byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, this.numLayer - 1, this.mdLength);
        byte[][] bArr5 = (byte[][]) Array.newInstance(byte.class, this.numLayer, this.mdLength);
        int i8 = 0;
        while (true) {
            i3 = this.numLayer;
            if (i8 >= i3) {
                break;
            }
            System.arraycopy(this.currentSeeds[i8], 0, bArr5[i8], 0, this.mdLength);
            i8++;
            i5 = 1;
        }
        int[] iArr2 = new int[2];
        iArr2[i5] = this.mdLength;
        iArr2[0] = i3 - i5;
        this.currentRootSigs = (byte[][]) Array.newInstance(byte.class, iArr2);
        int i9 = this.numLayer - i5;
        while (i9 >= 0) {
            GMSSRootCalc generateCurrentAuthpathAndRoot = i9 == this.numLayer - i5 ? generateCurrentAuthpathAndRoot(null, vectorArr[i9], bArr5[i9], i9) : generateCurrentAuthpathAndRoot(bArr3[i9 + 1], vectorArr[i9], bArr5[i9], i9);
            for (int i10 = 0; i10 < this.heightOfTrees[i9]; i10++) {
                System.arraycopy(generateCurrentAuthpathAndRoot.getAuthPath()[i10], 0, bArr[i9][i10], 0, this.mdLength);
            }
            vectorArr3[i9] = generateCurrentAuthpathAndRoot.getRetain();
            treehashArr[i9] = generateCurrentAuthpathAndRoot.getTreehash();
            System.arraycopy(generateCurrentAuthpathAndRoot.getRoot(), 0, bArr3[i9], 0, this.mdLength);
            i9--;
            i5 = 1;
        }
        int i11 = this.numLayer - 2;
        while (i11 >= 0) {
            int i12 = i11 + 1;
            GMSSRootCalc generateNextAuthpathAndRoot = generateNextAuthpathAndRoot(vectorArr2[i11], bArr5[i12], i12);
            int i13 = 0;
            while (i13 < this.heightOfTrees[i12]) {
                System.arraycopy(generateNextAuthpathAndRoot.getAuthPath()[i13], 0, bArr2[i11][i13], 0, this.mdLength);
                i13++;
                vectorArr3 = vectorArr3;
            }
            vectorArr6[i11] = generateNextAuthpathAndRoot.getRetain();
            treehashArr2[i11] = generateNextAuthpathAndRoot.getTreehash();
            System.arraycopy(generateNextAuthpathAndRoot.getRoot(), 0, bArr4[i11], 0, this.mdLength);
            System.arraycopy(bArr5[i12], 0, this.nextNextSeeds[i11], 0, this.mdLength);
            i11--;
            vectorArr3 = vectorArr3;
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new GMSSPublicKeyParameters(bArr3[0], this.gmssPS), (AsymmetricKeyParameter) new GMSSPrivateKeyParameters(this.currentSeeds, this.nextNextSeeds, bArr, bArr2, treehashArr, treehashArr2, vectorArr, vectorArr2, vectorArr3, vectorArr6, bArr4, this.currentRootSigs, this.gmssPS, this.digestProvider));
    }

    private GMSSRootCalc generateCurrentAuthpathAndRoot(byte[] bArr, Vector vector, byte[] bArr2, int i2) {
        byte[] Verify;
        int i3 = this.mdLength;
        byte[] bArr3 = new byte[i3];
        byte[] bArr4 = new byte[i3];
        byte[] nextSeed = this.gmssRandom.nextSeed(bArr2);
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[i2], this.K[i2], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        if (i2 == this.numLayer - 1) {
            Verify = new WinternitzOTSignature(nextSeed, this.digestProvider.get(), this.otsIndex[i2]).getPublicKey();
        } else {
            this.currentRootSigs[i2] = new WinternitzOTSignature(nextSeed, this.digestProvider.get(), this.otsIndex[i2]).getSignature(bArr);
            Verify = new WinternitzOTSVerify(this.digestProvider.get(), this.otsIndex[i2]).Verify(bArr, this.currentRootSigs[i2]);
        }
        gMSSRootCalc.update(Verify);
        int i4 = 3;
        int i5 = 0;
        int i6 = 1;
        while (true) {
            int[] iArr = this.heightOfTrees;
            if (i6 >= (1 << iArr[i2])) {
                break;
            }
            if (i6 == i4 && i5 < iArr[i2] - this.K[i2]) {
                gMSSRootCalc.initializeTreehashSeed(bArr2, i5);
                i4 *= 2;
                i5++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr2), this.digestProvider.get(), this.otsIndex[i2]).getPublicKey());
            i6++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private GMSSRootCalc generateNextAuthpathAndRoot(Vector vector, byte[] bArr, int i2) {
        byte[] bArr2 = new byte[this.numLayer];
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[i2], this.K[i2], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        int i3 = 0;
        int i4 = 3;
        int i5 = 0;
        while (true) {
            int[] iArr = this.heightOfTrees;
            if (i3 >= (1 << iArr[i2])) {
                break;
            }
            if (i3 == i4 && i5 < iArr[i2] - this.K[i2]) {
                gMSSRootCalc.initializeTreehashSeed(bArr, i5);
                i4 *= 2;
                i5++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr), this.digestProvider.get(), this.otsIndex[i2]).getPublicKey());
            i3++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("N�chster Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private void initializeDefault() {
        initialize(new GMSSKeyGenerationParameters(null, new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{3, 3, 3, 3}, new int[]{2, 2, 2, 2})));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(int i2, SecureRandom secureRandom) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters;
        if (i2 <= 10) {
            gMSSKeyGenerationParameters = new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(1, new int[]{10}, new int[]{3}, new int[]{2}));
        } else {
            gMSSKeyGenerationParameters = i2 <= 20 ? new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2})) : new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2}));
        }
        initialize(gMSSKeyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters = (GMSSKeyGenerationParameters) keyGenerationParameters;
        this.gmssParams = gMSSKeyGenerationParameters;
        GMSSParameters gMSSParameters = new GMSSParameters(gMSSKeyGenerationParameters.getParameters().getNumOfLayers(), this.gmssParams.getParameters().getHeightOfTrees(), this.gmssParams.getParameters().getWinternitzParameter(), this.gmssParams.getParameters().getK());
        this.gmssPS = gMSSParameters;
        this.numLayer = gMSSParameters.getNumOfLayers();
        this.heightOfTrees = this.gmssPS.getHeightOfTrees();
        this.otsIndex = this.gmssPS.getWinternitzParameter();
        this.K = this.gmssPS.getK();
        this.currentSeeds = (byte[][]) Array.newInstance(byte.class, this.numLayer, this.mdLength);
        this.nextNextSeeds = (byte[][]) Array.newInstance(byte.class, this.numLayer - 1, this.mdLength);
        SecureRandom random = keyGenerationParameters.getRandom();
        for (int i2 = 0; i2 < this.numLayer; i2++) {
            random.nextBytes(this.currentSeeds[i2]);
            this.gmssRandom.nextSeed(this.currentSeeds[i2]);
        }
        this.initialized = true;
    }
}
