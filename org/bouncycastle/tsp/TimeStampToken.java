package org.bouncycastle.tsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificate;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Store;
/* loaded from: classes4.dex */
public class TimeStampToken {

    /* renamed from: a  reason: collision with root package name */
    CMSSignedData f15084a;

    /* renamed from: b  reason: collision with root package name */
    SignerInformation f15085b;

    /* renamed from: c  reason: collision with root package name */
    TimeStampTokenInfo f15086c;

    /* renamed from: d  reason: collision with root package name */
    CertID f15087d;

    /* loaded from: classes4.dex */
    private class CertID {
        private ESSCertID certID;
        private ESSCertIDv2 certIDv2;

        CertID(TimeStampToken timeStampToken, ESSCertID eSSCertID) {
            this.certID = eSSCertID;
            this.certIDv2 = null;
        }

        CertID(TimeStampToken timeStampToken, ESSCertIDv2 eSSCertIDv2) {
            this.certIDv2 = eSSCertIDv2;
            this.certID = null;
        }

        public byte[] getCertHash() {
            ESSCertID eSSCertID = this.certID;
            return eSSCertID != null ? eSSCertID.getCertHash() : this.certIDv2.getCertHash();
        }

        public AlgorithmIdentifier getHashAlgorithm() {
            return this.certID != null ? new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1) : this.certIDv2.getHashAlgorithm();
        }

        public IssuerSerial getIssuerSerial() {
            ESSCertID eSSCertID = this.certID;
            return eSSCertID != null ? eSSCertID.getIssuerSerial() : this.certIDv2.getIssuerSerial();
        }
    }

    public TimeStampToken(ContentInfo contentInfo) {
        this(getSignedData(contentInfo));
    }

    public TimeStampToken(CMSSignedData cMSSignedData) {
        CertID certID;
        this.f15084a = cMSSignedData;
        if (!cMSSignedData.getSignedContentTypeOID().equals(PKCSObjectIdentifiers.id_ct_TSTInfo.getId())) {
            throw new TSPValidationException("ContentInfo object not for a time stamp.");
        }
        Collection<SignerInformation> signers = this.f15084a.getSignerInfos().getSigners();
        if (signers.size() != 1) {
            throw new IllegalArgumentException("Time-stamp token signed by " + signers.size() + " signers, but it must contain just the TSA signature.");
        }
        this.f15085b = signers.iterator().next();
        try {
            CMSTypedData signedContent = this.f15084a.getSignedContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            signedContent.write(byteArrayOutputStream);
            this.f15086c = new TimeStampTokenInfo(TSTInfo.getInstance(ASN1Primitive.fromByteArray(byteArrayOutputStream.toByteArray())));
            Attribute attribute = this.f15085b.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
            if (attribute != null) {
                certID = new CertID(this, ESSCertID.getInstance(SigningCertificate.getInstance(attribute.getAttrValues().getObjectAt(0)).getCerts()[0]));
            } else {
                Attribute attribute2 = this.f15085b.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
                if (attribute2 == null) {
                    throw new TSPValidationException("no signing certificate attribute found, time stamp invalid.");
                }
                certID = new CertID(this, ESSCertIDv2.getInstance(SigningCertificateV2.getInstance(attribute2.getAttrValues().getObjectAt(0)).getCerts()[0]));
            }
            this.f15087d = certID;
        } catch (CMSException e2) {
            throw new TSPException(e2.getMessage(), e2.getUnderlyingException());
        }
    }

    private static CMSSignedData getSignedData(ContentInfo contentInfo) {
        try {
            return new CMSSignedData(contentInfo);
        } catch (CMSException e2) {
            throw new TSPException("TSP parsing error: " + e2.getMessage(), e2.getCause());
        }
    }

    public Store<X509AttributeCertificateHolder> getAttributeCertificates() {
        return this.f15084a.getAttributeCertificates();
    }

    public Store<X509CRLHolder> getCRLs() {
        return this.f15084a.getCRLs();
    }

    public Store<X509CertificateHolder> getCertificates() {
        return this.f15084a.getCertificates();
    }

    public byte[] getEncoded() {
        return this.f15084a.getEncoded(ASN1Encoding.DL);
    }

    public byte[] getEncoded(String str) {
        return this.f15084a.getEncoded(str);
    }

    public SignerId getSID() {
        return this.f15085b.getSID();
    }

    public AttributeTable getSignedAttributes() {
        return this.f15085b.getSignedAttributes();
    }

    public TimeStampTokenInfo getTimeStampInfo() {
        return this.f15086c;
    }

    public AttributeTable getUnsignedAttributes() {
        return this.f15085b.getUnsignedAttributes();
    }

    public boolean isSignatureValid(SignerInformationVerifier signerInformationVerifier) {
        try {
            return this.f15085b.verify(signerInformationVerifier);
        } catch (CMSException e2) {
            if (e2.getUnderlyingException() != null) {
                throw new TSPException(e2.getMessage(), e2.getUnderlyingException());
            }
            throw new TSPException("CMS exception: " + e2, e2);
        }
    }

    public CMSSignedData toCMSSignedData() {
        return this.f15084a;
    }

    public void validate(SignerInformationVerifier signerInformationVerifier) {
        if (!signerInformationVerifier.hasAssociatedCertificate()) {
            throw new IllegalArgumentException("verifier provider needs an associated certificate");
        }
        try {
            X509CertificateHolder associatedCertificate = signerInformationVerifier.getAssociatedCertificate();
            DigestCalculator digestCalculator = signerInformationVerifier.getDigestCalculator(this.f15087d.getHashAlgorithm());
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(associatedCertificate.getEncoded());
            outputStream.close();
            if (!Arrays.constantTimeAreEqual(this.f15087d.getCertHash(), digestCalculator.getDigest())) {
                throw new TSPValidationException("certificate hash does not match certID hash.");
            }
            if (this.f15087d.getIssuerSerial() != null) {
                IssuerAndSerialNumber issuerAndSerialNumber = new IssuerAndSerialNumber(associatedCertificate.toASN1Structure());
                if (!this.f15087d.getIssuerSerial().getSerial().equals((ASN1Primitive) issuerAndSerialNumber.getSerialNumber())) {
                    throw new TSPValidationException("certificate serial number does not match certID for signature.");
                }
                GeneralName[] names = this.f15087d.getIssuerSerial().getIssuer().getNames();
                boolean z = false;
                int i2 = 0;
                while (true) {
                    if (i2 != names.length) {
                        if (names[i2].getTagNo() == 4 && X500Name.getInstance(names[i2].getName()).equals(X500Name.getInstance(issuerAndSerialNumber.getName()))) {
                            z = true;
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
                if (!z) {
                    throw new TSPValidationException("certificate name does not match certID for signature. ");
                }
            }
            TSPUtil.validateCertificate(associatedCertificate);
            if (!associatedCertificate.isValidOn(this.f15086c.getGenTime())) {
                throw new TSPValidationException("certificate not valid when time stamp created.");
            }
            if (!this.f15085b.verify(signerInformationVerifier)) {
                throw new TSPValidationException("signature not created by certificate.");
            }
        } catch (IOException e2) {
            throw new TSPException("problem processing certificate: " + e2, e2);
        } catch (CMSException e3) {
            if (e3.getUnderlyingException() != null) {
                throw new TSPException(e3.getMessage(), e3.getUnderlyingException());
            }
            throw new TSPException("CMS exception: " + e3, e3);
        } catch (OperatorCreationException e4) {
            throw new TSPException("unable to create digest: " + e4.getMessage(), e4);
        }
    }
}
