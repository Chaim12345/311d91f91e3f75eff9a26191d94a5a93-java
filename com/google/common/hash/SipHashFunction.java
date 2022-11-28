package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Immutable
/* loaded from: classes2.dex */
final class SipHashFunction extends AbstractHashFunction implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    static final HashFunction f9245a = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c  reason: collision with root package name */
    private final int f9246c;

    /* renamed from: d  reason: collision with root package name */
    private final int f9247d;
    private final long k0;
    private final long k1;

    /* loaded from: classes2.dex */
    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b  reason: collision with root package name */
        private long f9248b;

        /* renamed from: c  reason: collision with root package name */
        private final int f9249c;

        /* renamed from: d  reason: collision with root package name */
        private final int f9250d;
        private long finalM;
        private long v0;
        private long v1;
        private long v2;
        private long v3;

        SipHasher(int i2, int i3, long j2, long j3) {
            super(8);
            this.v0 = 8317987319222330741L;
            this.v1 = 7237128888997146477L;
            this.v2 = 7816392313619706465L;
            this.v3 = 8387220255154660723L;
            this.f9248b = 0L;
            this.finalM = 0L;
            this.f9249c = i2;
            this.f9250d = i3;
            this.v0 = 8317987319222330741L ^ j2;
            this.v1 = 7237128888997146477L ^ j3;
            this.v2 = 7816392313619706465L ^ j2;
            this.v3 = 8387220255154660723L ^ j3;
        }

        private void processM(long j2) {
            this.v3 ^= j2;
            sipRound(this.f9249c);
            this.v0 = j2 ^ this.v0;
        }

        private void sipRound(int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = this.v0;
                long j3 = this.v1;
                this.v0 = j2 + j3;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(j3, 13);
                long rotateLeft = Long.rotateLeft(this.v3, 16);
                this.v3 = rotateLeft;
                long j4 = this.v1;
                long j5 = this.v0;
                this.v1 = j4 ^ j5;
                this.v3 = rotateLeft ^ this.v2;
                long rotateLeft2 = Long.rotateLeft(j5, 32);
                this.v0 = rotateLeft2;
                long j6 = this.v2;
                long j7 = this.v1;
                this.v2 = j6 + j7;
                this.v0 = rotateLeft2 + this.v3;
                this.v1 = Long.rotateLeft(j7, 17);
                long rotateLeft3 = Long.rotateLeft(this.v3, 21);
                this.v3 = rotateLeft3;
                long j8 = this.v1;
                long j9 = this.v2;
                this.v1 = j8 ^ j9;
                this.v3 = rotateLeft3 ^ this.v0;
                this.v2 = Long.rotateLeft(j9, 32);
            }
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected HashCode a() {
            long j2 = this.finalM ^ (this.f9248b << 56);
            this.finalM = j2;
            processM(j2);
            this.v2 ^= 255;
            sipRound(this.f9250d);
            return HashCode.fromLong(((this.v0 ^ this.v1) ^ this.v2) ^ this.v3);
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void b(ByteBuffer byteBuffer) {
            this.f9248b += 8;
            processM(byteBuffer.getLong());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void c(ByteBuffer byteBuffer) {
            this.f9248b += byteBuffer.remaining();
            int i2 = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (byteBuffer.get() & 255) << i2;
                i2 += 8;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SipHashFunction(int i2, int i3, long j2, long j3) {
        Preconditions.checkArgument(i2 > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i2);
        Preconditions.checkArgument(i3 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i3);
        this.f9246c = i2;
        this.f9247d = i3;
        this.k0 = j2;
        this.k1 = j3;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof SipHashFunction) {
            SipHashFunction sipHashFunction = (SipHashFunction) obj;
            return this.f9246c == sipHashFunction.f9246c && this.f9247d == sipHashFunction.f9247d && this.k0 == sipHashFunction.k0 && this.k1 == sipHashFunction.k1;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((SipHashFunction.class.hashCode() ^ this.f9246c) ^ this.f9247d) ^ this.k0) ^ this.k1);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.f9246c, this.f9247d, this.k0, this.k1);
    }

    public String toString() {
        return "Hashing.sipHash" + this.f9246c + "" + this.f9247d + "(" + this.k0 + ", " + this.k1 + ")";
    }
}
