package org.bouncycastle.asn1.tsp;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes3.dex */
public class ArchiveTimeStamp extends ASN1Object {
    private final Attributes attributes;
    private final AlgorithmIdentifier digestAlgorithm;
    private final ASN1Sequence reducedHashTree;
    private final ContentInfo timeStamp;

    private ArchiveTimeStamp(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 4) {
            throw new IllegalArgumentException("wrong sequence size in constructor: " + aSN1Sequence.size());
        }
        AlgorithmIdentifier algorithmIdentifier = null;
        Attributes attributes = null;
        ASN1Sequence aSN1Sequence2 = null;
        for (int i2 = 0; i2 < aSN1Sequence.size() - 1; i2++) {
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i2);
            if (objectAt instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objectAt);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    algorithmIdentifier = AlgorithmIdentifier.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 1) {
                    attributes = Attributes.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 2) {
                    throw new IllegalArgumentException("invalid tag no in constructor: " + aSN1TaggedObject.getTagNo());
                } else {
                    aSN1Sequence2 = ASN1Sequence.getInstance(aSN1TaggedObject, false);
                }
            }
        }
        this.digestAlgorithm = algorithmIdentifier;
        this.attributes = attributes;
        this.reducedHashTree = aSN1Sequence2;
        this.timeStamp = ContentInfo.getInstance(aSN1Sequence.getObjectAt(aSN1Sequence.size() - 1));
    }

    public ArchiveTimeStamp(ContentInfo contentInfo) {
        this(null, null, null, contentInfo);
    }

    public ArchiveTimeStamp(AlgorithmIdentifier algorithmIdentifier, Attributes attributes, PartialHashtree[] partialHashtreeArr, ContentInfo contentInfo) {
        this.digestAlgorithm = algorithmIdentifier;
        this.attributes = attributes;
        this.reducedHashTree = partialHashtreeArr != null ? new DERSequence(partialHashtreeArr) : null;
        this.timeStamp = contentInfo;
    }

    public ArchiveTimeStamp(AlgorithmIdentifier algorithmIdentifier, PartialHashtree[] partialHashtreeArr, ContentInfo contentInfo) {
        this(algorithmIdentifier, null, partialHashtreeArr, contentInfo);
    }

    public static ArchiveTimeStamp getInstance(Object obj) {
        if (obj instanceof ArchiveTimeStamp) {
            return (ArchiveTimeStamp) obj;
        }
        if (obj != null) {
            return new ArchiveTimeStamp(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digestAlgorithm;
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        AlgorithmIdentifier algorithmIdentifier = this.digestAlgorithm;
        if (algorithmIdentifier != null) {
            return algorithmIdentifier;
        }
        if (this.timeStamp.getContentType().equals((ASN1Primitive) CMSObjectIdentifiers.signedData)) {
            SignedData signedData = SignedData.getInstance(this.timeStamp.getContent());
            if (signedData.getEncapContentInfo().getContentType().equals((ASN1Primitive) PKCSObjectIdentifiers.id_ct_TSTInfo)) {
                return TSTInfo.getInstance(signedData.getEncapContentInfo()).getMessageImprint().getHashAlgorithm();
            }
            throw new IllegalStateException("cannot parse time stamp");
        }
        throw new IllegalStateException("cannot identify algorithm identifier for digest");
    }

    public PartialHashtree[] getReducedHashTree() {
        ASN1Sequence aSN1Sequence = this.reducedHashTree;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        PartialHashtree[] partialHashtreeArr = new PartialHashtree[size];
        for (int i2 = 0; i2 != size; i2++) {
            partialHashtreeArr[i2] = PartialHashtree.getInstance(this.reducedHashTree.getObjectAt(i2));
        }
        return partialHashtreeArr;
    }

    public ContentInfo getTimeStamp() {
        return this.timeStamp;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        AlgorithmIdentifier algorithmIdentifier = this.digestAlgorithm;
        if (algorithmIdentifier != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) algorithmIdentifier));
        }
        Attributes attributes = this.attributes;
        if (attributes != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) attributes));
        }
        ASN1Sequence aSN1Sequence = this.reducedHashTree;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, (ASN1Encodable) aSN1Sequence));
        }
        aSN1EncodableVector.add(this.timeStamp);
        return new DERSequence(aSN1EncodableVector);
    }
}
