package org.bouncycastle.oer.its;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
/* loaded from: classes4.dex */
public class SequenceOfCertificate extends ASN1Object {
    private final List<Certificate> certificates;

    /* loaded from: classes4.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        List f14392a = new ArrayList();

        public Builder add(Certificate... certificateArr) {
            this.f14392a.addAll(Arrays.asList(certificateArr));
            return this;
        }

        public SequenceOfCertificate build() {
            return new SequenceOfCertificate(this.f14392a);
        }
    }

    public SequenceOfCertificate(List<Certificate> list) {
        this.certificates = Collections.unmodifiableList(list);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static SequenceOfCertificate getInstance(Object obj) {
        if (obj instanceof SequenceOfCertificate) {
            return (SequenceOfCertificate) obj;
        }
        Iterator<ASN1Encodable> it = ASN1Sequence.getInstance(obj).iterator();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(Certificate.getInstance(it.next()));
        }
        return new SequenceOfCertificate(arrayList);
    }

    public List<Certificate> getCertificates() {
        return this.certificates;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return Utils.b(this.certificates);
    }
}
