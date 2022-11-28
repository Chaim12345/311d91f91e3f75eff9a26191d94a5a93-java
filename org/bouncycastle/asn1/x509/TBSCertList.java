package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes3.dex */
public class TBSCertList extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12970a;

    /* renamed from: b  reason: collision with root package name */
    AlgorithmIdentifier f12971b;

    /* renamed from: c  reason: collision with root package name */
    X500Name f12972c;

    /* renamed from: d  reason: collision with root package name */
    Time f12973d;

    /* renamed from: e  reason: collision with root package name */
    Time f12974e;

    /* renamed from: f  reason: collision with root package name */
    ASN1Sequence f12975f;

    /* renamed from: g  reason: collision with root package name */
    Extensions f12976g;

    /* loaded from: classes3.dex */
    public static class CRLEntry extends ASN1Object {

        /* renamed from: a  reason: collision with root package name */
        ASN1Sequence f12977a;

        /* renamed from: b  reason: collision with root package name */
        Extensions f12978b;

        private CRLEntry(ASN1Sequence aSN1Sequence) {
            if (aSN1Sequence.size() >= 2 && aSN1Sequence.size() <= 3) {
                this.f12977a = aSN1Sequence;
                return;
            }
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }

        public static CRLEntry getInstance(Object obj) {
            if (obj instanceof CRLEntry) {
                return (CRLEntry) obj;
            }
            if (obj != null) {
                return new CRLEntry(ASN1Sequence.getInstance(obj));
            }
            return null;
        }

        public Extensions getExtensions() {
            if (this.f12978b == null && this.f12977a.size() == 3) {
                this.f12978b = Extensions.getInstance(this.f12977a.getObjectAt(2));
            }
            return this.f12978b;
        }

        public Time getRevocationDate() {
            return Time.getInstance(this.f12977a.getObjectAt(1));
        }

        public ASN1Integer getUserCertificate() {
            return ASN1Integer.getInstance(this.f12977a.getObjectAt(0));
        }

        public boolean hasExtensions() {
            return this.f12977a.size() == 3;
        }

        @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
        public ASN1Primitive toASN1Primitive() {
            return this.f12977a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class EmptyEnumeration implements Enumeration {
        private EmptyEnumeration(TBSCertList tBSCertList) {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            throw new NoSuchElementException("Empty Enumeration");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class RevokedCertificatesEnumeration implements Enumeration {
        private final Enumeration en;

        RevokedCertificatesEnumeration(TBSCertList tBSCertList, Enumeration enumeration) {
            this.en = enumeration;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.en.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return CRLEntry.getInstance(this.en.nextElement());
        }
    }

    public TBSCertList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 7) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int i2 = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.f12970a = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            i2 = 1;
        } else {
            this.f12970a = null;
        }
        int i3 = i2 + 1;
        this.f12971b = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i2));
        int i4 = i3 + 1;
        this.f12972c = X500Name.getInstance(aSN1Sequence.getObjectAt(i3));
        int i5 = i4 + 1;
        this.f12973d = Time.getInstance(aSN1Sequence.getObjectAt(i4));
        if (i5 < aSN1Sequence.size() && ((aSN1Sequence.getObjectAt(i5) instanceof ASN1UTCTime) || (aSN1Sequence.getObjectAt(i5) instanceof ASN1GeneralizedTime) || (aSN1Sequence.getObjectAt(i5) instanceof Time))) {
            this.f12974e = Time.getInstance(aSN1Sequence.getObjectAt(i5));
            i5++;
        }
        if (i5 < aSN1Sequence.size() && !(aSN1Sequence.getObjectAt(i5) instanceof ASN1TaggedObject)) {
            this.f12975f = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(i5));
            i5++;
        }
        if (i5 >= aSN1Sequence.size() || !(aSN1Sequence.getObjectAt(i5) instanceof ASN1TaggedObject)) {
            return;
        }
        this.f12976g = Extensions.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i5), true));
    }

    public static TBSCertList getInstance(Object obj) {
        if (obj instanceof TBSCertList) {
            return (TBSCertList) obj;
        }
        if (obj != null) {
            return new TBSCertList(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertList getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Extensions getExtensions() {
        return this.f12976g;
    }

    public X500Name getIssuer() {
        return this.f12972c;
    }

    public Time getNextUpdate() {
        return this.f12974e;
    }

    public Enumeration getRevokedCertificateEnumeration() {
        ASN1Sequence aSN1Sequence = this.f12975f;
        return aSN1Sequence == null ? new EmptyEnumeration() : new RevokedCertificatesEnumeration(this, aSN1Sequence.getObjects());
    }

    public CRLEntry[] getRevokedCertificates() {
        ASN1Sequence aSN1Sequence = this.f12975f;
        if (aSN1Sequence == null) {
            return new CRLEntry[0];
        }
        int size = aSN1Sequence.size();
        CRLEntry[] cRLEntryArr = new CRLEntry[size];
        for (int i2 = 0; i2 < size; i2++) {
            cRLEntryArr[i2] = CRLEntry.getInstance(this.f12975f.getObjectAt(i2));
        }
        return cRLEntryArr;
    }

    public AlgorithmIdentifier getSignature() {
        return this.f12971b;
    }

    public Time getThisUpdate() {
        return this.f12973d;
    }

    public ASN1Integer getVersion() {
        return this.f12970a;
    }

    public int getVersionNumber() {
        ASN1Integer aSN1Integer = this.f12970a;
        if (aSN1Integer == null) {
            return 1;
        }
        return aSN1Integer.intValueExact() + 1;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(7);
        ASN1Integer aSN1Integer = this.f12970a;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        aSN1EncodableVector.add(this.f12971b);
        aSN1EncodableVector.add(this.f12972c);
        aSN1EncodableVector.add(this.f12973d);
        Time time = this.f12974e;
        if (time != null) {
            aSN1EncodableVector.add(time);
        }
        ASN1Sequence aSN1Sequence = this.f12975f;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        Extensions extensions = this.f12976g;
        if (extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, extensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
