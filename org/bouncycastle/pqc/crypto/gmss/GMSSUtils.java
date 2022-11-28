package org.bouncycastle.pqc.crypto.gmss;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
class GMSSUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector[] a(Vector[] vectorArr) {
        if (vectorArr == null) {
            return null;
        }
        Vector[] vectorArr2 = new Vector[vectorArr.length];
        for (int i2 = 0; i2 != vectorArr.length; i2++) {
            vectorArr2[i2] = new Vector();
            Enumeration elements = vectorArr[i2].elements();
            while (elements.hasMoreElements()) {
                vectorArr2[i2].addElement(elements.nextElement());
            }
        }
        return vectorArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Treehash[] b(Treehash[] treehashArr) {
        if (treehashArr == null) {
            return null;
        }
        Treehash[] treehashArr2 = new Treehash[treehashArr.length];
        System.arraycopy(treehashArr, 0, treehashArr2, 0, treehashArr.length);
        return treehashArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[][] c(byte[][] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[][] bArr2 = new byte[bArr.length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr2[i2] = Arrays.clone(bArr[i2]);
        }
        return bArr2;
    }
}
