package org.bouncycastle.crypto.prng.drbg;

import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.util.Integers;
/* loaded from: classes3.dex */
class Utils {

    /* renamed from: a  reason: collision with root package name */
    static final Hashtable f13511a;

    static {
        Hashtable hashtable = new Hashtable();
        f13511a = hashtable;
        hashtable.put("SHA-1", Integers.valueOf(128));
        hashtable.put("SHA-224", Integers.valueOf(192));
        hashtable.put("SHA-256", Integers.valueOf(256));
        hashtable.put("SHA-384", Integers.valueOf(256));
        hashtable.put("SHA-512", Integers.valueOf(256));
        hashtable.put("SHA-512/224", Integers.valueOf(192));
        hashtable.put(SPHINCSKeyParameters.SHA512_256, Integers.valueOf(256));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Digest digest) {
        return ((Integer) f13511a.get(digest.getAlgorithmName())).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(Mac mac) {
        String algorithmName = mac.getAlgorithmName();
        return ((Integer) f13511a.get(algorithmName.substring(0, algorithmName.indexOf("/")))).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] c(Digest digest, byte[] bArr, int i2) {
        int i3 = (i2 + 7) / 8;
        byte[] bArr2 = new byte[i3];
        int digestSize = i3 / digest.getDigestSize();
        int digestSize2 = digest.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        int i4 = 1;
        int i5 = 0;
        for (int i6 = 0; i6 <= digestSize; i6++) {
            digest.update((byte) i4);
            digest.update((byte) (i2 >> 24));
            digest.update((byte) (i2 >> 16));
            digest.update((byte) (i2 >> 8));
            digest.update((byte) i2);
            digest.update(bArr, 0, bArr.length);
            digest.doFinal(bArr3, 0);
            int i7 = i6 * digestSize2;
            int i8 = i3 - i7;
            if (i8 > digestSize2) {
                i8 = digestSize2;
            }
            System.arraycopy(bArr3, 0, bArr2, i7, i8);
            i4++;
        }
        int i9 = i2 % 8;
        if (i9 != 0) {
            int i10 = 8 - i9;
            int i11 = 0;
            while (i5 != i3) {
                int i12 = bArr2[i5] & 255;
                bArr2[i5] = (byte) ((i11 << (8 - i10)) | (i12 >>> i10));
                i5++;
                i11 = i12;
            }
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(byte[] bArr, int i2) {
        return bArr != null && bArr.length > i2;
    }
}
