package org.bouncycastle.oer.its;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class SequenceOfPsidSsp extends ASN1Object {
    private final List<PsidSsp> items;

    /* loaded from: classes4.dex */
    public static class Builder {
        private List<PsidSsp> items = new ArrayList();

        public SequenceOfPsidSsp createSequenceOfPsidSsp() {
            return new SequenceOfPsidSsp(this.items);
        }

        public Builder setItem(PsidSsp... psidSspArr) {
            for (int i2 = 0; i2 != psidSspArr.length; i2++) {
                this.items.add(psidSspArr[i2]);
            }
            return this;
        }

        public Builder setItems(List<PsidSsp> list) {
            this.items = list;
            return this;
        }
    }

    public SequenceOfPsidSsp(List<PsidSsp> list) {
        this.items = Collections.unmodifiableList(list);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static SequenceOfPsidSsp getInstance(Object obj) {
        if (obj instanceof SequenceOfPsidSsp) {
            return (SequenceOfPsidSsp) obj;
        }
        Enumeration objects = ASN1Sequence.getInstance(obj).getObjects();
        ArrayList arrayList = new ArrayList();
        while (objects.hasMoreElements()) {
            arrayList.add(PsidSsp.getInstance(objects.nextElement()));
        }
        return new Builder().setItems(arrayList).createSequenceOfPsidSsp();
    }

    public List<PsidSsp> getItems() {
        return this.items;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (PsidSsp psidSsp : this.items) {
            aSN1EncodableVector.add(psidSsp);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
