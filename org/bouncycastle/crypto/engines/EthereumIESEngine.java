package org.bouncycastle.crypto.engines;

import androidx.core.view.InputDeviceCompat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.DigestDerivationFunction;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.IESParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.ISO18033KDFParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class EthereumIESEngine {
    private byte[] IV;

    /* renamed from: a  reason: collision with root package name */
    BasicAgreement f13357a;

    /* renamed from: b  reason: collision with root package name */
    DerivationFunction f13358b;

    /* renamed from: c  reason: collision with root package name */
    Mac f13359c;

    /* renamed from: d  reason: collision with root package name */
    BufferedBlockCipher f13360d;

    /* renamed from: e  reason: collision with root package name */
    byte[] f13361e;

    /* renamed from: f  reason: collision with root package name */
    boolean f13362f;

    /* renamed from: g  reason: collision with root package name */
    CipherParameters f13363g;

    /* renamed from: h  reason: collision with root package name */
    CipherParameters f13364h;

    /* renamed from: i  reason: collision with root package name */
    IESParameters f13365i;

    /* renamed from: j  reason: collision with root package name */
    byte[] f13366j;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;

    /* loaded from: classes3.dex */
    public static class HandshakeKDFFunction implements DigestDerivationFunction {
        private int counterStart;
        private Digest digest;
        private byte[] iv;
        private byte[] shared;

        public HandshakeKDFFunction(int i2, Digest digest) {
            this.counterStart = i2;
            this.digest = digest;
        }

        @Override // org.bouncycastle.crypto.DerivationFunction
        public int generateBytes(byte[] bArr, int i2, int i3) {
            int i4 = i3;
            int i5 = i2;
            if (bArr.length - i4 >= i5) {
                long j2 = i4;
                int digestSize = this.digest.getDigestSize();
                if (j2 <= 8589934591L) {
                    long j3 = digestSize;
                    int i6 = (int) (((j2 + j3) - 1) / j3);
                    byte[] bArr2 = new byte[this.digest.getDigestSize()];
                    int i7 = 4;
                    byte[] bArr3 = new byte[4];
                    Pack.intToBigEndian(this.counterStart, bArr3, 0);
                    int i8 = this.counterStart & InputDeviceCompat.SOURCE_ANY;
                    int i9 = 0;
                    while (i9 < i6) {
                        this.digest.update(bArr3, 0, i7);
                        Digest digest = this.digest;
                        byte[] bArr4 = this.shared;
                        digest.update(bArr4, 0, bArr4.length);
                        byte[] bArr5 = this.iv;
                        if (bArr5 != null) {
                            this.digest.update(bArr5, 0, bArr5.length);
                        }
                        this.digest.doFinal(bArr2, 0);
                        if (i4 > digestSize) {
                            System.arraycopy(bArr2, 0, bArr, i5, digestSize);
                            i5 += digestSize;
                            i4 -= digestSize;
                        } else {
                            System.arraycopy(bArr2, 0, bArr, i5, i4);
                        }
                        byte b2 = (byte) (bArr3[3] + 1);
                        bArr3[3] = b2;
                        if (b2 == 0) {
                            i8 += 256;
                            Pack.intToBigEndian(i8, bArr3, 0);
                        }
                        i9++;
                        i7 = 4;
                    }
                    this.digest.reset();
                    return (int) j2;
                }
                throw new IllegalArgumentException("output length too large");
            }
            throw new OutputLengthException("output buffer too small");
        }

        @Override // org.bouncycastle.crypto.DigestDerivationFunction
        public Digest getDigest() {
            return this.digest;
        }

        @Override // org.bouncycastle.crypto.DerivationFunction
        public void init(DerivationParameters derivationParameters) {
            if (derivationParameters instanceof KDFParameters) {
                KDFParameters kDFParameters = (KDFParameters) derivationParameters;
                this.shared = kDFParameters.getSharedSecret();
                this.iv = kDFParameters.getIV();
            } else if (!(derivationParameters instanceof ISO18033KDFParameters)) {
                throw new IllegalArgumentException("KDF parameters required for generator");
            } else {
                this.shared = ((ISO18033KDFParameters) derivationParameters).getSeed();
                this.iv = null;
            }
        }
    }

    public EthereumIESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, byte[] bArr) {
        this.f13357a = basicAgreement;
        this.f13358b = derivationFunction;
        this.f13359c = mac;
        byte[] bArr2 = new byte[mac.getMacSize()];
        this.f13361e = bArr;
        this.f13360d = null;
    }

    public EthereumIESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, byte[] bArr, BufferedBlockCipher bufferedBlockCipher) {
        this.f13357a = basicAgreement;
        this.f13358b = derivationFunction;
        this.f13359c = mac;
        byte[] bArr2 = new byte[mac.getMacSize()];
        this.f13361e = bArr;
        this.f13360d = bufferedBlockCipher;
    }

    private byte[] decryptBlock(byte[] bArr, int i2, int i3) {
        byte[] bArr2;
        byte[] bArr3;
        int processBytes;
        if (i3 >= this.f13366j.length + this.f13359c.getMacSize()) {
            if (this.f13360d == null) {
                int length = (i3 - this.f13366j.length) - this.f13359c.getMacSize();
                byte[] bArr4 = new byte[length];
                int macKeySize = this.f13365i.getMacKeySize() / 8;
                bArr2 = new byte[macKeySize];
                int i4 = length + macKeySize;
                byte[] bArr5 = new byte[i4];
                this.f13358b.generateBytes(bArr5, 0, i4);
                if (this.f13366j.length != 0) {
                    System.arraycopy(bArr5, 0, bArr2, 0, macKeySize);
                    System.arraycopy(bArr5, macKeySize, bArr4, 0, length);
                } else {
                    System.arraycopy(bArr5, 0, bArr4, 0, length);
                    System.arraycopy(bArr5, length, bArr2, 0, macKeySize);
                }
                bArr3 = new byte[length];
                for (int i5 = 0; i5 != length; i5++) {
                    bArr3[i5] = (byte) (bArr[(this.f13366j.length + i2) + i5] ^ bArr4[i5]);
                }
                processBytes = 0;
            } else {
                int cipherKeySize = ((IESWithCipherParameters) this.f13365i).getCipherKeySize() / 8;
                byte[] bArr6 = new byte[cipherKeySize];
                int macKeySize2 = this.f13365i.getMacKeySize() / 8;
                bArr2 = new byte[macKeySize2];
                int i6 = cipherKeySize + macKeySize2;
                byte[] bArr7 = new byte[i6];
                this.f13358b.generateBytes(bArr7, 0, i6);
                System.arraycopy(bArr7, 0, bArr6, 0, cipherKeySize);
                System.arraycopy(bArr7, cipherKeySize, bArr2, 0, macKeySize2);
                CipherParameters keyParameter = new KeyParameter(bArr6);
                byte[] bArr8 = this.IV;
                if (bArr8 != null) {
                    keyParameter = new ParametersWithIV(keyParameter, bArr8);
                }
                this.f13360d.init(false, keyParameter);
                bArr3 = new byte[this.f13360d.getOutputSize((i3 - this.f13366j.length) - this.f13359c.getMacSize())];
                BufferedBlockCipher bufferedBlockCipher = this.f13360d;
                byte[] bArr9 = this.f13366j;
                processBytes = bufferedBlockCipher.processBytes(bArr, i2 + bArr9.length, (i3 - bArr9.length) - this.f13359c.getMacSize(), bArr3, 0);
            }
            byte[] encodingV = this.f13365i.getEncodingV();
            byte[] a2 = this.f13366j.length != 0 ? a(encodingV) : null;
            int i7 = i2 + i3;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i7 - this.f13359c.getMacSize(), i7);
            int length2 = copyOfRange.length;
            byte[] bArr10 = new byte[length2];
            SHA256Digest sHA256Digest = new SHA256Digest();
            byte[] bArr11 = new byte[sHA256Digest.getDigestSize()];
            sHA256Digest.reset();
            sHA256Digest.update(bArr2, 0, bArr2.length);
            sHA256Digest.doFinal(bArr11, 0);
            this.f13359c.init(new KeyParameter(bArr11));
            Mac mac = this.f13359c;
            byte[] bArr12 = this.IV;
            mac.update(bArr12, 0, bArr12.length);
            Mac mac2 = this.f13359c;
            byte[] bArr13 = this.f13366j;
            mac2.update(bArr, i2 + bArr13.length, (i3 - bArr13.length) - length2);
            if (encodingV != null) {
                this.f13359c.update(encodingV, 0, encodingV.length);
            }
            if (this.f13366j.length != 0) {
                this.f13359c.update(a2, 0, a2.length);
            }
            Mac mac3 = this.f13359c;
            byte[] bArr14 = this.f13361e;
            mac3.update(bArr14, 0, bArr14.length);
            this.f13359c.doFinal(bArr10, 0);
            if (Arrays.constantTimeAreEqual(copyOfRange, bArr10)) {
                BufferedBlockCipher bufferedBlockCipher2 = this.f13360d;
                return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, processBytes + bufferedBlockCipher2.doFinal(bArr3, processBytes));
            }
            throw new InvalidCipherTextException("invalid MAC");
        }
        throw new InvalidCipherTextException("length of input must be greater than the MAC and V combined");
    }

    private byte[] encryptBlock(byte[] bArr, int i2, int i3) {
        BufferedBlockCipher bufferedBlockCipher;
        CipherParameters keyParameter;
        byte[] bArr2;
        byte[] bArr3;
        if (this.f13360d == null) {
            byte[] bArr4 = new byte[i3];
            int macKeySize = this.f13365i.getMacKeySize() / 8;
            bArr3 = new byte[macKeySize];
            int i4 = i3 + macKeySize;
            byte[] bArr5 = new byte[i4];
            this.f13358b.generateBytes(bArr5, 0, i4);
            if (this.f13366j.length != 0) {
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
            int cipherKeySize = ((IESWithCipherParameters) this.f13365i).getCipherKeySize() / 8;
            byte[] bArr6 = new byte[cipherKeySize];
            int macKeySize2 = this.f13365i.getMacKeySize() / 8;
            byte[] bArr7 = new byte[macKeySize2];
            int i6 = cipherKeySize + macKeySize2;
            byte[] bArr8 = new byte[i6];
            this.f13358b.generateBytes(bArr8, 0, i6);
            System.arraycopy(bArr8, 0, bArr6, 0, cipherKeySize);
            System.arraycopy(bArr8, cipherKeySize, bArr7, 0, macKeySize2);
            if (this.IV != null) {
                bufferedBlockCipher = this.f13360d;
                keyParameter = new ParametersWithIV(new KeyParameter(bArr6), this.IV);
            } else {
                bufferedBlockCipher = this.f13360d;
                keyParameter = new KeyParameter(bArr6);
            }
            bufferedBlockCipher.init(true, keyParameter);
            bArr2 = new byte[this.f13360d.getOutputSize(i3)];
            int processBytes = this.f13360d.processBytes(bArr, i2, i3, bArr2, 0);
            i3 = processBytes + this.f13360d.doFinal(bArr2, processBytes);
            bArr3 = bArr7;
        }
        byte[] encodingV = this.f13365i.getEncodingV();
        byte[] a2 = this.f13366j.length != 0 ? a(encodingV) : null;
        int macSize = this.f13359c.getMacSize();
        byte[] bArr9 = new byte[macSize];
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] bArr10 = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.reset();
        sHA256Digest.update(bArr3, 0, bArr3.length);
        sHA256Digest.doFinal(bArr10, 0);
        this.f13359c.init(new KeyParameter(bArr10));
        Mac mac = this.f13359c;
        byte[] bArr11 = this.IV;
        mac.update(bArr11, 0, bArr11.length);
        this.f13359c.update(bArr2, 0, bArr2.length);
        if (encodingV != null) {
            this.f13359c.update(encodingV, 0, encodingV.length);
        }
        if (this.f13366j.length != 0) {
            this.f13359c.update(a2, 0, a2.length);
        }
        Mac mac2 = this.f13359c;
        byte[] bArr12 = this.f13361e;
        mac2.update(bArr12, 0, bArr12.length);
        this.f13359c.doFinal(bArr9, 0);
        byte[] bArr13 = this.f13366j;
        byte[] bArr14 = new byte[bArr13.length + i3 + macSize];
        System.arraycopy(bArr13, 0, bArr14, 0, bArr13.length);
        System.arraycopy(bArr2, 0, bArr14, this.f13366j.length, i3);
        System.arraycopy(bArr9, 0, bArr14, this.f13366j.length + i3, macSize);
        return bArr14;
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.IV = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            this.IV = null;
        }
        this.f13365i = (IESParameters) cipherParameters;
    }

    protected byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(bArr.length * 8, bArr2, 0);
        }
        return bArr2;
    }

    public BufferedBlockCipher getCipher() {
        return this.f13360d;
    }

    public Mac getMac() {
        return this.f13359c;
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.f13362f = false;
        this.f13363g = asymmetricKeyParameter;
        this.keyParser = keyParser;
        extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.f13362f = true;
        this.f13364h = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.f13362f = z;
        this.f13363g = cipherParameters;
        this.f13364h = cipherParameters2;
        this.f13366j = new byte[0];
        extractParams(cipherParameters3);
    }

    public byte[] processBlock(byte[] bArr, int i2, int i3) {
        if (this.f13362f) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.keyPairGenerator;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair generate = ephemeralKeyPairGenerator.generate();
                this.f13363g = generate.getKeyPair().getPrivate();
                this.f13366j = generate.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i2, i3);
            try {
                this.f13364h = this.keyParser.readKey(byteArrayInputStream);
                this.f13366j = Arrays.copyOfRange(bArr, i2, (i3 - byteArrayInputStream.available()) + i2);
            } catch (IOException e2) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e2.getMessage(), e2);
            } catch (IllegalArgumentException e3) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e3.getMessage(), e3);
            }
        }
        this.f13357a.init(this.f13363g);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.f13357a.getFieldSize(), this.f13357a.calculateAgreement(this.f13364h));
        byte[] bArr2 = this.f13366j;
        if (bArr2.length != 0) {
            byte[] concatenate = Arrays.concatenate(bArr2, asUnsignedByteArray);
            Arrays.fill(asUnsignedByteArray, (byte) 0);
            asUnsignedByteArray = concatenate;
        }
        try {
            this.f13358b.init(new KDFParameters(asUnsignedByteArray, this.f13365i.getDerivationV()));
            return this.f13362f ? encryptBlock(bArr, i2, i3) : decryptBlock(bArr, i2, i3);
        } finally {
            Arrays.fill(asUnsignedByteArray, (byte) 0);
        }
    }
}
