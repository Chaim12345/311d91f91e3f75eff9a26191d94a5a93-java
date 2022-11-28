package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.Extension;
/* loaded from: classes3.dex */
class Utils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static BodyPartID[] a(BodyPartID[] bodyPartIDArr) {
        BodyPartID[] bodyPartIDArr2 = new BodyPartID[bodyPartIDArr.length];
        System.arraycopy(bodyPartIDArr, 0, bodyPartIDArr2, 0, bodyPartIDArr.length);
        return bodyPartIDArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Extension[] b(Extension[] extensionArr) {
        Extension[] extensionArr2 = new Extension[extensionArr.length];
        System.arraycopy(extensionArr, 0, extensionArr2, 0, extensionArr.length);
        return extensionArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BodyPartID[] c(ASN1Sequence aSN1Sequence) {
        BodyPartID[] bodyPartIDArr = new BodyPartID[aSN1Sequence.size()];
        for (int i2 = 0; i2 != aSN1Sequence.size(); i2++) {
            bodyPartIDArr[i2] = BodyPartID.getInstance(aSN1Sequence.getObjectAt(i2));
        }
        return bodyPartIDArr;
    }
}
