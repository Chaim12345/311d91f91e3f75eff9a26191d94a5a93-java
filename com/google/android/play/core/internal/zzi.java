package com.google.android.play.core.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.tls.NamedGroup;
/* loaded from: classes2.dex */
public final class zzi {
    public static X509Certificate[][] zza(String str) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        try {
            Pair a2 = zzj.a(randomAccessFile);
            if (a2 == null) {
                long length = randomAccessFile.length();
                StringBuilder sb = new StringBuilder(102);
                sb.append("Not an APK file: ZIP End of Central Directory record not found in file with ");
                sb.append(length);
                sb.append(" bytes");
                throw new zzf(sb.toString());
            }
            ByteBuffer byteBuffer = (ByteBuffer) a2.first;
            long longValue = ((Long) a2.second).longValue();
            long j2 = (-20) + longValue;
            if (j2 >= 0) {
                randomAccessFile.seek(j2);
                if (randomAccessFile.readInt() == 1347094023) {
                    throw new zzf("ZIP64 APK not supported");
                }
            }
            long zza = zzj.zza(byteBuffer);
            if (zza >= longValue) {
                StringBuilder sb2 = new StringBuilder(122);
                sb2.append("ZIP Central Directory offset out of range: ");
                sb2.append(zza);
                sb2.append(". ZIP End of Central Directory offset: ");
                sb2.append(longValue);
                throw new zzf(sb2.toString());
            } else if (zzj.zzb(byteBuffer) + zza == longValue) {
                if (zza < 32) {
                    StringBuilder sb3 = new StringBuilder(87);
                    sb3.append("APK too small for APK Signing Block. ZIP Central Directory offset: ");
                    sb3.append(zza);
                    throw new zzf(sb3.toString());
                }
                ByteBuffer allocate = ByteBuffer.allocate(24);
                ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                allocate.order(byteOrder);
                randomAccessFile.seek(zza - allocate.capacity());
                randomAccessFile.readFully(allocate.array(), allocate.arrayOffset(), allocate.capacity());
                if (allocate.getLong(8) == 2334950737559900225L && allocate.getLong(16) == 3617552046287187010L) {
                    int i2 = 0;
                    long j3 = allocate.getLong(0);
                    if (j3 < allocate.capacity() || j3 > 2147483639) {
                        StringBuilder sb4 = new StringBuilder(57);
                        sb4.append("APK Signing Block size out of range: ");
                        sb4.append(j3);
                        throw new zzf(sb4.toString());
                    }
                    int i3 = (int) (8 + j3);
                    long j4 = zza - i3;
                    if (j4 < 0) {
                        StringBuilder sb5 = new StringBuilder(59);
                        sb5.append("APK Signing Block offset out of range: ");
                        sb5.append(j4);
                        throw new zzf(sb5.toString());
                    }
                    ByteBuffer allocate2 = ByteBuffer.allocate(i3);
                    allocate2.order(byteOrder);
                    randomAccessFile.seek(j4);
                    randomAccessFile.readFully(allocate2.array(), allocate2.arrayOffset(), allocate2.capacity());
                    long j5 = allocate2.getLong(0);
                    if (j5 != j3) {
                        StringBuilder sb6 = new StringBuilder(103);
                        sb6.append("APK Signing Block sizes in header and footer do not match: ");
                        sb6.append(j5);
                        sb6.append(" vs ");
                        sb6.append(j3);
                        throw new zzf(sb6.toString());
                    }
                    Pair create = Pair.create(allocate2, Long.valueOf(j4));
                    ByteBuffer byteBuffer2 = (ByteBuffer) create.first;
                    long longValue2 = ((Long) create.second).longValue();
                    if (byteBuffer2.order() == byteOrder) {
                        int capacity = byteBuffer2.capacity() - 24;
                        if (capacity < 8) {
                            StringBuilder sb7 = new StringBuilder(38);
                            sb7.append("end < start: ");
                            sb7.append(capacity);
                            sb7.append(" < ");
                            sb7.append(8);
                            throw new IllegalArgumentException(sb7.toString());
                        }
                        int capacity2 = byteBuffer2.capacity();
                        if (capacity > byteBuffer2.capacity()) {
                            StringBuilder sb8 = new StringBuilder(41);
                            sb8.append("end > capacity: ");
                            sb8.append(capacity);
                            sb8.append(" > ");
                            sb8.append(capacity2);
                            throw new IllegalArgumentException(sb8.toString());
                        }
                        int limit = byteBuffer2.limit();
                        int position = byteBuffer2.position();
                        byteBuffer2.position(0);
                        byteBuffer2.limit(capacity);
                        byteBuffer2.position(8);
                        ByteBuffer slice = byteBuffer2.slice();
                        slice.order(byteBuffer2.order());
                        byteBuffer2.position(0);
                        byteBuffer2.limit(limit);
                        byteBuffer2.position(position);
                        while (slice.hasRemaining()) {
                            i2++;
                            if (slice.remaining() < 8) {
                                StringBuilder sb9 = new StringBuilder(70);
                                sb9.append("Insufficient data to read size of APK Signing Block entry #");
                                sb9.append(i2);
                                throw new zzf(sb9.toString());
                            }
                            long j6 = slice.getLong();
                            if (j6 < 4 || j6 > 2147483647L) {
                                StringBuilder sb10 = new StringBuilder(76);
                                sb10.append("APK Signing Block entry #");
                                sb10.append(i2);
                                sb10.append(" size out of range: ");
                                sb10.append(j6);
                                throw new zzf(sb10.toString());
                            }
                            int i4 = (int) j6;
                            int position2 = slice.position() + i4;
                            if (i4 > slice.remaining()) {
                                int remaining = slice.remaining();
                                StringBuilder sb11 = new StringBuilder(91);
                                sb11.append("APK Signing Block entry #");
                                sb11.append(i2);
                                sb11.append(" size out of range: ");
                                sb11.append(i4);
                                sb11.append(", available: ");
                                sb11.append(remaining);
                                throw new zzf(sb11.toString());
                            } else if (slice.getInt() == 1896449818) {
                                X509Certificate[][] zzl = zzl(randomAccessFile.getChannel(), new zze(zze(slice, i4 - 4), longValue2, zza, longValue, byteBuffer, null));
                                randomAccessFile.close();
                                return zzl;
                            } else {
                                slice.position(position2);
                            }
                        }
                        throw new zzf("No APK Signature Scheme v2 block in APK Signing Block");
                    }
                    throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
                }
                throw new zzf("No APK Signing Block before ZIP Central Directory");
            } else {
                throw new zzf("ZIP Central Directory is not immediately followed by End of Central Directory");
            }
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException unused) {
            }
        }
    }

    private static int zzb(int i2) {
        if (i2 != 1) {
            if (i2 == 2) {
                return 64;
            }
            StringBuilder sb = new StringBuilder(44);
            sb.append("Unknown content digest algorthm: ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
        return 32;
    }

    private static int zzc(int i2) {
        if (i2 != 513) {
            if (i2 != 514) {
                if (i2 != 769) {
                    switch (i2) {
                        case 257:
                        case NamedGroup.ffdhe6144 /* 259 */:
                            return 1;
                        case NamedGroup.ffdhe4096 /* 258 */:
                        case NamedGroup.ffdhe8192 /* 260 */:
                            return 2;
                        default:
                            String valueOf = String.valueOf(Long.toHexString(i2));
                            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unknown signature algorithm: 0x".concat(valueOf) : new String("Unknown signature algorithm: 0x"));
                    }
                }
                return 1;
            }
            return 2;
        }
        return 1;
    }

    private static String zzd(int i2) {
        if (i2 != 1) {
            if (i2 == 2) {
                return "SHA-512";
            }
            StringBuilder sb = new StringBuilder(44);
            sb.append("Unknown content digest algorthm: ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
        return "SHA-256";
    }

    private static ByteBuffer zze(ByteBuffer byteBuffer, int i2) {
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i3 = i2 + position;
        if (i3 < position || i3 > limit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i3);
        try {
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            byteBuffer.position(i3);
            return slice;
        } finally {
            byteBuffer.limit(limit);
        }
    }

    private static ByteBuffer zzf(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < 4) {
            int remaining = byteBuffer.remaining();
            StringBuilder sb = new StringBuilder(93);
            sb.append("Remaining buffer too short to contain length of length-prefixed field. Remaining: ");
            sb.append(remaining);
            throw new IOException(sb.toString());
        }
        int i2 = byteBuffer.getInt();
        if (i2 >= 0) {
            if (i2 <= byteBuffer.remaining()) {
                return zze(byteBuffer, i2);
            }
            int remaining2 = byteBuffer.remaining();
            StringBuilder sb2 = new StringBuilder(101);
            sb2.append("Length-prefixed field longer than remaining buffer. Field length: ");
            sb2.append(i2);
            sb2.append(", remaining: ");
            sb2.append(remaining2);
            throw new IOException(sb2.toString());
        }
        throw new IllegalArgumentException("Negative length");
    }

    private static void zzg(int i2, byte[] bArr, int i3) {
        bArr[1] = (byte) (i2 & 255);
        bArr[2] = (byte) ((i2 >>> 8) & 255);
        bArr[3] = (byte) ((i2 >>> 16) & 255);
        bArr[4] = (byte) (i2 >> 24);
    }

    private static void zzh(Map map, FileChannel fileChannel, long j2, long j3, long j4, ByteBuffer byteBuffer) {
        if (map.isEmpty()) {
            throw new SecurityException("No digests provided");
        }
        zzd zzdVar = new zzd(fileChannel, 0L, j2);
        zzd zzdVar2 = new zzd(fileChannel, j3, j4 - j3);
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
        zzj.zzd(duplicate, j2);
        zzb zzbVar = new zzb(duplicate);
        int size = map.size();
        int[] iArr = new int[size];
        int i2 = 0;
        for (Integer num : map.keySet()) {
            iArr[i2] = num.intValue();
            i2++;
        }
        try {
            byte[][] zzk = zzk(iArr, new zzc[]{zzdVar, zzdVar2, zzbVar});
            for (int i3 = 0; i3 < size; i3++) {
                int i4 = iArr[i3];
                if (!MessageDigest.isEqual((byte[]) map.get(Integer.valueOf(i4)), zzk[i3])) {
                    throw new SecurityException(zzd(i4).concat(" digest of contents did not verify"));
                }
            }
        } catch (DigestException e2) {
            throw new SecurityException("Failed to compute digest(s) of contents", e2);
        }
    }

    private static byte[] zzi(ByteBuffer byteBuffer) {
        int i2 = byteBuffer.getInt();
        if (i2 >= 0) {
            if (i2 <= byteBuffer.remaining()) {
                byte[] bArr = new byte[i2];
                byteBuffer.get(bArr);
                return bArr;
            }
            int remaining = byteBuffer.remaining();
            StringBuilder sb = new StringBuilder(90);
            sb.append("Underflow while reading length-prefixed value. Length: ");
            sb.append(i2);
            sb.append(", available: ");
            sb.append(remaining);
            throw new IOException(sb.toString());
        }
        throw new IOException("Negative length");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
        r11 = zzc(r5);
        r12 = zzc(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
        if (r11 == 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (r12 == 1) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x014b A[Catch: SignatureException -> 0x026f, InvalidAlgorithmParameterException -> 0x0271, InvalidKeyException -> 0x0273, InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException -> 0x0275, NoSuchAlgorithmException -> 0x0277, TryCatch #5 {InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException -> 0x0275, blocks: (B:68:0x0135, B:70:0x014b, B:71:0x014e), top: B:138:0x0135 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0157  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static X509Certificate[] zzj(ByteBuffer byteBuffer, Map map, CertificateFactory certificateFactory) {
        String str;
        String str2;
        Pair pair;
        String str3;
        AlgorithmParameterSpec algorithmParameterSpec;
        Signature signature;
        PSSParameterSpec pSSParameterSpec;
        String str4;
        ByteBuffer zzf = zzf(byteBuffer);
        ByteBuffer zzf2 = zzf(byteBuffer);
        byte[] zzi = zzi(byteBuffer);
        ArrayList arrayList = new ArrayList();
        byte[] bArr = null;
        int i2 = -1;
        byte[] bArr2 = null;
        int i3 = 0;
        while (zzf2.hasRemaining()) {
            i3++;
            try {
                ByteBuffer zzf3 = zzf(zzf2);
                if (zzf3.remaining() < 8) {
                    throw new SecurityException("Signature record too short");
                }
                int i4 = zzf3.getInt();
                arrayList.add(Integer.valueOf(i4));
                if (i4 != 513 && i4 != 514 && i4 != 769) {
                    switch (i4) {
                        case 257:
                        case NamedGroup.ffdhe4096 /* 258 */:
                        case NamedGroup.ffdhe6144 /* 259 */:
                        case NamedGroup.ffdhe8192 /* 260 */:
                            break;
                        default:
                            continue;
                    }
                }
                bArr2 = zzi(zzf3);
                i2 = i4;
            } catch (IOException | BufferUnderflowException e2) {
                StringBuilder sb = new StringBuilder(45);
                sb.append("Failed to parse signature record #");
                sb.append(i3);
                throw new SecurityException(sb.toString(), e2);
            }
        }
        if (i2 == -1) {
            if (i3 == 0) {
                throw new SecurityException("No signatures found");
            }
            throw new SecurityException("No supported signatures found");
        }
        if (i2 == 513 || i2 == 514) {
            str = "EC";
        } else if (i2 != 769) {
            switch (i2) {
                case 257:
                case NamedGroup.ffdhe4096 /* 258 */:
                case NamedGroup.ffdhe6144 /* 259 */:
                case NamedGroup.ffdhe8192 /* 260 */:
                    str = "RSA";
                    break;
                default:
                    String valueOf = String.valueOf(Long.toHexString(i2));
                    throw new IllegalArgumentException(valueOf.length() != 0 ? "Unknown signature algorithm: 0x".concat(valueOf) : new String("Unknown signature algorithm: 0x"));
            }
        } else {
            str = "DSA";
        }
        try {
            if (i2 == 513) {
                str2 = "SHA256withECDSA";
            } else if (i2 == 514) {
                str2 = "SHA512withECDSA";
            } else if (i2 != 769) {
                switch (i2) {
                    case 257:
                        pSSParameterSpec = new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1);
                        str4 = "SHA256withRSA/PSS";
                        pair = Pair.create(str4, pSSParameterSpec);
                        break;
                    case NamedGroup.ffdhe4096 /* 258 */:
                        pSSParameterSpec = new PSSParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1);
                        str4 = "SHA512withRSA/PSS";
                        pair = Pair.create(str4, pSSParameterSpec);
                        break;
                    case NamedGroup.ffdhe6144 /* 259 */:
                        str2 = "SHA256withRSA";
                        break;
                    case NamedGroup.ffdhe8192 /* 260 */:
                        str2 = "SHA512withRSA";
                        break;
                    default:
                        String valueOf2 = String.valueOf(Long.toHexString(i2));
                        throw new IllegalArgumentException(valueOf2.length() != 0 ? "Unknown signature algorithm: 0x".concat(valueOf2) : new String("Unknown signature algorithm: 0x"));
                }
                str3 = (String) pair.first;
                algorithmParameterSpec = (AlgorithmParameterSpec) pair.second;
                PublicKey generatePublic = KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(zzi));
                signature = Signature.getInstance(str3);
                signature.initVerify(generatePublic);
                if (algorithmParameterSpec != null) {
                    signature.setParameter(algorithmParameterSpec);
                }
                signature.update(zzf);
                if (signature.verify(bArr2)) {
                    throw new SecurityException(String.valueOf(str3).concat(" signature did not verify"));
                }
                zzf.clear();
                ByteBuffer zzf4 = zzf(zzf);
                ArrayList arrayList2 = new ArrayList();
                int i5 = 0;
                while (zzf4.hasRemaining()) {
                    i5++;
                    try {
                        ByteBuffer zzf5 = zzf(zzf4);
                        if (zzf5.remaining() < 8) {
                            throw new IOException("Record too short");
                        }
                        int i6 = zzf5.getInt();
                        arrayList2.add(Integer.valueOf(i6));
                        if (i6 == i2) {
                            bArr = zzi(zzf5);
                        }
                    } catch (IOException | BufferUnderflowException e3) {
                        StringBuilder sb2 = new StringBuilder(42);
                        sb2.append("Failed to parse digest record #");
                        sb2.append(i5);
                        throw new IOException(sb2.toString(), e3);
                    }
                }
                if (arrayList.equals(arrayList2)) {
                    int zzc = zzc(i2);
                    byte[] bArr3 = (byte[]) map.put(Integer.valueOf(zzc), bArr);
                    if (bArr3 == null || MessageDigest.isEqual(bArr3, bArr)) {
                        ByteBuffer zzf6 = zzf(zzf);
                        ArrayList arrayList3 = new ArrayList();
                        int i7 = 0;
                        while (zzf6.hasRemaining()) {
                            i7++;
                            byte[] zzi2 = zzi(zzf6);
                            try {
                                arrayList3.add(new zzg((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(zzi2)), zzi2));
                            } catch (CertificateException e4) {
                                StringBuilder sb3 = new StringBuilder(41);
                                sb3.append("Failed to decode certificate #");
                                sb3.append(i7);
                                throw new SecurityException(sb3.toString(), e4);
                            }
                        }
                        if (arrayList3.isEmpty()) {
                            throw new SecurityException("No certificates listed");
                        }
                        if (Arrays.equals(zzi, ((X509Certificate) arrayList3.get(0)).getPublicKey().getEncoded())) {
                            return (X509Certificate[]) arrayList3.toArray(new X509Certificate[arrayList3.size()]);
                        }
                        throw new SecurityException("Public key mismatch between certificate and signature record");
                    }
                    throw new SecurityException(zzd(zzc).concat(" contents digest does not match the digest specified by a preceding signer"));
                }
                throw new SecurityException("Signature algorithms don't match between digests and signatures records");
            } else {
                str2 = "SHA256withDSA";
            }
            PublicKey generatePublic2 = KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(zzi));
            signature = Signature.getInstance(str3);
            signature.initVerify(generatePublic2);
            if (algorithmParameterSpec != null) {
            }
            signature.update(zzf);
            if (signature.verify(bArr2)) {
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e5) {
            StringBuilder sb4 = new StringBuilder(String.valueOf(str3).length() + 27);
            sb4.append("Failed to verify ");
            sb4.append(str3);
            sb4.append(" signature");
            throw new SecurityException(sb4.toString(), e5);
        }
        pair = Pair.create(str2, null);
        str3 = (String) pair.first;
        algorithmParameterSpec = (AlgorithmParameterSpec) pair.second;
    }

    private static byte[][] zzk(int[] iArr, zzc[] zzcVarArr) {
        long j2;
        int i2;
        int length;
        long j3 = 0;
        int i3 = 0;
        long j4 = 0;
        int i4 = 0;
        while (true) {
            j2 = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            if (i4 >= 3) {
                break;
            }
            j4 += (zzcVarArr[i4].zza() + 1048575) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            i4++;
        }
        if (j4 >= 2097151) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Too many chunks: ");
            sb.append(j4);
            throw new DigestException(sb.toString());
        }
        int i5 = (int) j4;
        byte[][] bArr = new byte[iArr.length];
        int i6 = 0;
        while (true) {
            length = iArr.length;
            if (i6 >= length) {
                break;
            }
            byte[] bArr2 = new byte[(zzb(iArr[i6]) * i5) + 5];
            bArr2[0] = 90;
            zzg(i5, bArr2, 1);
            bArr[i6] = bArr2;
            i6++;
        }
        byte[] bArr3 = new byte[5];
        bArr3[0] = -91;
        MessageDigest[] messageDigestArr = new MessageDigest[length];
        for (int i7 = 0; i7 < iArr.length; i7++) {
            String zzd = zzd(iArr[i7]);
            try {
                messageDigestArr[i7] = MessageDigest.getInstance(zzd);
            } catch (NoSuchAlgorithmException e2) {
                throw new RuntimeException(zzd.concat(" digest not supported"), e2);
            }
        }
        int i8 = 0;
        int i9 = 0;
        for (i2 = 3; i8 < i2; i2 = 3) {
            zzc zzcVar = zzcVarArr[i8];
            long j5 = j3;
            long zza = zzcVar.zza();
            while (zza > j3) {
                int min = (int) Math.min(zza, j2);
                zzg(min, bArr3, 1);
                for (int i10 = 0; i10 < length; i10++) {
                    messageDigestArr[i10].update(bArr3);
                }
                long j6 = j5;
                try {
                    zzcVar.zzb(messageDigestArr, j6, min);
                    byte[] bArr4 = bArr3;
                    int i11 = 0;
                    while (i11 < iArr.length) {
                        int i12 = iArr[i11];
                        zzc zzcVar2 = zzcVar;
                        byte[] bArr5 = bArr[i11];
                        int zzb = zzb(i12);
                        int i13 = length;
                        MessageDigest messageDigest = messageDigestArr[i11];
                        MessageDigest[] messageDigestArr2 = messageDigestArr;
                        int digest = messageDigest.digest(bArr5, (i9 * zzb) + 5, zzb);
                        if (digest != zzb) {
                            String algorithm = messageDigest.getAlgorithm();
                            StringBuilder sb2 = new StringBuilder(String.valueOf(algorithm).length() + 46);
                            sb2.append("Unexpected output size of ");
                            sb2.append(algorithm);
                            sb2.append(" digest: ");
                            sb2.append(digest);
                            throw new RuntimeException(sb2.toString());
                        }
                        i11++;
                        zzcVar = zzcVar2;
                        length = i13;
                        messageDigestArr = messageDigestArr2;
                    }
                    MessageDigest[] messageDigestArr3 = messageDigestArr;
                    long j7 = min;
                    long j8 = j6 + j7;
                    zza -= j7;
                    i9++;
                    bArr3 = bArr4;
                    j3 = 0;
                    j5 = j8;
                    messageDigestArr = messageDigestArr3;
                    j2 = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                } catch (IOException e3) {
                    StringBuilder sb3 = new StringBuilder(59);
                    sb3.append("Failed to digest chunk #");
                    sb3.append(i9);
                    sb3.append(" of section #");
                    sb3.append(i3);
                    throw new DigestException(sb3.toString(), e3);
                }
            }
            i3++;
            i8++;
            j3 = 0;
            j2 = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        byte[][] bArr6 = new byte[iArr.length];
        for (int i14 = 0; i14 < iArr.length; i14++) {
            int i15 = iArr[i14];
            byte[] bArr7 = bArr[i14];
            String zzd2 = zzd(i15);
            try {
                bArr6[i14] = MessageDigest.getInstance(zzd2).digest(bArr7);
            } catch (NoSuchAlgorithmException e4) {
                throw new RuntimeException(zzd2.concat(" digest not supported"), e4);
            }
        }
        return bArr6;
    }

    private static X509Certificate[][] zzl(FileChannel fileChannel, zze zzeVar) {
        ByteBuffer byteBuffer;
        long j2;
        long j3;
        long j4;
        ByteBuffer byteBuffer2;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            try {
                byteBuffer = zzeVar.zza;
                ByteBuffer zzf = zzf(byteBuffer);
                int i2 = 0;
                while (zzf.hasRemaining()) {
                    i2++;
                    try {
                        arrayList.add(zzj(zzf(zzf), hashMap, certificateFactory));
                    } catch (IOException | SecurityException | BufferUnderflowException e2) {
                        StringBuilder sb = new StringBuilder(48);
                        sb.append("Failed to parse/verify signer #");
                        sb.append(i2);
                        sb.append(" block");
                        throw new SecurityException(sb.toString(), e2);
                    }
                }
                if (i2 > 0) {
                    if (hashMap.isEmpty()) {
                        throw new SecurityException("No content digests found");
                    }
                    j2 = zzeVar.zzb;
                    j3 = zzeVar.zzc;
                    j4 = zzeVar.zzd;
                    byteBuffer2 = zzeVar.zze;
                    zzh(hashMap, fileChannel, j2, j3, j4, byteBuffer2);
                    return (X509Certificate[][]) arrayList.toArray(new X509Certificate[arrayList.size()]);
                }
                throw new SecurityException("No signers found");
            } catch (IOException e3) {
                throw new SecurityException("Failed to read list of signers", e3);
            }
        } catch (CertificateException e4) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e4);
        }
    }
}
