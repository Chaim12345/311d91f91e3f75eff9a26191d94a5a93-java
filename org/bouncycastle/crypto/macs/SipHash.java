package org.bouncycastle.crypto.macs;

import org.apache.commons.cli.HelpFormatter;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class SipHash implements Mac {

    /* renamed from: a  reason: collision with root package name */
    protected final int f13436a;

    /* renamed from: b  reason: collision with root package name */
    protected final int f13437b;

    /* renamed from: c  reason: collision with root package name */
    protected long f13438c;

    /* renamed from: d  reason: collision with root package name */
    protected long f13439d;

    /* renamed from: e  reason: collision with root package name */
    protected long f13440e;

    /* renamed from: f  reason: collision with root package name */
    protected long f13441f;

    /* renamed from: g  reason: collision with root package name */
    protected long f13442g;

    /* renamed from: h  reason: collision with root package name */
    protected long f13443h;

    /* renamed from: i  reason: collision with root package name */
    protected long f13444i;

    /* renamed from: j  reason: collision with root package name */
    protected int f13445j;

    /* renamed from: k  reason: collision with root package name */
    protected int f13446k;

    public SipHash() {
        this.f13444i = 0L;
        this.f13445j = 0;
        this.f13446k = 0;
        this.f13436a = 2;
        this.f13437b = 4;
    }

    public SipHash(int i2, int i3) {
        this.f13444i = 0L;
        this.f13445j = 0;
        this.f13446k = 0;
        this.f13436a = i2;
        this.f13437b = i3;
    }

    protected static long c(long j2, int i2) {
        return (j2 >>> (-i2)) | (j2 << i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i2) {
        long j2 = this.f13440e;
        long j3 = this.f13441f;
        long j4 = this.f13442g;
        long j5 = this.f13443h;
        for (int i3 = 0; i3 < i2; i3++) {
            long j6 = j2 + j3;
            long j7 = j4 + j5;
            long c2 = c(j3, 13) ^ j6;
            long c3 = c(j5, 16) ^ j7;
            long j8 = j7 + c2;
            j2 = c(j6, 32) + c3;
            j3 = c(c2, 17) ^ j8;
            j5 = c(c3, 21) ^ j2;
            j4 = c(j8, 32);
        }
        this.f13440e = j2;
        this.f13441f = j3;
        this.f13442g = j4;
        this.f13443h = j5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        this.f13446k++;
        this.f13443h ^= this.f13444i;
        a(this.f13436a);
        this.f13440e ^= this.f13444i;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        Pack.longToLittleEndian(doFinal(), bArr, i2);
        return 8;
    }

    public long doFinal() {
        int i2;
        long j2 = this.f13444i >>> ((7 - this.f13445j) << 3);
        this.f13444i = j2;
        long j3 = j2 >>> 8;
        this.f13444i = j3;
        this.f13444i = j3 | ((((this.f13446k << 3) + i2) & 255) << 56);
        b();
        this.f13442g ^= 255;
        a(this.f13437b);
        long j4 = ((this.f13440e ^ this.f13441f) ^ this.f13442g) ^ this.f13443h;
        reset();
        return j4;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash-" + this.f13436a + HelpFormatter.DEFAULT_OPT_PREFIX + this.f13437b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        this.f13438c = Pack.littleEndianToLong(key, 0);
        this.f13439d = Pack.littleEndianToLong(key, 8);
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        long j2 = this.f13438c;
        this.f13440e = 8317987319222330741L ^ j2;
        long j3 = this.f13439d;
        this.f13441f = 7237128888997146477L ^ j3;
        this.f13442g = j2 ^ 7816392313619706465L;
        this.f13443h = 8387220255154660723L ^ j3;
        this.f13444i = 0L;
        this.f13445j = 0;
        this.f13446k = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b2) {
        long j2 = this.f13444i >>> 8;
        this.f13444i = j2;
        this.f13444i = j2 | ((b2 & 255) << 56);
        int i2 = this.f13445j + 1;
        this.f13445j = i2;
        if (i2 == 8) {
            b();
            this.f13445j = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i2, int i3) {
        int i4 = i3 & (-8);
        int i5 = this.f13445j;
        int i6 = 0;
        if (i5 == 0) {
            while (i6 < i4) {
                this.f13444i = Pack.littleEndianToLong(bArr, i2 + i6);
                b();
                i6 += 8;
            }
            while (i6 < i3) {
                long j2 = this.f13444i >>> 8;
                this.f13444i = j2;
                this.f13444i = j2 | ((bArr[i2 + i6] & 255) << 56);
                i6++;
            }
            this.f13445j = i3 - i4;
            return;
        }
        int i7 = i5 << 3;
        int i8 = 0;
        while (i8 < i4) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr, i2 + i8);
            this.f13444i = (this.f13444i >>> (-i7)) | (littleEndianToLong << i7);
            b();
            this.f13444i = littleEndianToLong;
            i8 += 8;
        }
        while (i8 < i3) {
            long j3 = this.f13444i >>> 8;
            this.f13444i = j3;
            this.f13444i = j3 | ((bArr[i2 + i8] & 255) << 56);
            int i9 = this.f13445j + 1;
            this.f13445j = i9;
            if (i9 == 8) {
                b();
                this.f13445j = 0;
            }
            i8++;
        }
    }
}
