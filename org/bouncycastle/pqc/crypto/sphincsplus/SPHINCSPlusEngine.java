package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.generators.MGF1BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.MGFParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class SPHINCSPlusEngine {

    /* renamed from: a  reason: collision with root package name */
    final boolean f14590a;

    /* renamed from: b  reason: collision with root package name */
    final int f14591b;

    /* renamed from: c  reason: collision with root package name */
    final int f14592c;

    /* renamed from: d  reason: collision with root package name */
    final int f14593d;

    /* renamed from: e  reason: collision with root package name */
    final int f14594e;

    /* renamed from: f  reason: collision with root package name */
    final int f14595f;

    /* renamed from: g  reason: collision with root package name */
    final int f14596g;

    /* renamed from: h  reason: collision with root package name */
    final int f14597h;

    /* renamed from: i  reason: collision with root package name */
    final int f14598i;

    /* renamed from: j  reason: collision with root package name */
    final int f14599j;

    /* renamed from: k  reason: collision with root package name */
    final int f14600k;

    /* renamed from: l  reason: collision with root package name */
    final int f14601l;

    /* renamed from: m  reason: collision with root package name */
    final int f14602m;

    /* loaded from: classes4.dex */
    static class Sha256Engine extends SPHINCSPlusEngine {
        private final byte[] digestBuf;
        private final byte[] hmacBuf;
        private final MGF1BytesGenerator mgf1;
        private final Digest msgDigest;
        private final byte[] padding;
        private final Digest treeDigest;
        private final HMac treeHMac;

        public Sha256Engine(boolean z, int i2, int i3, int i4, int i5, int i6, int i7) {
            super(z, i2, i3, i4, i5, i6, i7);
            MGF1BytesGenerator mGF1BytesGenerator;
            this.padding = new byte[64];
            SHA256Digest sHA256Digest = new SHA256Digest();
            this.treeDigest = sHA256Digest;
            if (i2 == 32) {
                this.msgDigest = new SHA512Digest();
                this.treeHMac = new HMac(new SHA512Digest());
                mGF1BytesGenerator = new MGF1BytesGenerator(new SHA512Digest());
            } else {
                this.msgDigest = new SHA256Digest();
                this.treeHMac = new HMac(new SHA256Digest());
                mGF1BytesGenerator = new MGF1BytesGenerator(new SHA256Digest());
            }
            this.mgf1 = mGF1BytesGenerator;
            this.digestBuf = new byte[sHA256Digest.getDigestSize()];
            this.hmacBuf = new byte[this.treeHMac.getMacSize()];
        }

        private byte[] compressedADRS(ADRS adrs) {
            byte[] bArr = new byte[22];
            System.arraycopy(adrs.f14571a, 3, bArr, 0, 1);
            System.arraycopy(adrs.f14571a, 8, bArr, 1, 8);
            System.arraycopy(adrs.f14571a, 19, bArr, 9, 1);
            System.arraycopy(adrs.f14571a, 20, bArr, 10, 12);
            return bArr;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] F(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] compressedADRS = compressedADRS(adrs);
            if (this.f14590a) {
                bArr2 = d(Arrays.concatenate(bArr, compressedADRS), bArr2);
            }
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(this.padding, 0, 64 - bArr.length);
            this.treeDigest.update(compressedADRS, 0, compressedADRS.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(this.digestBuf, 0);
            return Arrays.copyOfRange(this.digestBuf, 0, this.f14591b);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3) {
            byte[] concatenate = Arrays.concatenate(bArr2, bArr3);
            byte[] compressedADRS = compressedADRS(adrs);
            if (this.f14590a) {
                concatenate = d(Arrays.concatenate(bArr, compressedADRS), concatenate);
            }
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(this.padding, 0, 64 - this.f14591b);
            this.treeDigest.update(compressedADRS, 0, compressedADRS.length);
            this.treeDigest.update(concatenate, 0, concatenate.length);
            this.treeDigest.doFinal(this.digestBuf, 0);
            return Arrays.copyOfRange(this.digestBuf, 0, this.f14591b);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.treeHMac.init(new KeyParameter(bArr));
            this.treeHMac.update(bArr2, 0, bArr2.length);
            this.treeHMac.update(bArr3, 0, bArr3.length);
            this.treeHMac.doFinal(this.hmacBuf, 0);
            return Arrays.copyOfRange(this.hmacBuf, 0, this.f14591b);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] compressedADRS = compressedADRS(adrs);
            if (this.f14590a) {
                bArr2 = d(Arrays.concatenate(bArr, compressedADRS), bArr2);
            }
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(this.padding, 0, 64 - this.f14591b);
            this.treeDigest.update(compressedADRS, 0, compressedADRS.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(this.digestBuf, 0);
            return Arrays.copyOfRange(this.digestBuf, 0, this.f14591b);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        IndexedDigest a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            int i2 = ((this.f14598i * this.f14599j) + 7) / 8;
            int i3 = this.f14600k;
            int i4 = i3 / this.f14597h;
            int i5 = i3 - i4;
            int i6 = (i4 + 7) / 8;
            int i7 = (i5 + 7) / 8;
            byte[] bArr5 = new byte[this.msgDigest.getDigestSize()];
            this.msgDigest.update(bArr, 0, bArr.length);
            this.msgDigest.update(bArr2, 0, bArr2.length);
            this.msgDigest.update(bArr3, 0, bArr3.length);
            this.msgDigest.update(bArr4, 0, bArr4.length);
            this.msgDigest.doFinal(bArr5, 0);
            byte[] c2 = c(Arrays.concatenate(bArr, bArr2, bArr5), new byte[i2 + i6 + i7]);
            byte[] bArr6 = new byte[8];
            System.arraycopy(c2, i2, bArr6, 8 - i7, i7);
            long bigEndianToLong = Pack.bigEndianToLong(bArr6, 0) & ((-1) >>> (64 - i5));
            byte[] bArr7 = new byte[4];
            System.arraycopy(c2, i7 + i2, bArr7, 4 - i6, i6);
            return new IndexedDigest(bigEndianToLong, Pack.bigEndianToInt(bArr7, 0) & ((-1) >>> (32 - i4)), Arrays.copyOfRange(c2, 0, i2));
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] b(byte[] bArr, ADRS adrs) {
            int length = bArr.length;
            this.treeDigest.update(bArr, 0, bArr.length);
            byte[] compressedADRS = compressedADRS(adrs);
            this.treeDigest.update(compressedADRS, 0, compressedADRS.length);
            this.treeDigest.doFinal(this.digestBuf, 0);
            return Arrays.copyOfRange(this.digestBuf, 0, length);
        }

        protected byte[] c(byte[] bArr, byte[] bArr2) {
            int length = bArr2.length;
            byte[] bArr3 = new byte[length];
            this.mgf1.init(new MGFParameters(bArr));
            this.mgf1.generateBytes(bArr3, 0, length);
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2]);
            }
            return bArr3;
        }

        protected byte[] d(byte[] bArr, byte[] bArr2) {
            int length = bArr2.length;
            byte[] bArr3 = new byte[length];
            MGF1BytesGenerator mGF1BytesGenerator = new MGF1BytesGenerator(new SHA256Digest());
            mGF1BytesGenerator.init(new MGFParameters(bArr));
            mGF1BytesGenerator.generateBytes(bArr3, 0, length);
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2]);
            }
            return bArr3;
        }
    }

    /* loaded from: classes4.dex */
    static class Shake256Engine extends SPHINCSPlusEngine {
        private final Xof treeDigest;

        public Shake256Engine(boolean z, int i2, int i3, int i4, int i5, int i6, int i7) {
            super(z, i2, i3, i4, i5, i6, i7);
            this.treeDigest = new SHAKEDigest(256);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] F(byte[] bArr, ADRS adrs, byte[] bArr2) {
            if (this.f14590a) {
                bArr2 = c(bArr, adrs, bArr2);
            }
            int i2 = this.f14591b;
            byte[] bArr3 = new byte[i2];
            this.treeDigest.update(bArr, 0, bArr.length);
            Xof xof = this.treeDigest;
            byte[] bArr4 = adrs.f14571a;
            xof.update(bArr4, 0, bArr4.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(bArr3, 0, i2);
            return bArr3;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3) {
            byte[] concatenate = Arrays.concatenate(bArr2, bArr3);
            if (this.f14590a) {
                concatenate = c(bArr, adrs, concatenate);
            }
            int i2 = this.f14591b;
            byte[] bArr4 = new byte[i2];
            this.treeDigest.update(bArr, 0, bArr.length);
            Xof xof = this.treeDigest;
            byte[] bArr5 = adrs.f14571a;
            xof.update(bArr5, 0, bArr5.length);
            this.treeDigest.update(concatenate, 0, concatenate.length);
            this.treeDigest.doFinal(bArr4, 0, i2);
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.update(bArr3, 0, bArr3.length);
            int i2 = this.f14591b;
            byte[] bArr4 = new byte[i2];
            this.treeDigest.doFinal(bArr4, 0, i2);
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2) {
            if (this.f14590a) {
                bArr2 = c(bArr, adrs, bArr2);
            }
            int i2 = this.f14591b;
            byte[] bArr3 = new byte[i2];
            this.treeDigest.update(bArr, 0, bArr.length);
            Xof xof = this.treeDigest;
            byte[] bArr4 = adrs.f14571a;
            xof.update(bArr4, 0, bArr4.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(bArr3, 0, i2);
            return bArr3;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        IndexedDigest a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            int i2 = ((this.f14598i * this.f14599j) + 7) / 8;
            int i3 = this.f14600k;
            int i4 = i3 / this.f14597h;
            int i5 = i3 - i4;
            int i6 = (i4 + 7) / 8;
            int i7 = (i5 + 7) / 8;
            int i8 = i2 + i6 + i7;
            byte[] bArr5 = new byte[i8];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.update(bArr3, 0, bArr3.length);
            this.treeDigest.update(bArr4, 0, bArr4.length);
            this.treeDigest.doFinal(bArr5, 0, i8);
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr5, i2, bArr6, 8 - i7, i7);
            long bigEndianToLong = Pack.bigEndianToLong(bArr6, 0) & ((-1) >>> (64 - i5));
            byte[] bArr7 = new byte[4];
            System.arraycopy(bArr5, i7 + i2, bArr7, 4 - i6, i6);
            return new IndexedDigest(bigEndianToLong, Pack.bigEndianToInt(bArr7, 0) & ((-1) >>> (32 - i4)), Arrays.copyOfRange(bArr5, 0, i2));
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] b(byte[] bArr, ADRS adrs) {
            this.treeDigest.update(bArr, 0, bArr.length);
            Xof xof = this.treeDigest;
            byte[] bArr2 = adrs.f14571a;
            xof.update(bArr2, 0, bArr2.length);
            int i2 = this.f14591b;
            byte[] bArr3 = new byte[i2];
            this.treeDigest.doFinal(bArr3, 0, i2);
            return bArr3;
        }

        protected byte[] c(byte[] bArr, ADRS adrs, byte[] bArr2) {
            int length = bArr2.length;
            byte[] bArr3 = new byte[length];
            this.treeDigest.update(bArr, 0, bArr.length);
            Xof xof = this.treeDigest;
            byte[] bArr4 = adrs.f14571a;
            xof.update(bArr4, 0, bArr4.length);
            this.treeDigest.doFinal(bArr3, 0, length);
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2]);
            }
            return bArr3;
        }
    }

    public SPHINCSPlusEngine(boolean z, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f14591b = i2;
        if (i3 == 16) {
            this.f14593d = 4;
            this.f14595f = (i2 * 8) / 4;
            if (i2 > 8) {
                if (i2 <= 136) {
                    this.f14596g = 3;
                } else if (i2 > 256) {
                    throw new IllegalArgumentException("cannot precompute SPX_WOTS_LEN2 for n outside {2, .., 256}");
                } else {
                    this.f14596g = 4;
                }
                this.f14592c = i3;
                this.f14594e = this.f14595f + this.f14596g;
                this.f14590a = z;
                this.f14597h = i4;
                this.f14598i = i5;
                this.f14599j = i6;
                this.f14600k = i7;
                this.f14601l = i7 / i4;
                this.f14602m = 1 << i5;
            }
            this.f14596g = 2;
            this.f14592c = i3;
            this.f14594e = this.f14595f + this.f14596g;
            this.f14590a = z;
            this.f14597h = i4;
            this.f14598i = i5;
            this.f14599j = i6;
            this.f14600k = i7;
            this.f14601l = i7 / i4;
            this.f14602m = 1 << i5;
        } else if (i3 != 256) {
            throw new IllegalArgumentException("wots_w assumed 16 or 256");
        } else {
            this.f14593d = 8;
            this.f14595f = (i2 * 8) / 8;
            if (i2 <= 1) {
                this.f14596g = 1;
                this.f14592c = i3;
                this.f14594e = this.f14595f + this.f14596g;
                this.f14590a = z;
                this.f14597h = i4;
                this.f14598i = i5;
                this.f14599j = i6;
                this.f14600k = i7;
                this.f14601l = i7 / i4;
                this.f14602m = 1 << i5;
            }
            if (i2 > 256) {
                throw new IllegalArgumentException("cannot precompute SPX_WOTS_LEN2 for n outside {2, .., 256}");
            }
            this.f14596g = 2;
            this.f14592c = i3;
            this.f14594e = this.f14595f + this.f14596g;
            this.f14590a = z;
            this.f14597h = i4;
            this.f14598i = i5;
            this.f14599j = i6;
            this.f14600k = i7;
            this.f14601l = i7 / i4;
            this.f14602m = 1 << i5;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] F(byte[] bArr, ADRS adrs, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract IndexedDigest a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] b(byte[] bArr, ADRS adrs);
}
