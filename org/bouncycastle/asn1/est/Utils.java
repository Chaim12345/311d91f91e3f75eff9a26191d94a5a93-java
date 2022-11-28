package org.bouncycastle.asn1.est;
/* loaded from: classes3.dex */
class Utils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static AttrOrOID[] a(AttrOrOID[] attrOrOIDArr) {
        AttrOrOID[] attrOrOIDArr2 = new AttrOrOID[attrOrOIDArr.length];
        System.arraycopy(attrOrOIDArr, 0, attrOrOIDArr2, 0, attrOrOIDArr.length);
        return attrOrOIDArr2;
    }
}
