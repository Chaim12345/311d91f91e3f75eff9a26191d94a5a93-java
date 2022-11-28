package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes2.dex */
public final class Hashing {

    /* renamed from: a  reason: collision with root package name */
    static final int f9235a = (int) System.currentTimeMillis();

    @Immutable
    /* loaded from: classes2.dex */
    enum ChecksumType implements ImmutableSupplier<Checksum> {
        CRC_32("Hashing.crc32()") { // from class: com.google.common.hash.Hashing.ChecksumType.1
            @Override // com.google.common.base.Supplier
            public Checksum get() {
                return new CRC32();
            }
        },
        ADLER_32("Hashing.adler32()") { // from class: com.google.common.hash.Hashing.ChecksumType.2
            @Override // com.google.common.base.Supplier
            public Checksum get() {
                return new Adler32();
            }
        };
        
        public final HashFunction hashFunction;

        ChecksumType(String str) {
            this.hashFunction = new ChecksumHashFunction(this, 32, str);
        }
    }

    /* loaded from: classes2.dex */
    private static final class ConcatenatedHashFunction extends AbstractCompositeHashFunction {
        private ConcatenatedHashFunction(HashFunction... hashFunctionArr) {
            super(hashFunctionArr);
            for (HashFunction hashFunction : hashFunctionArr) {
                Preconditions.checkArgument(hashFunction.bits() % 8 == 0, "the number of bits (%s) in hashFunction (%s) must be divisible by 8", hashFunction.bits(), (Object) hashFunction);
            }
        }

        @Override // com.google.common.hash.AbstractCompositeHashFunction
        HashCode a(Hasher[] hasherArr) {
            byte[] bArr = new byte[bits() / 8];
            int i2 = 0;
            for (Hasher hasher : hasherArr) {
                HashCode hash = hasher.hash();
                i2 += hash.writeBytesTo(bArr, i2, hash.bits() / 8);
            }
            return HashCode.b(bArr);
        }

        @Override // com.google.common.hash.HashFunction
        public int bits() {
            int i2 = 0;
            for (HashFunction hashFunction : this.f9219a) {
                i2 += hashFunction.bits();
            }
            return i2;
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof ConcatenatedHashFunction) {
                return Arrays.equals(this.f9219a, ((ConcatenatedHashFunction) obj).f9219a);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.f9219a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class LinearCongruentialGenerator {
        private long state;

        public LinearCongruentialGenerator(long j2) {
            this.state = j2;
        }

        public double nextDouble() {
            long j2 = (this.state * 2862933555777941757L) + 1;
            this.state = j2;
            return (((int) (j2 >>> 33)) + 1) / 2.147483648E9d;
        }
    }

    /* loaded from: classes2.dex */
    private static class Md5Holder {

        /* renamed from: a  reason: collision with root package name */
        static final HashFunction f9236a = new MessageDigestHashFunction(MessageDigestAlgorithms.MD5, "Hashing.md5()");

        private Md5Holder() {
        }
    }

    /* loaded from: classes2.dex */
    private static class Sha1Holder {

        /* renamed from: a  reason: collision with root package name */
        static final HashFunction f9237a = new MessageDigestHashFunction("SHA-1", "Hashing.sha1()");

        private Sha1Holder() {
        }
    }

    /* loaded from: classes2.dex */
    private static class Sha256Holder {

        /* renamed from: a  reason: collision with root package name */
        static final HashFunction f9238a = new MessageDigestHashFunction("SHA-256", "Hashing.sha256()");

        private Sha256Holder() {
        }
    }

    /* loaded from: classes2.dex */
    private static class Sha384Holder {

        /* renamed from: a  reason: collision with root package name */
        static final HashFunction f9239a = new MessageDigestHashFunction("SHA-384", "Hashing.sha384()");

        private Sha384Holder() {
        }
    }

    /* loaded from: classes2.dex */
    private static class Sha512Holder {

        /* renamed from: a  reason: collision with root package name */
        static final HashFunction f9240a = new MessageDigestHashFunction("SHA-512", "Hashing.sha512()");

        private Sha512Holder() {
        }
    }

    private Hashing() {
    }

    static int a(int i2) {
        Preconditions.checkArgument(i2 > 0, "Number of bits must be positive");
        return (i2 + 31) & (-32);
    }

    public static HashFunction adler32() {
        return ChecksumType.ADLER_32.hashFunction;
    }

    public static HashCode combineOrdered(Iterable<HashCode> iterable) {
        Iterator<HashCode> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        int bits = it.next().bits() / 8;
        byte[] bArr = new byte[bits];
        for (HashCode hashCode : iterable) {
            byte[] asBytes = hashCode.asBytes();
            Preconditions.checkArgument(asBytes.length == bits, "All hashcodes must have the same bit length.");
            for (int i2 = 0; i2 < asBytes.length; i2++) {
                bArr[i2] = (byte) ((bArr[i2] * 37) ^ asBytes[i2]);
            }
        }
        return HashCode.b(bArr);
    }

    public static HashCode combineUnordered(Iterable<HashCode> iterable) {
        Iterator<HashCode> it = iterable.iterator();
        Preconditions.checkArgument(it.hasNext(), "Must be at least 1 hash code to combine.");
        int bits = it.next().bits() / 8;
        byte[] bArr = new byte[bits];
        for (HashCode hashCode : iterable) {
            byte[] asBytes = hashCode.asBytes();
            Preconditions.checkArgument(asBytes.length == bits, "All hashcodes must have the same bit length.");
            for (int i2 = 0; i2 < asBytes.length; i2++) {
                bArr[i2] = (byte) (bArr[i2] + asBytes[i2]);
            }
        }
        return HashCode.b(bArr);
    }

    public static HashFunction concatenating(HashFunction hashFunction, HashFunction hashFunction2, HashFunction... hashFunctionArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashFunction);
        arrayList.add(hashFunction2);
        arrayList.addAll(Arrays.asList(hashFunctionArr));
        return new ConcatenatedHashFunction((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }

    public static HashFunction concatenating(Iterable<HashFunction> iterable) {
        Preconditions.checkNotNull(iterable);
        ArrayList arrayList = new ArrayList();
        for (HashFunction hashFunction : iterable) {
            arrayList.add(hashFunction);
        }
        Preconditions.checkArgument(arrayList.size() > 0, "number of hash functions (%s) must be > 0", arrayList.size());
        return new ConcatenatedHashFunction((HashFunction[]) arrayList.toArray(new HashFunction[0]));
    }

    public static int consistentHash(long j2, int i2) {
        int i3 = 0;
        Preconditions.checkArgument(i2 > 0, "buckets must be positive: %s", i2);
        LinearCongruentialGenerator linearCongruentialGenerator = new LinearCongruentialGenerator(j2);
        while (true) {
            int nextDouble = (int) ((i3 + 1) / linearCongruentialGenerator.nextDouble());
            if (nextDouble < 0 || nextDouble >= i2) {
                break;
            }
            i3 = nextDouble;
        }
        return i3;
    }

    public static int consistentHash(HashCode hashCode, int i2) {
        return consistentHash(hashCode.padToLong(), i2);
    }

    public static HashFunction crc32() {
        return ChecksumType.CRC_32.hashFunction;
    }

    public static HashFunction crc32c() {
        return Crc32cHashFunction.f9228a;
    }

    public static HashFunction farmHashFingerprint64() {
        return FarmHashFingerprint64.f9230a;
    }

    public static HashFunction goodFastHash(int i2) {
        int a2 = a(i2);
        if (a2 == 32) {
            return Murmur3_32HashFunction.f9244b;
        }
        if (a2 <= 128) {
            return Murmur3_128HashFunction.f9242b;
        }
        int i3 = (a2 + 127) / 128;
        HashFunction[] hashFunctionArr = new HashFunction[i3];
        hashFunctionArr[0] = Murmur3_128HashFunction.f9242b;
        int i4 = f9235a;
        for (int i5 = 1; i5 < i3; i5++) {
            i4 += 1500450271;
            hashFunctionArr[i5] = murmur3_128(i4);
        }
        return new ConcatenatedHashFunction(hashFunctionArr);
    }

    public static HashFunction hmacMd5(Key key) {
        return new MacHashFunction("HmacMD5", key, hmacToString("hmacMd5", key));
    }

    public static HashFunction hmacMd5(byte[] bArr) {
        return hmacMd5(new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacMD5"));
    }

    public static HashFunction hmacSha1(Key key) {
        return new MacHashFunction("HmacSHA1", key, hmacToString("hmacSha1", key));
    }

    public static HashFunction hmacSha1(byte[] bArr) {
        return hmacSha1(new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacSHA1"));
    }

    public static HashFunction hmacSha256(Key key) {
        return new MacHashFunction("HmacSHA256", key, hmacToString("hmacSha256", key));
    }

    public static HashFunction hmacSha256(byte[] bArr) {
        return hmacSha256(new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacSHA256"));
    }

    public static HashFunction hmacSha512(Key key) {
        return new MacHashFunction("HmacSHA512", key, hmacToString("hmacSha512", key));
    }

    public static HashFunction hmacSha512(byte[] bArr) {
        return hmacSha512(new SecretKeySpec((byte[]) Preconditions.checkNotNull(bArr), "HmacSHA512"));
    }

    private static String hmacToString(String str, Key key) {
        return String.format("Hashing.%s(Key[algorithm=%s, format=%s])", str, key.getAlgorithm(), key.getFormat());
    }

    @Deprecated
    public static HashFunction md5() {
        return Md5Holder.f9236a;
    }

    public static HashFunction murmur3_128() {
        return Murmur3_128HashFunction.f9241a;
    }

    public static HashFunction murmur3_128(int i2) {
        return new Murmur3_128HashFunction(i2);
    }

    public static HashFunction murmur3_32() {
        return Murmur3_32HashFunction.f9243a;
    }

    public static HashFunction murmur3_32(int i2) {
        return new Murmur3_32HashFunction(i2);
    }

    @Deprecated
    public static HashFunction sha1() {
        return Sha1Holder.f9237a;
    }

    public static HashFunction sha256() {
        return Sha256Holder.f9238a;
    }

    public static HashFunction sha384() {
        return Sha384Holder.f9239a;
    }

    public static HashFunction sha512() {
        return Sha512Holder.f9240a;
    }

    public static HashFunction sipHash24() {
        return SipHashFunction.f9245a;
    }

    public static HashFunction sipHash24(long j2, long j3) {
        return new SipHashFunction(2, 4, j2, j3);
    }
}
