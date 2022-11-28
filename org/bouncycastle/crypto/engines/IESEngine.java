package org.bouncycastle.crypto.engines;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.IESParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class IESEngine {
    private byte[] IV;

    /* renamed from: a  reason: collision with root package name */
    BasicAgreement f13371a;

    /* renamed from: b  reason: collision with root package name */
    DerivationFunction f13372b;

    /* renamed from: c  reason: collision with root package name */
    Mac f13373c;

    /* renamed from: d  reason: collision with root package name */
    BufferedBlockCipher f13374d;

    /* renamed from: e  reason: collision with root package name */
    boolean f13375e;

    /* renamed from: f  reason: collision with root package name */
    CipherParameters f13376f;

    /* renamed from: g  reason: collision with root package name */
    CipherParameters f13377g;

    /* renamed from: h  reason: collision with root package name */
    IESParameters f13378h;

    /* renamed from: i  reason: collision with root package name */
    byte[] f13379i;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        this.f13371a = basicAgreement;
        this.f13372b = derivationFunction;
        this.f13373c = mac;
        byte[] bArr = new byte[mac.getMacSize()];
        this.f13374d = null;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        this.f13371a = basicAgreement;
        this.f13372b = derivationFunction;
        this.f13373c = mac;
        byte[] bArr = new byte[mac.getMacSize()];
        this.f13374d = bufferedBlockCipher;
    }

    private byte[] decryptBlock(byte[] bArr, int i2, int i3) {
        byte[] bArr2;
        byte[] bArr3;
        int processBytes;
        if (i3 >= this.f13379i.length + this.f13373c.getMacSize()) {
            if (this.f13374d == null) {
                int length = (i3 - this.f13379i.length) - this.f13373c.getMacSize();
                byte[] bArr4 = new byte[length];
                int macKeySize = this.f13378h.getMacKeySize() / 8;
                bArr2 = new byte[macKeySize];
                int i4 = length + macKeySize;
                byte[] bArr5 = new byte[i4];
                this.f13372b.generateBytes(bArr5, 0, i4);
                if (this.f13379i.length != 0) {
                    System.arraycopy(bArr5, 0, bArr2, 0, macKeySize);
                    System.arraycopy(bArr5, macKeySize, bArr4, 0, length);
                } else {
                    System.arraycopy(bArr5, 0, bArr4, 0, length);
                    System.arraycopy(bArr5, length, bArr2, 0, macKeySize);
                }
                bArr3 = new byte[length];
                for (int i5 = 0; i5 != length; i5++) {
                    bArr3[i5] = (byte) (bArr[(this.f13379i.length + i2) + i5] ^ bArr4[i5]);
                }
                processBytes = 0;
            } else {
                int cipherKeySize = ((IESWithCipherParameters) this.f13378h).getCipherKeySize() / 8;
                byte[] bArr6 = new byte[cipherKeySize];
                int macKeySize2 = this.f13378h.getMacKeySize() / 8;
                bArr2 = new byte[macKeySize2];
                int i6 = cipherKeySize + macKeySize2;
                byte[] bArr7 = new byte[i6];
                this.f13372b.generateBytes(bArr7, 0, i6);
                System.arraycopy(bArr7, 0, bArr6, 0, cipherKeySize);
                System.arraycopy(bArr7, cipherKeySize, bArr2, 0, macKeySize2);
                CipherParameters keyParameter = new KeyParameter(bArr6);
                byte[] bArr8 = this.IV;
                if (bArr8 != null) {
                    keyParameter = new ParametersWithIV(keyParameter, bArr8);
                }
                this.f13374d.init(false, keyParameter);
                bArr3 = new byte[this.f13374d.getOutputSize((i3 - this.f13379i.length) - this.f13373c.getMacSize())];
                BufferedBlockCipher bufferedBlockCipher = this.f13374d;
                byte[] bArr9 = this.f13379i;
                processBytes = bufferedBlockCipher.processBytes(bArr, i2 + bArr9.length, (i3 - bArr9.length) - this.f13373c.getMacSize(), bArr3, 0);
            }
            byte[] encodingV = this.f13378h.getEncodingV();
            byte[] a2 = this.f13379i.length != 0 ? a(encodingV) : null;
            int i7 = i2 + i3;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i7 - this.f13373c.getMacSize(), i7);
            int length2 = copyOfRange.length;
            byte[] bArr10 = new byte[length2];
            this.f13373c.init(new KeyParameter(bArr2));
            Mac mac = this.f13373c;
            byte[] bArr11 = this.f13379i;
            mac.update(bArr, i2 + bArr11.length, (i3 - bArr11.length) - length2);
            if (encodingV != null) {
                this.f13373c.update(encodingV, 0, encodingV.length);
            }
            if (this.f13379i.length != 0) {
                this.f13373c.update(a2, 0, a2.length);
            }
            this.f13373c.doFinal(bArr10, 0);
            if (Arrays.constantTimeAreEqual(copyOfRange, bArr10)) {
                BufferedBlockCipher bufferedBlockCipher2 = this.f13374d;
                return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, processBytes + bufferedBlockCipher2.doFinal(bArr3, processBytes));
            }
            throw new InvalidCipherTextException("invalid MAC");
        }
        throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
    }

    private byte[] encryptBlock(byte[] bArr, int i2, int i3) {
        BufferedBlockCipher bufferedBlockCipher;
        CipherParameters keyParameter;
        byte[] bArr2;
        byte[] bArr3;
        if (this.f13374d == null) {
            byte[] bArr4 = new byte[i3];
            int macKeySize = this.f13378h.getMacKeySize() / 8;
            bArr3 = new byte[macKeySize];
            int i4 = i3 + macKeySize;
            byte[] bArr5 = new byte[i4];
            this.f13372b.generateBytes(bArr5, 0, i4);
            if (this.f13379i.length != 0) {
                System.arraycopy(bArr5, 0, bArr3, 0, macKeySize);
                System.arraycopy(bArr5, macKeySize, bArr4, 0, i3);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, i3);
                System.arraycopy(bArr5, i3, bArr3, 0, macKeySize);
            }
            bArr2 = new byte[i3];
            for (int i5 = 0; i5 != i3; i5++) {
                bArr2[i5] = (byte) (bArr[i2 + i5] ^ bArr4[i5]);
            }
        } else {
            int cipherKeySize = ((IESWithCipherParameters) this.f13378h).getCipherKeySize() / 8;
            byte[] bArr6 = new byte[cipherKeySize];
            int macKeySize2 = this.f13378h.getMacKeySize() / 8;
            byte[] bArr7 = new byte[macKeySize2];
            int i6 = cipherKeySize + macKeySize2;
            byte[] bArr8 = new byte[i6];
            this.f13372b.generateBytes(bArr8, 0, i6);
            System.arraycopy(bArr8, 0, bArr6, 0, cipherKeySize);
            System.arraycopy(bArr8, cipherKeySize, bArr7, 0, macKeySize2);
            if (this.IV != null) {
                bufferedBlockCipher = this.f13374d;
                keyParameter = new ParametersWithIV(new KeyParameter(bArr6), this.IV);
            } else {
                bufferedBlockCipher = this.f13374d;
                keyParameter = new KeyParameter(bArr6);
            }
            bufferedBlockCipher.init(true, keyParameter);
            bArr2 = new byte[this.f13374d.getOutputSize(i3)];
            int processBytes = this.f13374d.processBytes(bArr, i2, i3, bArr2, 0);
            i3 = processBytes + this.f13374d.doFinal(bArr2, processBytes);
            bArr3 = bArr7;
        }
        byte[] encodingV = this.f13378h.getEncodingV();
        byte[] a2 = this.f13379i.length != 0 ? a(encodingV) : null;
        int macSize = this.f13373c.getMacSize();
        byte[] bArr9 = new byte[macSize];
        this.f13373c.init(new KeyParameter(bArr3));
        this.f13373c.update(bArr2, 0, bArr2.length);
        if (encodingV != null) {
            this.f13373c.update(encodingV, 0, encodingV.length);
        }
        if (this.f13379i.length != 0) {
            this.f13373c.update(a2, 0, a2.length);
        }
        this.f13373c.doFinal(bArr9, 0);
        byte[] bArr10 = this.f13379i;
        byte[] bArr11 = new byte[bArr10.length + i3 + macSize];
        System.arraycopy(bArr10, 0, bArr11, 0, bArr10.length);
        System.arraycopy(bArr2, 0, bArr11, this.f13379i.length, i3);
        System.arraycopy(bArr9, 0, bArr11, this.f13379i.length + i3, macSize);
        return bArr11;
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.IV = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            this.IV = null;
        }
        this.f13378h = (IESParameters) cipherParameters;
    }

    protected byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(bArr.length * 8, bArr2, 0);
        }
        return bArr2;
    }

    public BufferedBlockCipher getCipher() {
        return this.f13374d;
    }

    public Mac getMac() {
        return this.f13373c;
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.f13375e = false;
        this.f13376f = asymmetricKeyParameter;
        this.keyParser = keyParser;
        extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.f13375e = true;
        this.f13377g = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.f13375e = z;
        this.f13376f = cipherParameters;
        this.f13377g = cipherParameters2;
        this.f13379i = new byte[0];
        extractParams(cipherParameters3);
    }

    public byte[] processBlock(byte[] bArr, int i2, int i3) {
        if (this.f13375e) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.keyPairGenerator;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair generate = ephemeralKeyPairGenerator.generate();
                this.f13376f = generate.getKeyPair().getPrivate();
                this.f13379i = generate.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i2, i3);
            try {
                this.f13377g = this.keyParser.readKey(byteArrayInputStream);
                this.f13379i = Arrays.copyOfRange(bArr, i2, (i3 - byteArrayInputStream.available()) + i2);
            } catch (IOException e2) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e2.getMessage(), e2);
            } catch (IllegalArgumentException e3) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e3.getMessage(), e3);
            }
        }
        this.f13371a.init(this.f13376f);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.f13371a.getFieldSize(), this.f13371a.calculateAgreement(this.f13377g));
        byte[] bArr2 = this.f13379i;
        if (bArr2.length != 0) {
            byte[] concatenate = Arrays.concatenate(bArr2, asUnsignedByteArray);
            Arrays.fill(asUnsignedByteArray, (byte) 0);
            asUnsignedByteArray = concatenate;
        }
        try {
            this.f13372b.init(new KDFParameters(asUnsignedByteArray, this.f13378h.getDerivationV()));
            return this.f13375e ? encryptBlock(bArr, i2, i3) : decryptBlock(bArr, i2, i3);
        } finally {
            Arrays.fill(asUnsignedByteArray, (byte) 0);
        }
    }
}
