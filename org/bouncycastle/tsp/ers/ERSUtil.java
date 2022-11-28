package org.bouncycastle.tsp.ers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
class ERSUtil {
    private static final Comparator<byte[]> hashComp = new ByteArrayComparator();

    private ERSUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List a(DigestCalculator digestCalculator, List list) {
        SortedHashList sortedHashList = new SortedHashList();
        for (int i2 = 0; i2 != list.size(); i2++) {
            sortedHashList.add(((ERSData) list.get(i2)).getHash(digestCalculator));
        }
        return sortedHashList.toList();
    }

    static List b(byte[][] bArr) {
        SortedHashList sortedHashList = new SortedHashList();
        for (int i2 = 0; i2 != bArr.length; i2++) {
            sortedHashList.add(bArr[i2]);
        }
        return sortedHashList.toList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] c(DigestCalculator digestCalculator, byte[] bArr, byte[] bArr2) {
        return hashComp.compare(bArr, bArr2) <= 0 ? h(digestCalculator, bArr, bArr2) : h(digestCalculator, bArr2, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] d(DigestCalculator digestCalculator, byte[][] bArr) {
        return bArr.length == 2 ? c(digestCalculator, bArr[0], bArr[1]) : f(digestCalculator, b(bArr).iterator());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] e(DigestCalculator digestCalculator, InputStream inputStream) {
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            Streams.pipeAll(inputStream, outputStream);
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException e2) {
            throw ExpUtil.a("unable to calculate hash: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] f(DigestCalculator digestCalculator, Iterator it) {
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            while (it.hasNext()) {
                outputStream.write((byte[]) it.next());
            }
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException e2) {
            throw ExpUtil.a("unable to calculate hash: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] g(DigestCalculator digestCalculator, byte[] bArr) {
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException e2) {
            throw ExpUtil.a("unable to calculate hash: " + e2.getMessage(), e2);
        }
    }

    static byte[] h(DigestCalculator digestCalculator, byte[] bArr, byte[] bArr2) {
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(bArr);
            outputStream.write(bArr2);
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException e2) {
            throw ExpUtil.a("unable to calculate hash: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] i(DigestCalculator digestCalculator, PartialHashtree partialHashtree) {
        byte[][] values = partialHashtree.getValues();
        return values.length > 1 ? f(digestCalculator, b(values).iterator()) : values[0];
    }
}
