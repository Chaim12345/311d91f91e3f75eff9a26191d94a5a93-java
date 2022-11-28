package org.bouncycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
/* loaded from: classes3.dex */
public class CMSSignedDataGenerator extends CMSSignedGenerator {
    private List signerInfs;

    public CMSSignedDataGenerator() {
        this.signerInfs = new ArrayList();
    }

    public CMSSignedDataGenerator(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        super(digestAlgorithmIdentifierFinder);
        this.signerInfs = new ArrayList();
    }

    public CMSSignedData generate(CMSTypedData cMSTypedData) {
        return generate(cMSTypedData, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CMSSignedData generate(CMSTypedData cMSTypedData, boolean z) {
        BEROctetString bEROctetString;
        if (this.signerInfs.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            this.f13146e.clear();
            for (SignerInformation signerInformation : this.f13144c) {
                CMSUtils.a(linkedHashSet, signerInformation, this.f13147f);
                aSN1EncodableVector.add(signerInformation.toASN1Structure());
            }
            ASN1ObjectIdentifier contentType = cMSTypedData.getContentType();
            if (cMSTypedData.getContent() != null) {
                ByteArrayOutputStream byteArrayOutputStream = z ? new ByteArrayOutputStream() : null;
                OutputStream l2 = CMSUtils.l(CMSUtils.c(this.f13145d, byteArrayOutputStream));
                try {
                    cMSTypedData.write(l2);
                    l2.close();
                    if (z) {
                        bEROctetString = new BEROctetString(byteArrayOutputStream.toByteArray());
                        for (SignerInfoGenerator signerInfoGenerator : this.f13145d) {
                            SignerInfo generate = signerInfoGenerator.generate(contentType);
                            linkedHashSet.add(generate.getDigestAlgorithm());
                            aSN1EncodableVector.add(generate);
                            byte[] calculatedDigest = signerInfoGenerator.getCalculatedDigest();
                            if (calculatedDigest != null) {
                                this.f13146e.put(generate.getDigestAlgorithm().getAlgorithm().getId(), calculatedDigest);
                            }
                        }
                        return new CMSSignedData(cMSTypedData, new ContentInfo(CMSObjectIdentifiers.signedData, new SignedData(CMSUtils.d(linkedHashSet), new ContentInfo(contentType, bEROctetString), this.f13142a.size() == 0 ? CMSUtils.f(this.f13142a) : null, this.f13143b.size() != 0 ? CMSUtils.f(this.f13143b) : null, new DERSet(aSN1EncodableVector))));
                    }
                } catch (IOException e2) {
                    throw new CMSException("data processing exception: " + e2.getMessage(), e2);
                }
            }
            bEROctetString = null;
            while (r3.hasNext()) {
            }
            if (this.f13142a.size() == 0) {
            }
            if (this.f13143b.size() != 0) {
            }
            return new CMSSignedData(cMSTypedData, new ContentInfo(CMSObjectIdentifiers.signedData, new SignedData(CMSUtils.d(linkedHashSet), new ContentInfo(contentType, bEROctetString), this.f13142a.size() == 0 ? CMSUtils.f(this.f13142a) : null, this.f13143b.size() != 0 ? CMSUtils.f(this.f13143b) : null, new DERSet(aSN1EncodableVector))));
        }
        throw new IllegalStateException("this method can only be used with SignerInfoGenerator");
    }

    public SignerInformationStore generateCounterSigners(SignerInformation signerInformation) {
        return generate(new CMSProcessableByteArray(null, signerInformation.getSignature()), false).getSignerInfos();
    }
}
