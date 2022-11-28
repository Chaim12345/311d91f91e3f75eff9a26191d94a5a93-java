package org.bouncycastle.cms;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DLSet;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Store;
/* loaded from: classes3.dex */
public class CMSSignedData implements Encodable {
    private static final CMSSignedHelper HELPER = CMSSignedHelper.f13148a;
    private static final DefaultDigestAlgorithmIdentifierFinder dgstAlgFinder = new DefaultDigestAlgorithmIdentifierFinder();

    /* renamed from: a  reason: collision with root package name */
    SignedData f13135a;

    /* renamed from: b  reason: collision with root package name */
    ContentInfo f13136b;

    /* renamed from: c  reason: collision with root package name */
    CMSTypedData f13137c;

    /* renamed from: d  reason: collision with root package name */
    SignerInformationStore f13138d;
    private Map hashes;

    public CMSSignedData(InputStream inputStream) {
        this(CMSUtils.q(inputStream));
    }

    public CMSSignedData(Map map, ContentInfo contentInfo) {
        this.hashes = map;
        this.f13136b = contentInfo;
        this.f13135a = getSignedData();
    }

    public CMSSignedData(Map map, byte[] bArr) {
        this(map, CMSUtils.r(bArr));
    }

    public CMSSignedData(ContentInfo contentInfo) {
        this.f13136b = contentInfo;
        SignedData signedData = getSignedData();
        this.f13135a = signedData;
        ASN1Encodable content = signedData.getEncapContentInfo().getContent();
        if (content != null) {
            this.f13137c = content instanceof ASN1OctetString ? new CMSProcessableByteArray(this.f13135a.getEncapContentInfo().getContentType(), ((ASN1OctetString) content).getOctets()) : new PKCS7ProcessableObject(this.f13135a.getEncapContentInfo().getContentType(), content);
        } else {
            this.f13137c = null;
        }
    }

    public CMSSignedData(CMSProcessable cMSProcessable, InputStream inputStream) {
        this(cMSProcessable, CMSUtils.q(new ASN1InputStream(inputStream)));
    }

    public CMSSignedData(final CMSProcessable cMSProcessable, ContentInfo contentInfo) {
        if (cMSProcessable instanceof CMSTypedData) {
            this.f13137c = (CMSTypedData) cMSProcessable;
        } else {
            this.f13137c = new CMSTypedData() { // from class: org.bouncycastle.cms.CMSSignedData.1
                @Override // org.bouncycastle.cms.CMSProcessable
                public Object getContent() {
                    return cMSProcessable.getContent();
                }

                @Override // org.bouncycastle.cms.CMSTypedData
                public ASN1ObjectIdentifier getContentType() {
                    return CMSSignedData.this.f13135a.getEncapContentInfo().getContentType();
                }

                @Override // org.bouncycastle.cms.CMSProcessable
                public void write(OutputStream outputStream) {
                    cMSProcessable.write(outputStream);
                }
            };
        }
        this.f13136b = contentInfo;
        this.f13135a = getSignedData();
    }

    public CMSSignedData(CMSProcessable cMSProcessable, byte[] bArr) {
        this(cMSProcessable, CMSUtils.r(bArr));
    }

    private CMSSignedData(CMSSignedData cMSSignedData) {
        this.f13135a = cMSSignedData.f13135a;
        this.f13136b = cMSSignedData.f13136b;
        this.f13137c = cMSSignedData.f13137c;
        this.f13138d = cMSSignedData.f13138d;
    }

    public CMSSignedData(byte[] bArr) {
        this(CMSUtils.r(bArr));
    }

    public static CMSSignedData addDigestAlgorithm(CMSSignedData cMSSignedData, AlgorithmIdentifier algorithmIdentifier) {
        Set<AlgorithmIdentifier> digestAlgorithmIDs = cMSSignedData.getDigestAlgorithmIDs();
        AlgorithmIdentifier a2 = CMSSignedHelper.f13148a.a(algorithmIdentifier, dgstAlgFinder);
        if (digestAlgorithmIDs.contains(a2)) {
            return cMSSignedData;
        }
        CMSSignedData cMSSignedData2 = new CMSSignedData(cMSSignedData);
        HashSet hashSet = new HashSet();
        for (AlgorithmIdentifier algorithmIdentifier2 : digestAlgorithmIDs) {
            hashSet.add(CMSSignedHelper.f13148a.a(algorithmIdentifier2, dgstAlgFinder));
        }
        hashSet.add(a2);
        ASN1Set d2 = CMSUtils.d(hashSet);
        ASN1Sequence aSN1Sequence = (ASN1Sequence) cMSSignedData.f13135a.toASN1Primitive();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(aSN1Sequence.getObjectAt(0));
        aSN1EncodableVector.add(d2);
        for (int i2 = 2; i2 != aSN1Sequence.size(); i2++) {
            aSN1EncodableVector.add(aSN1Sequence.getObjectAt(i2));
        }
        cMSSignedData2.f13135a = SignedData.getInstance(new BERSequence(aSN1EncodableVector));
        cMSSignedData2.f13136b = new ContentInfo(cMSSignedData2.f13136b.getContentType(), cMSSignedData2.f13135a);
        return cMSSignedData2;
    }

    private SignedData getSignedData() {
        try {
            return SignedData.getInstance(this.f13136b.getContent());
        } catch (ClassCastException e2) {
            throw new CMSException("Malformed content.", e2);
        } catch (IllegalArgumentException e3) {
            throw new CMSException("Malformed content.", e3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static CMSSignedData replaceCertificatesAndCRLs(CMSSignedData cMSSignedData, Store store, Store store2, Store store3) {
        ASN1Set aSN1Set;
        ASN1Set aSN1Set2;
        CMSSignedData cMSSignedData2 = new CMSSignedData(cMSSignedData);
        if (store != null || store2 != null) {
            ArrayList arrayList = new ArrayList();
            if (store != null) {
                arrayList.addAll(CMSUtils.j(store));
            }
            if (store2 != null) {
                arrayList.addAll(CMSUtils.h(store2));
            }
            ASN1Set f2 = CMSUtils.f(arrayList);
            if (f2.size() != 0) {
                aSN1Set = f2;
                if (store3 != null) {
                    ASN1Set f3 = CMSUtils.f(CMSUtils.i(store3));
                    if (f3.size() != 0) {
                        aSN1Set2 = f3;
                        cMSSignedData2.f13135a = new SignedData(cMSSignedData.f13135a.getDigestAlgorithms(), cMSSignedData.f13135a.getEncapContentInfo(), aSN1Set, aSN1Set2, cMSSignedData.f13135a.getSignerInfos());
                        cMSSignedData2.f13136b = new ContentInfo(cMSSignedData2.f13136b.getContentType(), cMSSignedData2.f13135a);
                        return cMSSignedData2;
                    }
                }
                aSN1Set2 = null;
                cMSSignedData2.f13135a = new SignedData(cMSSignedData.f13135a.getDigestAlgorithms(), cMSSignedData.f13135a.getEncapContentInfo(), aSN1Set, aSN1Set2, cMSSignedData.f13135a.getSignerInfos());
                cMSSignedData2.f13136b = new ContentInfo(cMSSignedData2.f13136b.getContentType(), cMSSignedData2.f13135a);
                return cMSSignedData2;
            }
        }
        aSN1Set = null;
        if (store3 != null) {
        }
        aSN1Set2 = null;
        cMSSignedData2.f13135a = new SignedData(cMSSignedData.f13135a.getDigestAlgorithms(), cMSSignedData.f13135a.getEncapContentInfo(), aSN1Set, aSN1Set2, cMSSignedData.f13135a.getSignerInfos());
        cMSSignedData2.f13136b = new ContentInfo(cMSSignedData2.f13136b.getContentType(), cMSSignedData2.f13135a);
        return cMSSignedData2;
    }

    public static CMSSignedData replaceSigners(CMSSignedData cMSSignedData, SignerInformationStore signerInformationStore) {
        CMSSignedData cMSSignedData2 = new CMSSignedData(cMSSignedData);
        cMSSignedData2.f13138d = signerInformationStore;
        HashSet hashSet = new HashSet();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (SignerInformation signerInformation : signerInformationStore.getSigners()) {
            CMSUtils.a(hashSet, signerInformation, dgstAlgFinder);
            aSN1EncodableVector.add(signerInformation.toASN1Structure());
        }
        ASN1Set d2 = CMSUtils.d(hashSet);
        DLSet dLSet = new DLSet(aSN1EncodableVector);
        ASN1Sequence aSN1Sequence = (ASN1Sequence) cMSSignedData.f13135a.toASN1Primitive();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(aSN1Sequence.getObjectAt(0));
        aSN1EncodableVector2.add(d2);
        for (int i2 = 2; i2 != aSN1Sequence.size() - 1; i2++) {
            aSN1EncodableVector2.add(aSN1Sequence.getObjectAt(i2));
        }
        aSN1EncodableVector2.add(dLSet);
        cMSSignedData2.f13135a = SignedData.getInstance(new BERSequence(aSN1EncodableVector2));
        cMSSignedData2.f13136b = new ContentInfo(cMSSignedData2.f13136b.getContentType(), cMSSignedData2.f13135a);
        return cMSSignedData2;
    }

    private boolean verifyCounterSignature(SignerInformation signerInformation, SignerInformationVerifierProvider signerInformationVerifierProvider) {
        if (signerInformation.verify(signerInformationVerifierProvider.get(signerInformation.getSID()))) {
            for (SignerInformation signerInformation2 : signerInformation.getCounterSignatures().getSigners()) {
                if (!verifyCounterSignature(signerInformation2, signerInformationVerifierProvider)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Store<X509AttributeCertificateHolder> getAttributeCertificates() {
        return HELPER.b(this.f13135a.getCertificates());
    }

    public Store<X509CRLHolder> getCRLs() {
        return HELPER.c(this.f13135a.getCRLs());
    }

    public Store<X509CertificateHolder> getCertificates() {
        return HELPER.d(this.f13135a.getCertificates());
    }

    public Set<AlgorithmIdentifier> getDigestAlgorithmIDs() {
        HashSet hashSet = new HashSet(this.f13135a.getDigestAlgorithms().size());
        Enumeration objects = this.f13135a.getDigestAlgorithms().getObjects();
        while (objects.hasMoreElements()) {
            hashSet.add(AlgorithmIdentifier.getInstance(objects.nextElement()));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        return this.f13136b.getEncoded();
    }

    public byte[] getEncoded(String str) {
        return this.f13136b.getEncoded(str);
    }

    public Store getOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return HELPER.f(aSN1ObjectIdentifier, this.f13135a.getCRLs());
    }

    public CMSTypedData getSignedContent() {
        return this.f13137c;
    }

    public String getSignedContentTypeOID() {
        return this.f13135a.getEncapContentInfo().getContentType().getId();
    }

    public SignerInformationStore getSignerInfos() {
        Map map;
        Object algorithm;
        if (this.f13138d == null) {
            ASN1Set signerInfos = this.f13135a.getSignerInfos();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 != signerInfos.size(); i2++) {
                SignerInfo signerInfo = SignerInfo.getInstance(signerInfos.getObjectAt(i2));
                ASN1ObjectIdentifier contentType = this.f13135a.getEncapContentInfo().getContentType();
                Map map2 = this.hashes;
                if (map2 == null) {
                    arrayList.add(new SignerInformation(signerInfo, contentType, this.f13137c, null));
                } else {
                    if (map2.keySet().iterator().next() instanceof String) {
                        map = this.hashes;
                        algorithm = signerInfo.getDigestAlgorithm().getAlgorithm().getId();
                    } else {
                        map = this.hashes;
                        algorithm = signerInfo.getDigestAlgorithm().getAlgorithm();
                    }
                    arrayList.add(new SignerInformation(signerInfo, contentType, null, (byte[]) map.get(algorithm)));
                }
            }
            this.f13138d = new SignerInformationStore(arrayList);
        }
        return this.f13138d;
    }

    public int getVersion() {
        return this.f13135a.getVersion().intValueExact();
    }

    public boolean isCertificateManagementMessage() {
        return this.f13135a.getEncapContentInfo().getContent() == null && this.f13135a.getSignerInfos().size() == 0;
    }

    public boolean isDetachedSignature() {
        return this.f13135a.getEncapContentInfo().getContent() == null && this.f13135a.getSignerInfos().size() > 0;
    }

    public ContentInfo toASN1Structure() {
        return this.f13136b;
    }

    public boolean verifySignatures(SignerInformationVerifierProvider signerInformationVerifierProvider) {
        return verifySignatures(signerInformationVerifierProvider, false);
    }

    public boolean verifySignatures(SignerInformationVerifierProvider signerInformationVerifierProvider, boolean z) {
        for (SignerInformation signerInformation : getSignerInfos().getSigners()) {
            try {
                if (!signerInformation.verify(signerInformationVerifierProvider.get(signerInformation.getSID()))) {
                    return false;
                }
                if (!z) {
                    for (SignerInformation signerInformation2 : signerInformation.getCounterSignatures().getSigners()) {
                        if (!verifyCounterSignature(signerInformation2, signerInformationVerifierProvider)) {
                            return false;
                        }
                    }
                    continue;
                }
            } catch (OperatorCreationException e2) {
                throw new CMSException("failure in verifier provider: " + e2.getMessage(), e2);
            }
        }
        return true;
    }
}
