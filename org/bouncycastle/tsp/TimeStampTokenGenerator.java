package org.bouncycastle.tsp;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificate;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.Accuracy;
import org.bouncycastle.asn1.tsp.MessageImprint;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Store;
/* loaded from: classes4.dex */
public class TimeStampTokenGenerator {
    public static final int R_HUNDREDTHS_OF_SECONDS = 2;
    public static final int R_MICROSECONDS = 2;
    public static final int R_MILLISECONDS = 3;
    public static final int R_SECONDS = 0;
    public static final int R_TENTHS_OF_SECONDS = 1;

    /* renamed from: a  reason: collision with root package name */
    boolean f15088a;
    private int accuracyMicros;
    private int accuracyMillis;
    private int accuracySeconds;
    private List attrCerts;

    /* renamed from: b  reason: collision with root package name */
    GeneralName f15089b;
    private List certs;
    private List crls;
    private Locale locale;
    private Map otherRevoc;
    private int resolution;
    private SignerInfoGenerator signerInfoGen;
    private ASN1ObjectIdentifier tsaPolicyOID;

    public TimeStampTokenGenerator(SignerInfoGenerator signerInfoGenerator, DigestCalculator digestCalculator, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(signerInfoGenerator, digestCalculator, aSN1ObjectIdentifier, false);
    }

    public TimeStampTokenGenerator(final SignerInfoGenerator signerInfoGenerator, DigestCalculator digestCalculator, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z) {
        SignerInfoGenerator signerInfoGenerator2;
        this.resolution = 0;
        this.locale = null;
        this.accuracySeconds = -1;
        this.accuracyMillis = -1;
        this.accuracyMicros = -1;
        this.f15088a = false;
        this.f15089b = null;
        this.certs = new ArrayList();
        this.crls = new ArrayList();
        this.attrCerts = new ArrayList();
        this.otherRevoc = new HashMap();
        this.signerInfoGen = signerInfoGenerator;
        this.tsaPolicyOID = aSN1ObjectIdentifier;
        if (!signerInfoGenerator.hasAssociatedCertificate()) {
            throw new IllegalArgumentException("SignerInfoGenerator must have an associated certificate");
        }
        X509CertificateHolder associatedCertificate = signerInfoGenerator.getAssociatedCertificate();
        TSPUtil.validateCertificate(associatedCertificate);
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(associatedCertificate.getEncoded());
            outputStream.close();
            if (digestCalculator.getAlgorithmIdentifier().getAlgorithm().equals((ASN1Primitive) OIWObjectIdentifiers.idSHA1)) {
                final ESSCertID eSSCertID = new ESSCertID(digestCalculator.getDigest(), z ? new IssuerSerial(new GeneralNames(new GeneralName(associatedCertificate.getIssuer())), associatedCertificate.getSerialNumber()) : null);
                signerInfoGenerator2 = new SignerInfoGenerator(signerInfoGenerator, new CMSAttributeTableGenerator(this) { // from class: org.bouncycastle.tsp.TimeStampTokenGenerator.1
                    @Override // org.bouncycastle.cms.CMSAttributeTableGenerator
                    public AttributeTable getAttributes(Map map) {
                        AttributeTable attributes = signerInfoGenerator.getSignedAttributeTableGenerator().getAttributes(map);
                        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.id_aa_signingCertificate;
                        return attributes.get(aSN1ObjectIdentifier2) == null ? attributes.add(aSN1ObjectIdentifier2, new SigningCertificate(eSSCertID)) : attributes;
                    }
                }, signerInfoGenerator.getUnsignedAttributeTableGenerator());
            } else {
                final ESSCertIDv2 eSSCertIDv2 = new ESSCertIDv2(new AlgorithmIdentifier(digestCalculator.getAlgorithmIdentifier().getAlgorithm()), digestCalculator.getDigest(), z ? new IssuerSerial(new GeneralNames(new GeneralName(associatedCertificate.getIssuer())), new ASN1Integer(associatedCertificate.getSerialNumber())) : null);
                signerInfoGenerator2 = new SignerInfoGenerator(signerInfoGenerator, new CMSAttributeTableGenerator(this) { // from class: org.bouncycastle.tsp.TimeStampTokenGenerator.2
                    @Override // org.bouncycastle.cms.CMSAttributeTableGenerator
                    public AttributeTable getAttributes(Map map) {
                        AttributeTable attributes = signerInfoGenerator.getSignedAttributeTableGenerator().getAttributes(map);
                        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.id_aa_signingCertificateV2;
                        return attributes.get(aSN1ObjectIdentifier2) == null ? attributes.add(aSN1ObjectIdentifier2, new SigningCertificateV2(eSSCertIDv2)) : attributes;
                    }
                }, signerInfoGenerator.getUnsignedAttributeTableGenerator());
            }
            this.signerInfoGen = signerInfoGenerator2;
        } catch (IOException e2) {
            throw new TSPException("Exception processing certificate.", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0067, code lost:
        if (r1.length() > r4) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:
        if (r1.length() > r4) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0072, code lost:
        r1.delete(r4, r1.length());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ASN1GeneralizedTime createGeneralizedTime(Date date) {
        int i2;
        SimpleDateFormat simpleDateFormat = this.locale == null ? new SimpleDateFormat("yyyyMMddHHmmss.SSS") : new SimpleDateFormat("yyyyMMddHHmmss.SSS", this.locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        StringBuilder sb = new StringBuilder(simpleDateFormat.format(date));
        int indexOf = sb.indexOf(".");
        if (indexOf < 0) {
            sb.append("Z");
            return new ASN1GeneralizedTime(sb.toString());
        }
        int i3 = this.resolution;
        if (i3 == 1) {
            i2 = indexOf + 2;
        } else if (i3 == 2) {
            i2 = indexOf + 3;
        } else if (i3 != 3) {
            throw new TSPException("unknown time-stamp resolution: " + this.resolution);
        }
        while (sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb.length() - 1 == indexOf) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("Z");
        return new ASN1GeneralizedTime(sb.toString());
    }

    public void addAttributeCertificates(Store store) {
        this.attrCerts.addAll(store.getMatches(null));
    }

    public void addCRLs(Store store) {
        this.crls.addAll(store.getMatches(null));
    }

    public void addCertificates(Store store) {
        this.certs.addAll(store.getMatches(null));
    }

    public void addOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, Store store) {
        this.otherRevoc.put(aSN1ObjectIdentifier, store.getMatches(null));
    }

    public TimeStampToken generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) {
        return generate(timeStampRequest, bigInteger, date, null);
    }

    public TimeStampToken generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, Extensions extensions) {
        Extensions extensions2;
        MessageImprint messageImprint = new MessageImprint(timeStampRequest.getMessageImprintAlgID(), timeStampRequest.getMessageImprintDigest());
        int i2 = this.accuracySeconds;
        Accuracy accuracy = (i2 > 0 || this.accuracyMillis > 0 || this.accuracyMicros > 0) ? new Accuracy(i2 > 0 ? new ASN1Integer(this.accuracySeconds) : null, this.accuracyMillis > 0 ? new ASN1Integer(this.accuracyMillis) : null, this.accuracyMicros > 0 ? new ASN1Integer(this.accuracyMicros) : null) : null;
        boolean z = this.f15088a;
        ASN1Boolean aSN1Boolean = z ? ASN1Boolean.getInstance(z) : null;
        ASN1Integer aSN1Integer = timeStampRequest.getNonce() != null ? new ASN1Integer(timeStampRequest.getNonce()) : null;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.tsaPolicyOID;
        if (timeStampRequest.getReqPolicy() != null) {
            aSN1ObjectIdentifier = timeStampRequest.getReqPolicy();
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = aSN1ObjectIdentifier;
        Extensions a2 = timeStampRequest.a();
        if (extensions != null) {
            ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();
            if (a2 != null) {
                Enumeration oids = a2.oids();
                while (oids.hasMoreElements()) {
                    extensionsGenerator.addExtension(a2.getExtension(ASN1ObjectIdentifier.getInstance(oids.nextElement())));
                }
            }
            Enumeration oids2 = extensions.oids();
            while (oids2.hasMoreElements()) {
                extensionsGenerator.addExtension(extensions.getExtension(ASN1ObjectIdentifier.getInstance(oids2.nextElement())));
            }
            extensions2 = extensionsGenerator.generate();
        } else {
            extensions2 = a2;
        }
        TSTInfo tSTInfo = new TSTInfo(aSN1ObjectIdentifier2, messageImprint, new ASN1Integer(bigInteger), this.resolution == 0 ? this.locale == null ? new ASN1GeneralizedTime(date) : new ASN1GeneralizedTime(date, this.locale) : createGeneralizedTime(date), accuracy, aSN1Boolean, aSN1Integer, this.f15089b, extensions2);
        try {
            CMSSignedDataGenerator cMSSignedDataGenerator = new CMSSignedDataGenerator();
            if (timeStampRequest.getCertReq()) {
                cMSSignedDataGenerator.addCertificates(new CollectionStore(this.certs));
                cMSSignedDataGenerator.addAttributeCertificates(new CollectionStore(this.attrCerts));
            }
            cMSSignedDataGenerator.addCRLs(new CollectionStore(this.crls));
            if (!this.otherRevoc.isEmpty()) {
                for (ASN1ObjectIdentifier aSN1ObjectIdentifier3 : this.otherRevoc.keySet()) {
                    cMSSignedDataGenerator.addOtherRevocationInfo(aSN1ObjectIdentifier3, new CollectionStore((Collection) this.otherRevoc.get(aSN1ObjectIdentifier3)));
                }
            }
            cMSSignedDataGenerator.addSignerInfoGenerator(this.signerInfoGen);
            return new TimeStampToken(cMSSignedDataGenerator.generate(new CMSProcessableByteArray(PKCSObjectIdentifiers.id_ct_TSTInfo, tSTInfo.getEncoded(ASN1Encoding.DER)), true));
        } catch (IOException e2) {
            throw new TSPException("Exception encoding info", e2);
        } catch (CMSException e3) {
            throw new TSPException("Error generating time-stamp token", e3);
        }
    }

    public void setAccuracyMicros(int i2) {
        this.accuracyMicros = i2;
    }

    public void setAccuracyMillis(int i2) {
        this.accuracyMillis = i2;
    }

    public void setAccuracySeconds(int i2) {
        this.accuracySeconds = i2;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setOrdering(boolean z) {
        this.f15088a = z;
    }

    public void setResolution(int i2) {
        this.resolution = i2;
    }

    public void setTSA(GeneralName generalName) {
        this.f15089b = generalName;
    }
}
