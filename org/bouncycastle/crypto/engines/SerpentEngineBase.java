package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes3.dex */
public abstract class SerpentEngineBase implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f13392a;

    /* renamed from: b  reason: collision with root package name */
    protected int[] f13393b;

    /* renamed from: c  reason: collision with root package name */
    protected int f13394c;

    /* renamed from: d  reason: collision with root package name */
    protected int f13395d;

    /* renamed from: e  reason: collision with root package name */
    protected int f13396e;

    /* renamed from: f  reason: collision with root package name */
    protected int f13397f;

    /* JADX INFO: Access modifiers changed from: protected */
    public static int n(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    protected static int o(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a() {
        int n2 = n(this.f13394c, 13);
        int n3 = n(this.f13396e, 3);
        this.f13395d = n((this.f13395d ^ n2) ^ n3, 1);
        int n4 = n((this.f13397f ^ n3) ^ (n2 << 3), 7);
        this.f13397f = n4;
        this.f13394c = n((n2 ^ this.f13395d) ^ n4, 5);
        this.f13396e = n((this.f13397f ^ n3) ^ (this.f13395d << 7), 22);
    }

    protected abstract void b(byte[] bArr, int i2, byte[] bArr2, int i3);

    protected abstract void c(byte[] bArr, int i2, byte[] bArr2, int i3);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void d(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i3 ^ i2;
        int i8 = (i6 | i7) ^ i5;
        int i9 = i4 ^ i8;
        int i10 = i7 ^ i9;
        this.f13396e = i10;
        int i11 = (i7 & i5) ^ i6;
        int i12 = (i10 & i11) ^ i8;
        this.f13395d = i12;
        int i13 = (i2 & i8) ^ (i12 | i9);
        this.f13397f = i13;
        this.f13394c = i13 ^ (i11 ^ i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(int i2, int i3, int i4, int i5) {
        int i6 = i5 ^ i3;
        int i7 = i2 ^ (i3 & i6);
        int i8 = i6 ^ i7;
        int i9 = i4 ^ i8;
        this.f13397f = i9;
        int i10 = i3 ^ (i6 & i7);
        int i11 = i7 ^ (i9 | i10);
        this.f13395d = i11;
        int i12 = ~i11;
        int i13 = i10 ^ i9;
        this.f13394c = i12 ^ i13;
        this.f13396e = (i12 | i13) ^ i8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(int i2, int i3, int i4, int i5) {
        int i6 = i3 ^ i5;
        int i7 = ~i6;
        int i8 = i2 ^ i4;
        int i9 = i4 ^ i6;
        int i10 = (i3 & i9) ^ i8;
        this.f13394c = i10;
        int i11 = (((i2 | i7) ^ i5) | i8) ^ i6;
        this.f13397f = i11;
        int i12 = ~i9;
        int i13 = i11 | i10;
        this.f13395d = i12 ^ i13;
        this.f13396e = (i13 ^ i8) ^ (i5 & i12);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void g(int i2, int i3, int i4, int i5) {
        int i6 = i2 | i3;
        int i7 = i3 ^ i4;
        int i8 = i2 ^ (i3 & i7);
        int i9 = i4 ^ i8;
        int i10 = i5 | i8;
        int i11 = i7 ^ i10;
        this.f13394c = i11;
        int i12 = (i10 | i7) ^ i5;
        this.f13396e = i9 ^ i12;
        int i13 = i6 ^ i12;
        int i14 = i8 ^ (i11 & i13);
        this.f13397f = i14;
        this.f13395d = i14 ^ (i13 ^ i11);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Serpent";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void h(int i2, int i3, int i4, int i5) {
        int i6 = i3 ^ ((i4 | i5) & i2);
        int i7 = i4 ^ (i2 & i6);
        int i8 = i5 ^ i7;
        this.f13395d = i8;
        int i9 = ~i2;
        int i10 = (i7 & i8) ^ i6;
        this.f13397f = i10;
        int i11 = i5 ^ (i8 | i9);
        this.f13394c = i10 ^ i11;
        this.f13396e = (i9 ^ i8) ^ (i6 & i11);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void i(int i2, int i3, int i4, int i5) {
        int i6 = ~i4;
        int i7 = (i3 & i6) ^ i5;
        int i8 = i2 & i7;
        int i9 = (i3 ^ i6) ^ i8;
        this.f13397f = i9;
        int i10 = i9 | i3;
        this.f13395d = i7 ^ (i2 & i10);
        int i11 = i5 | i2;
        this.f13394c = (i6 ^ i10) ^ i11;
        this.f13396e = ((i2 ^ i4) | i8) ^ (i3 & i11);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.f13392a = z;
            this.f13393b = m(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to " + getAlgorithmName() + " init - " + cipherParameters.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void j(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i2 ^ i3;
        int i8 = i4 ^ i7;
        int i9 = (i4 | i6) ^ i5;
        this.f13395d = i8 ^ i9;
        int i10 = i7 ^ (i8 & i9);
        int i11 = i9 ^ (i3 | i10);
        this.f13397f = i11;
        int i12 = i3 | i11;
        this.f13394c = i10 ^ i12;
        this.f13396e = (i5 & i6) ^ (i12 ^ i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void k(int i2, int i3, int i4, int i5) {
        int i6 = (i2 & i3) | i4;
        int i7 = (i2 | i3) & i5;
        int i8 = i6 ^ i7;
        this.f13397f = i8;
        int i9 = i3 ^ i7;
        int i10 = ((i8 ^ (~i5)) | i9) ^ i2;
        this.f13395d = i10;
        int i11 = (i9 ^ i4) ^ (i5 | i10);
        this.f13394c = i11;
        this.f13396e = ((i2 & i8) ^ i11) ^ (i6 ^ i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void l() {
        int o2 = (o(this.f13396e, 22) ^ this.f13397f) ^ (this.f13395d << 7);
        int o3 = o(this.f13394c, 5) ^ this.f13395d;
        int i2 = this.f13397f;
        int i3 = o3 ^ i2;
        int o4 = o(i2, 7);
        int o5 = o(this.f13395d, 1);
        this.f13397f = (o4 ^ o2) ^ (i3 << 3);
        this.f13395d = (o5 ^ i3) ^ o2;
        this.f13396e = o(o2, 3);
        this.f13394c = o(i3, 13);
    }

    protected abstract int[] m(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void p(int i2, int i3, int i4, int i5) {
        int i6 = i2 ^ i5;
        int i7 = i4 ^ i6;
        int i8 = i3 ^ i7;
        int i9 = (i5 & i2) ^ i8;
        this.f13397f = i9;
        int i10 = i2 ^ (i3 & i6);
        this.f13396e = (i4 | i10) ^ i8;
        int i11 = (i7 ^ i10) & i9;
        this.f13395d = (~i7) ^ i11;
        this.f13394c = (~i10) ^ i11;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.f13393b == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i2 + 16 <= bArr.length) {
            if (i3 + 16 <= bArr2.length) {
                if (this.f13392a) {
                    c(bArr, i2, bArr2, i3);
                    return 16;
                }
                b(bArr, i2, bArr2, i3);
                return 16;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void q(int i2, int i3, int i4, int i5) {
        int i6 = (~i2) ^ i3;
        int i7 = (i2 | i6) ^ i4;
        int i8 = i5 ^ i7;
        this.f13396e = i8;
        int i9 = i3 ^ (i5 | i6);
        int i10 = i8 ^ i6;
        int i11 = (i7 & i9) ^ i10;
        this.f13397f = i11;
        int i12 = i9 ^ i7;
        this.f13395d = i11 ^ i12;
        this.f13394c = i7 ^ (i12 & i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void r(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i3 ^ i5;
        int i8 = (i4 & i6) ^ i7;
        this.f13394c = i8;
        int i9 = i4 ^ i6;
        int i10 = i3 & (i4 ^ i8);
        int i11 = i9 ^ i10;
        this.f13397f = i11;
        int i12 = i2 ^ ((i10 | i5) & (i8 | i9));
        this.f13396e = i12;
        this.f13395d = (i12 ^ (i5 | i6)) ^ (i7 ^ i11);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void s(int i2, int i3, int i4, int i5) {
        int i6 = i2 ^ i3;
        int i7 = i2 & i4;
        int i8 = i2 | i5;
        int i9 = i4 ^ i5;
        int i10 = i7 | (i6 & i8);
        int i11 = i9 ^ i10;
        this.f13396e = i11;
        int i12 = (i8 ^ i3) ^ i10;
        int i13 = i6 ^ (i9 & i12);
        this.f13394c = i13;
        int i14 = i13 & i11;
        this.f13395d = i12 ^ i14;
        this.f13397f = (i3 | i5) ^ (i9 ^ i14);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void t(int i2, int i3, int i4, int i5) {
        int i6 = i2 ^ i5;
        int i7 = i4 ^ (i5 & i6);
        int i8 = i3 | i7;
        this.f13397f = i6 ^ i8;
        int i9 = ~i3;
        int i10 = (i6 | i9) ^ i7;
        this.f13394c = i10;
        int i11 = i9 ^ i6;
        int i12 = (i8 & i11) ^ (i10 & i2);
        this.f13396e = i12;
        this.f13395d = (i2 ^ i7) ^ (i11 & i12);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void u(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i2 ^ i3;
        int i8 = i2 ^ i5;
        int i9 = (i4 ^ i6) ^ (i7 | i8);
        this.f13394c = i9;
        int i10 = i5 & i9;
        int i11 = (i7 ^ i9) ^ i10;
        this.f13395d = i11;
        int i12 = i8 ^ (i9 | i6);
        this.f13396e = (i7 | i10) ^ i12;
        this.f13397f = (i12 & i11) ^ (i3 ^ i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void v(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i2 ^ i5;
        int i8 = i3 ^ i7;
        int i9 = i4 ^ (i6 | i7);
        int i10 = i3 ^ i9;
        this.f13395d = i10;
        int i11 = (i7 | i10) ^ i5;
        int i12 = (i9 & i11) ^ i8;
        this.f13396e = i12;
        int i13 = i11 ^ i9;
        this.f13394c = i12 ^ i13;
        this.f13397f = (i13 & i8) ^ (~i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void w(int i2, int i3, int i4, int i5) {
        int i6 = i3 ^ i4;
        int i7 = (i4 & i6) ^ i5;
        int i8 = i2 ^ i7;
        int i9 = i3 ^ ((i5 | i6) & i8);
        this.f13395d = i9;
        int i10 = (i2 & i8) ^ i6;
        this.f13397f = i10;
        int i11 = (i9 | i7) ^ i8;
        int i12 = i7 ^ (i10 & i11);
        this.f13396e = i12;
        this.f13394c = (i10 & i12) ^ (~i11);
    }
}
