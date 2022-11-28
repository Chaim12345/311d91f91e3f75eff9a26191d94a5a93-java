package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.CRLNumber;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.jcajce.CompositePublicKey;
import org.bouncycastle.jcajce.io.OutputStreamFactory;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
abstract class X509CRLImpl extends X509CRL {

    /* renamed from: a  reason: collision with root package name */
    protected JcaJceHelper f13724a;

    /* renamed from: b  reason: collision with root package name */
    protected CertificateList f13725b;

    /* renamed from: c  reason: collision with root package name */
    protected String f13726c;

    /* renamed from: d  reason: collision with root package name */
    protected byte[] f13727d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f13728e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public X509CRLImpl(JcaJceHelper jcaJceHelper, CertificateList certificateList, String str, byte[] bArr, boolean z) {
        this.f13724a = jcaJceHelper;
        this.f13725b = certificateList;
        this.f13726c = str;
        this.f13727d = bArr;
        this.f13728e = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] a(CertificateList certificateList, String str) {
        ASN1OctetString b2 = b(certificateList, str);
        if (b2 != null) {
            return b2.getOctets();
        }
        return null;
    }

    protected static ASN1OctetString b(CertificateList certificateList, String str) {
        Extension extension;
        Extensions extensions = certificateList.getTBSCertList().getExtensions();
        if (extensions == null || (extension = extensions.getExtension(new ASN1ObjectIdentifier(str))) == null) {
            return null;
        }
        return extension.getExtnValue();
    }

    private void checkSignature(PublicKey publicKey, Signature signature, ASN1Encodable aSN1Encodable, byte[] bArr) {
        if (aSN1Encodable != null) {
            X509SignatureUtil.d(signature, aSN1Encodable);
        }
        signature.initVerify(publicKey);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(OutputStreamFactory.createStream(signature), 512);
            this.f13725b.getTBSCertList().encodeTo(bufferedOutputStream, ASN1Encoding.DER);
            bufferedOutputStream.close();
            if (!signature.verify(bArr)) {
                throw new SignatureException("CRL does not verify with supplied public key.");
            }
        } catch (IOException e2) {
            throw new CRLException(e2.toString());
        }
    }

    private void doVerify(PublicKey publicKey, SignatureCreator signatureCreator) {
        if (!this.f13725b.getSignatureAlgorithm().equals(this.f13725b.getTBSCertList().getSignature())) {
            throw new CRLException("Signature algorithm on CertificateList does not match TBSCertList.");
        }
        int i2 = 0;
        if ((publicKey instanceof CompositePublicKey) && X509SignatureUtil.b(this.f13725b.getSignatureAlgorithm())) {
            List<PublicKey> publicKeys = ((CompositePublicKey) publicKey).getPublicKeys();
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(this.f13725b.getSignatureAlgorithm().getParameters());
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(DERBitString.getInstance((Object) this.f13725b.getSignature()).getBytes());
            boolean z = false;
            while (i2 != publicKeys.size()) {
                if (publicKeys.get(i2) != null) {
                    AlgorithmIdentifier algorithmIdentifier = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i2));
                    try {
                        checkSignature(publicKeys.get(i2), signatureCreator.createSignature(X509SignatureUtil.a(algorithmIdentifier)), algorithmIdentifier.getParameters(), DERBitString.getInstance((Object) aSN1Sequence2.getObjectAt(i2)).getBytes());
                        e = null;
                        z = true;
                    } catch (SignatureException e2) {
                        e = e2;
                    }
                    if (e != null) {
                        throw e;
                    }
                }
                i2++;
            }
            if (!z) {
                throw new InvalidKeyException("no matching key found");
            }
        } else if (!X509SignatureUtil.b(this.f13725b.getSignatureAlgorithm())) {
            Signature createSignature = signatureCreator.createSignature(getSigAlgName());
            byte[] bArr = this.f13727d;
            if (bArr == null) {
                checkSignature(publicKey, createSignature, null, getSignature());
                return;
            }
            try {
                checkSignature(publicKey, createSignature, ASN1Primitive.fromByteArray(bArr), getSignature());
            } catch (IOException e3) {
                throw new SignatureException("cannot decode signature parameters: " + e3.getMessage());
            }
        } else {
            ASN1Sequence aSN1Sequence3 = ASN1Sequence.getInstance(this.f13725b.getSignatureAlgorithm().getParameters());
            ASN1Sequence aSN1Sequence4 = ASN1Sequence.getInstance(DERBitString.getInstance((Object) this.f13725b.getSignature()).getBytes());
            boolean z2 = false;
            while (i2 != aSN1Sequence4.size()) {
                AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(aSN1Sequence3.getObjectAt(i2));
                try {
                    checkSignature(publicKey, signatureCreator.createSignature(X509SignatureUtil.a(algorithmIdentifier2)), algorithmIdentifier2.getParameters(), DERBitString.getInstance((Object) aSN1Sequence4.getObjectAt(i2)).getBytes());
                    e = null;
                    z2 = true;
                } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
                    e = null;
                } catch (SignatureException e4) {
                    e = e4;
                }
                if (e != null) {
                    throw e;
                }
                i2++;
            }
            if (!z2) {
                throw new InvalidKeyException("no matching key found");
            }
        }
    }

    private Set getExtensionOIDs(boolean z) {
        Extensions extensions;
        if (getVersion() != 2 || (extensions = this.f13725b.getTBSCertList().getExtensions()) == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Enumeration oids = extensions.oids();
        while (oids.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
            if (z == extensions.getExtension(aSN1ObjectIdentifier).isCritical()) {
                hashSet.add(aSN1ObjectIdentifier.getId());
            }
        }
        return hashSet;
    }

    private Set loadCRLEntries() {
        Extension extension;
        HashSet hashSet = new HashSet();
        Enumeration revokedCertificateEnumeration = this.f13725b.getRevokedCertificateEnumeration();
        X500Name x500Name = null;
        while (revokedCertificateEnumeration.hasMoreElements()) {
            TBSCertList.CRLEntry cRLEntry = (TBSCertList.CRLEntry) revokedCertificateEnumeration.nextElement();
            hashSet.add(new X509CRLEntryObject(cRLEntry, this.f13728e, x500Name));
            if (this.f13728e && cRLEntry.hasExtensions() && (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) != null) {
                x500Name = X500Name.getInstance(GeneralNames.getInstance(extension.getParsedValue()).getNames()[0].getName());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        return getExtensionOIDs(true);
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        ASN1OctetString b2 = b(this.f13725b, str);
        if (b2 != null) {
            try {
                return b2.getEncoded();
            } catch (Exception e2) {
                throw new IllegalStateException("error parsing " + e2.toString());
            }
        }
        return null;
    }

    @Override // java.security.cert.X509CRL
    public Principal getIssuerDN() {
        return new X509Principal(X500Name.getInstance(this.f13725b.getIssuer().toASN1Primitive()));
    }

    @Override // java.security.cert.X509CRL
    public X500Principal getIssuerX500Principal() {
        try {
            return new X500Principal(this.f13725b.getIssuer().getEncoded());
        } catch (IOException unused) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    @Override // java.security.cert.X509CRL
    public Date getNextUpdate() {
        Time nextUpdate = this.f13725b.getNextUpdate();
        if (nextUpdate == null) {
            return null;
        }
        return nextUpdate.getDate();
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        return getExtensionOIDs(false);
    }

    @Override // java.security.cert.X509CRL
    public X509CRLEntry getRevokedCertificate(BigInteger bigInteger) {
        Extension extension;
        Enumeration revokedCertificateEnumeration = this.f13725b.getRevokedCertificateEnumeration();
        X500Name x500Name = null;
        while (revokedCertificateEnumeration.hasMoreElements()) {
            TBSCertList.CRLEntry cRLEntry = (TBSCertList.CRLEntry) revokedCertificateEnumeration.nextElement();
            if (cRLEntry.getUserCertificate().hasValue(bigInteger)) {
                return new X509CRLEntryObject(cRLEntry, this.f13728e, x500Name);
            }
            if (this.f13728e && cRLEntry.hasExtensions() && (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) != null) {
                x500Name = X500Name.getInstance(GeneralNames.getInstance(extension.getParsedValue()).getNames()[0].getName());
            }
        }
        return null;
    }

    @Override // java.security.cert.X509CRL
    public Set getRevokedCertificates() {
        Set loadCRLEntries = loadCRLEntries();
        if (loadCRLEntries.isEmpty()) {
            return null;
        }
        return Collections.unmodifiableSet(loadCRLEntries);
    }

    @Override // java.security.cert.X509CRL
    public String getSigAlgName() {
        return this.f13726c;
    }

    @Override // java.security.cert.X509CRL
    public String getSigAlgOID() {
        return this.f13725b.getSignatureAlgorithm().getAlgorithm().getId();
    }

    @Override // java.security.cert.X509CRL
    public byte[] getSigAlgParams() {
        return Arrays.clone(this.f13727d);
    }

    @Override // java.security.cert.X509CRL
    public byte[] getSignature() {
        return this.f13725b.getSignature().getOctets();
    }

    @Override // java.security.cert.X509CRL
    public byte[] getTBSCertList() {
        try {
            return this.f13725b.getTBSCertList().getEncoded(ASN1Encoding.DER);
        } catch (IOException e2) {
            throw new CRLException(e2.toString());
        }
    }

    @Override // java.security.cert.X509CRL
    public Date getThisUpdate() {
        return this.f13725b.getThisUpdate().getDate();
    }

    @Override // java.security.cert.X509CRL
    public int getVersion() {
        return this.f13725b.getVersionNumber();
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        if (criticalExtensionOIDs == null) {
            return false;
        }
        criticalExtensionOIDs.remove(Extension.issuingDistributionPoint.getId());
        criticalExtensionOIDs.remove(Extension.deltaCRLIndicator.getId());
        return !criticalExtensionOIDs.isEmpty();
    }

    @Override // java.security.cert.CRL
    public boolean isRevoked(Certificate certificate) {
        X500Name issuer;
        Extension extension;
        if (certificate.getType().equals("X.509")) {
            Enumeration revokedCertificateEnumeration = this.f13725b.getRevokedCertificateEnumeration();
            X500Name issuer2 = this.f13725b.getIssuer();
            if (revokedCertificateEnumeration.hasMoreElements()) {
                X509Certificate x509Certificate = (X509Certificate) certificate;
                BigInteger serialNumber = x509Certificate.getSerialNumber();
                while (revokedCertificateEnumeration.hasMoreElements()) {
                    TBSCertList.CRLEntry cRLEntry = TBSCertList.CRLEntry.getInstance(revokedCertificateEnumeration.nextElement());
                    if (this.f13728e && cRLEntry.hasExtensions() && (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) != null) {
                        issuer2 = X500Name.getInstance(GeneralNames.getInstance(extension.getParsedValue()).getNames()[0].getName());
                    }
                    if (cRLEntry.getUserCertificate().hasValue(serialNumber)) {
                        if (certificate instanceof X509Certificate) {
                            issuer = X500Name.getInstance(x509Certificate.getIssuerX500Principal().getEncoded());
                        } else {
                            try {
                                issuer = org.bouncycastle.asn1.x509.Certificate.getInstance(certificate.getEncoded()).getIssuer();
                            } catch (CertificateEncodingException e2) {
                                throw new IllegalArgumentException("Cannot process certificate: " + e2.getMessage());
                            }
                        }
                        return issuer2.equals(issuer);
                    }
                }
            }
            return false;
        }
        throw new IllegalArgumentException("X.509 CRL used with non X.509 Cert");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0143 -> B:7:0x0075). Please submit an issue!!! */
    @Override // java.security.cert.CRL
    public String toString() {
        String str;
        String dumpAsString;
        Object cRLDistPoint;
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append("              Version: ");
        stringBuffer.append(getVersion());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("             IssuerDN: ");
        stringBuffer.append(getIssuerDN());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("          This update: ");
        stringBuffer.append(getThisUpdate());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("          Next update: ");
        stringBuffer.append(getNextUpdate());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("  Signature Algorithm: ");
        stringBuffer.append(getSigAlgName());
        stringBuffer.append(lineSeparator);
        X509SignatureUtil.c(getSignature(), stringBuffer, lineSeparator);
        Extensions extensions = this.f13725b.getTBSCertList().getExtensions();
        if (extensions != null) {
            Enumeration oids = extensions.oids();
            if (oids.hasMoreElements()) {
                str = "           Extensions: ";
                stringBuffer.append(str);
                stringBuffer.append(lineSeparator);
            }
            while (oids.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
                Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                if (extension.getExtnValue() != null) {
                    ASN1InputStream aSN1InputStream = new ASN1InputStream(extension.getExtnValue().getOctets());
                    stringBuffer.append("                       critical(");
                    stringBuffer.append(extension.isCritical());
                    stringBuffer.append(") ");
                    try {
                    } catch (Exception unused) {
                        stringBuffer.append(aSN1ObjectIdentifier.getId());
                        stringBuffer.append(" value = ");
                        str = "*****";
                    }
                    if (aSN1ObjectIdentifier.equals((ASN1Primitive) Extension.cRLNumber)) {
                        cRLDistPoint = new CRLNumber(ASN1Integer.getInstance(aSN1InputStream.readObject()).getPositiveValue());
                    } else {
                        if (aSN1ObjectIdentifier.equals((ASN1Primitive) Extension.deltaCRLIndicator)) {
                            dumpAsString = "Base CRL: " + new CRLNumber(ASN1Integer.getInstance(aSN1InputStream.readObject()).getPositiveValue());
                        } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) Extension.issuingDistributionPoint)) {
                            cRLDistPoint = IssuingDistributionPoint.getInstance(aSN1InputStream.readObject());
                        } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) Extension.cRLDistributionPoints)) {
                            cRLDistPoint = CRLDistPoint.getInstance(aSN1InputStream.readObject());
                        } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) Extension.freshestCRL)) {
                            cRLDistPoint = CRLDistPoint.getInstance(aSN1InputStream.readObject());
                        } else {
                            stringBuffer.append(aSN1ObjectIdentifier.getId());
                            stringBuffer.append(" value = ");
                            dumpAsString = ASN1Dump.dumpAsString(aSN1InputStream.readObject());
                        }
                        stringBuffer.append(dumpAsString);
                        stringBuffer.append(lineSeparator);
                    }
                    stringBuffer.append(cRLDistPoint);
                    stringBuffer.append(lineSeparator);
                } else {
                    stringBuffer.append(lineSeparator);
                }
            }
        }
        Set<Object> revokedCertificates = getRevokedCertificates();
        if (revokedCertificates != null) {
            for (Object obj : revokedCertificates) {
                stringBuffer.append(obj);
                stringBuffer.append(lineSeparator);
            }
        }
        return stringBuffer.toString();
    }

    @Override // java.security.cert.X509CRL
    public void verify(PublicKey publicKey) {
        doVerify(publicKey, new SignatureCreator() { // from class: org.bouncycastle.jcajce.provider.asymmetric.x509.X509CRLImpl.1
            @Override // org.bouncycastle.jcajce.provider.asymmetric.x509.SignatureCreator
            public Signature createSignature(String str) {
                try {
                    return X509CRLImpl.this.f13724a.createSignature(str);
                } catch (Exception unused) {
                    return Signature.getInstance(str);
                }
            }
        });
    }

    @Override // java.security.cert.X509CRL
    public void verify(PublicKey publicKey, final String str) {
        doVerify(publicKey, new SignatureCreator(this) { // from class: org.bouncycastle.jcajce.provider.asymmetric.x509.X509CRLImpl.2
            @Override // org.bouncycastle.jcajce.provider.asymmetric.x509.SignatureCreator
            public Signature createSignature(String str2) {
                String str3 = str;
                return str3 != null ? Signature.getInstance(str2, str3) : Signature.getInstance(str2);
            }
        });
    }

    @Override // java.security.cert.X509CRL
    public void verify(PublicKey publicKey, final Provider provider) {
        try {
            doVerify(publicKey, new SignatureCreator() { // from class: org.bouncycastle.jcajce.provider.asymmetric.x509.X509CRLImpl.3
                @Override // org.bouncycastle.jcajce.provider.asymmetric.x509.SignatureCreator
                public Signature createSignature(String str) {
                    return provider != null ? Signature.getInstance(X509CRLImpl.this.getSigAlgName(), provider) : Signature.getInstance(X509CRLImpl.this.getSigAlgName());
                }
            });
        } catch (NoSuchProviderException e2) {
            throw new NoSuchAlgorithmException("provider issue: " + e2.getMessage());
        }
    }
}
