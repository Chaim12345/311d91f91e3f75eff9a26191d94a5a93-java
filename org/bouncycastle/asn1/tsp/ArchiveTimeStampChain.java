package org.bouncycastle.asn1.tsp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class ArchiveTimeStampChain extends ASN1Object {
    private ASN1Sequence archiveTimestamps;

    private ArchiveTimeStampChain(ASN1Sequence aSN1Sequence) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(aSN1Sequence.size());
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            aSN1EncodableVector.add(ArchiveTimeStamp.getInstance(objects.nextElement()));
        }
        this.archiveTimestamps = new DERSequence(aSN1EncodableVector);
    }

    public ArchiveTimeStampChain(ArchiveTimeStamp archiveTimeStamp) {
        this.archiveTimestamps = new DERSequence(archiveTimeStamp);
    }

    public ArchiveTimeStampChain(ArchiveTimeStamp[] archiveTimeStampArr) {
        this.archiveTimestamps = new DERSequence(archiveTimeStampArr);
    }

    public static ArchiveTimeStampChain getInstance(Object obj) {
        if (obj instanceof ArchiveTimeStampChain) {
            return (ArchiveTimeStampChain) obj;
        }
        if (obj != null) {
            return new ArchiveTimeStampChain(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ArchiveTimeStampChain append(ArchiveTimeStamp archiveTimeStamp) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(this.archiveTimestamps.size() + 1);
        for (int i2 = 0; i2 != this.archiveTimestamps.size(); i2++) {
            aSN1EncodableVector.add(this.archiveTimestamps.getObjectAt(i2));
        }
        aSN1EncodableVector.add(archiveTimeStamp);
        return new ArchiveTimeStampChain(new DERSequence(aSN1EncodableVector));
    }

    public ArchiveTimeStamp[] getArchiveTimestamps() {
        int size = this.archiveTimestamps.size();
        ArchiveTimeStamp[] archiveTimeStampArr = new ArchiveTimeStamp[size];
        for (int i2 = 0; i2 != size; i2++) {
            archiveTimeStampArr[i2] = ArchiveTimeStamp.getInstance(this.archiveTimestamps.getObjectAt(i2));
        }
        return archiveTimeStampArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.archiveTimestamps;
    }
}
