package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.apache.http.HttpHost;
import org.apache.http.message.TokenParser;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.asn1.x509.qualified.ETSIQCObjectIdentifiers;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.asn1.x509.qualified.RFC3739QCObjectIdentifiers;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.LocaleString;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.i18n.filter.UntrustedUrlInput;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.jce.provider.PKIXPolicyNode;
import org.bouncycastle.jce.provider.RFC3280CertPathUtilities;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String RESOURCE_NAME = "org.bouncycastle.x509.CertPathReviewerMessages";
    private boolean initialized;

    /* renamed from: m  reason: collision with root package name */
    protected CertPath f15139m;

    /* renamed from: n  reason: collision with root package name */
    protected PKIXParameters f15140n;

    /* renamed from: o  reason: collision with root package name */
    protected Date f15141o;

    /* renamed from: p  reason: collision with root package name */
    protected Date f15142p;

    /* renamed from: q  reason: collision with root package name */
    protected List f15143q;

    /* renamed from: r  reason: collision with root package name */
    protected int f15144r;

    /* renamed from: s  reason: collision with root package name */
    protected List[] f15145s;

    /* renamed from: t  reason: collision with root package name */
    protected List[] f15146t;
    protected TrustAnchor u;
    protected PublicKey v;
    protected PolicyNode w;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 != bArr.length; i2++) {
                stringBuffer.append(Integer.toHexString(bArr[i2] & 255));
                stringBuffer.append(TokenParser.SP);
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.f15140n.getCertPathCheckers();
        for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
            try {
                try {
                    pKIXCertPathChecker.init(false);
                } catch (CertPathValidatorException e2) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e2.getMessage(), e2, e2.getClass().getName()}), e2);
                }
            } catch (CertPathReviewerException e3) {
                r(e3.getErrorMessage(), e3.getIndex());
                return;
            }
        }
        for (int size = this.f15143q.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.f15143q.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15129f);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15124a);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15126c);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15130g);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15131h);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15132i);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15133j);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15125b);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15127d);
                criticalExtensionOIDs.remove(CertPathValidatorUtilities.f15128e);
                if (size == 0) {
                    criticalExtensionOIDs.remove(Extension.extendedKeyUsage.getId());
                }
                String str = QC_STATEMENT;
                if (criticalExtensionOIDs.contains(str) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(str);
                }
                for (PKIXCertPathChecker pKIXCertPathChecker2 : certPathCheckers) {
                    try {
                        pKIXCertPathChecker2.check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e4) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e4.getMessage(), e4, e4.getClass().getName()}), e4.getCause(), this.f15139m, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it = criticalExtensionOIDs.iterator();
                    while (it.hasNext()) {
                        r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.f15143q.size() - 1; size > 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.f15143q.get(size);
                if (!CertPathValidatorUtilities.j(x509Certificate)) {
                    X500Principal g2 = CertPathValidatorUtilities.g(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(g2.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) CertPathValidatorUtilities.c(x509Certificate, CertPathValidatorUtilities.f15127d);
                                    if (aSN1Sequence2 != null) {
                                        for (int i2 = 0; i2 < aSN1Sequence2.size(); i2++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i2));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e2) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e2, this.f15139m, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e3) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e3, this.f15139m, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e4) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(g2.getName())}), e4, this.f15139m, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e5) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(g2.getName())}), e5, this.f15139m, size);
                        }
                    } catch (IOException e6) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(g2)}), e6, this.f15139m, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) CertPathValidatorUtilities.c(x509Certificate, CertPathValidatorUtilities.f15128e);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i3 = 0; i3 != excludedSubtrees.length; i3++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i3]);
                            }
                        }
                    }
                } catch (AnnotatedException e7) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e7, this.f15139m, size);
                }
            }
        } catch (CertPathReviewerException e8) {
            r(e8.getErrorMessage(), e8.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int intValue;
        int i2 = this.f15144r;
        int i3 = 0;
        for (int size = this.f15143q.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.f15143q.get(size);
            if (!CertPathValidatorUtilities.j(x509Certificate)) {
                if (i2 <= 0) {
                    q(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i2--;
                i3++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.c(x509Certificate, CertPathValidatorUtilities.f15125b));
            } catch (AnnotatedException unused) {
                r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && (pathLenConstraint = basicConstraints.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < i2) {
                i2 = intValue;
            }
        }
        s(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i3)}));
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x023f A[Catch: CertPathReviewerException -> 0x05f7, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:244:0x04c1, B:245:0x04c4, B:247:0x04ca, B:249:0x04da, B:250:0x04de, B:252:0x04e4, B:253:0x04ec, B:254:0x04ef, B:255:0x04f4, B:256:0x04f8, B:258:0x04fe, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0120 A[Catch: CertPathReviewerException -> 0x05f7, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:244:0x04c1, B:245:0x04c4, B:247:0x04ca, B:249:0x04da, B:250:0x04de, B:252:0x04e4, B:253:0x04ec, B:254:0x04ef, B:255:0x04f4, B:256:0x04f8, B:258:0x04fe, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x013d A[Catch: CertPathReviewerException -> 0x05f7, TRY_LEAVE, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:244:0x04c1, B:245:0x04c4, B:247:0x04ca, B:249:0x04da, B:250:0x04de, B:252:0x04e4, B:253:0x04ec, B:254:0x04ef, B:255:0x04f4, B:256:0x04f8, B:258:0x04fe, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0216 A[Catch: CertPathReviewerException -> 0x05f7, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:244:0x04c1, B:245:0x04c4, B:247:0x04ca, B:249:0x04da, B:250:0x04de, B:252:0x04e4, B:253:0x04ec, B:254:0x04ef, B:255:0x04f4, B:256:0x04f8, B:258:0x04fe, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkPolicy() {
        int i2;
        int i3;
        int i4;
        PKIXPolicyNode pKIXPolicyNode;
        Set<String> set;
        String str;
        int i5;
        int i6;
        HashSet hashSet;
        String str2;
        int i7;
        int intValueExact;
        int intValueExact2;
        String str3;
        HashSet hashSet2;
        Enumeration objects;
        HashSet hashSet3;
        String str4;
        int i8;
        int i9;
        Set<String> criticalExtensionOIDs;
        String str5 = "CertPathReviewer.policyExtError";
        Set<String> initialPolicies = this.f15140n.getInitialPolicies();
        int i10 = this.f15144r + 1;
        ArrayList[] arrayListArr = new ArrayList[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            arrayListArr[i11] = new ArrayList();
        }
        HashSet hashSet4 = new HashSet();
        hashSet4.add(RFC3280CertPathUtilities.ANY_POLICY);
        PKIXPolicyNode pKIXPolicyNode2 = new PKIXPolicyNode(new ArrayList(), 0, hashSet4, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
        arrayListArr[0].add(pKIXPolicyNode2);
        if (this.f15140n.isExplicitPolicyRequired()) {
            i3 = 0;
            i2 = 1;
        } else {
            i2 = 1;
            i3 = this.f15144r + 1;
        }
        int i12 = this.f15140n.isAnyPolicyInhibited() ? 0 : this.f15144r + i2;
        int i13 = this.f15140n.isPolicyMappingInhibited() ? 0 : this.f15144r + i2;
        try {
            int size = this.f15143q.size() - i2;
            PKIXPolicyNode pKIXPolicyNode3 = pKIXPolicyNode2;
            X509Certificate x509Certificate = null;
            HashSet hashSet5 = null;
            while (size >= 0) {
                int i14 = this.f15144r - size;
                X509Certificate x509Certificate2 = (X509Certificate) this.f15143q.get(size);
                int i15 = i10;
                try {
                    ASN1Sequence aSN1Sequence = (ASN1Sequence) CertPathValidatorUtilities.c(x509Certificate2, CertPathValidatorUtilities.f15124a);
                    if (aSN1Sequence == null || pKIXPolicyNode3 == null) {
                        set = initialPolicies;
                        str = str5;
                        i5 = i12;
                        i6 = i13;
                        pKIXPolicyNode3 = pKIXPolicyNode3;
                    } else {
                        Enumeration objects2 = aSN1Sequence.getObjects();
                        set = initialPolicies;
                        HashSet hashSet6 = new HashSet();
                        while (objects2.hasMoreElements()) {
                            PolicyInformation policyInformation = PolicyInformation.getInstance(objects2.nextElement());
                            PKIXPolicyNode pKIXPolicyNode4 = pKIXPolicyNode3;
                            ASN1ObjectIdentifier policyIdentifier = policyInformation.getPolicyIdentifier();
                            String str6 = str5;
                            hashSet6.add(policyIdentifier.getId());
                            if (!RFC3280CertPathUtilities.ANY_POLICY.equals(policyIdentifier.getId())) {
                                try {
                                    Set f2 = CertPathValidatorUtilities.f(policyInformation.getPolicyQualifiers());
                                    if (!CertPathValidatorUtilities.m(i14, arrayListArr, policyIdentifier, f2)) {
                                        CertPathValidatorUtilities.n(i14, arrayListArr, policyIdentifier, f2);
                                    }
                                } catch (CertPathValidatorException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e2, this.f15139m, size);
                                }
                            }
                            pKIXPolicyNode3 = pKIXPolicyNode4;
                            str5 = str6;
                        }
                        str = str5;
                        PKIXPolicyNode pKIXPolicyNode5 = pKIXPolicyNode3;
                        if (hashSet5 != null && !hashSet5.contains(RFC3280CertPathUtilities.ANY_POLICY)) {
                            hashSet2 = new HashSet();
                            for (Object obj : hashSet5) {
                                if (hashSet6.contains(obj)) {
                                    hashSet2.add(obj);
                                }
                            }
                            if (i12 <= 0) {
                                if (i14 < this.f15144r && CertPathValidatorUtilities.j(x509Certificate2)) {
                                }
                                i5 = i12;
                                i6 = i13;
                                hashSet3 = hashSet2;
                                pKIXPolicyNode3 = pKIXPolicyNode5;
                                for (i9 = i14 - 1; i9 >= 0; i9--) {
                                    ArrayList arrayList = arrayListArr[i9];
                                    for (int i16 = 0; i16 < arrayList.size(); i16++) {
                                        PKIXPolicyNode pKIXPolicyNode6 = (PKIXPolicyNode) arrayList.get(i16);
                                        if (!pKIXPolicyNode6.hasChildren()) {
                                            PKIXPolicyNode o2 = CertPathValidatorUtilities.o(pKIXPolicyNode3, arrayListArr, pKIXPolicyNode6);
                                            pKIXPolicyNode3 = o2;
                                            if (o2 == null) {
                                                break;
                                            }
                                        }
                                    }
                                }
                                criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                if (criticalExtensionOIDs != null) {
                                    boolean contains = criticalExtensionOIDs.contains(CertPathValidatorUtilities.f15124a);
                                    ArrayList arrayList2 = arrayListArr[i14];
                                    for (int i17 = 0; i17 < arrayList2.size(); i17++) {
                                        ((PKIXPolicyNode) arrayList2.get(i17)).setCritical(contains);
                                    }
                                }
                                hashSet5 = hashSet3;
                            }
                            objects = aSN1Sequence.getObjects();
                            while (objects.hasMoreElements()) {
                                PolicyInformation policyInformation2 = PolicyInformation.getInstance(objects.nextElement());
                                if (RFC3280CertPathUtilities.ANY_POLICY.equals(policyInformation2.getPolicyIdentifier().getId())) {
                                    try {
                                        Set f3 = CertPathValidatorUtilities.f(policyInformation2.getPolicyQualifiers());
                                        ArrayList arrayList3 = arrayListArr[i14 - 1];
                                        hashSet3 = hashSet2;
                                        for (int i18 = 0; i18 < arrayList3.size(); i18++) {
                                            PKIXPolicyNode pKIXPolicyNode7 = (PKIXPolicyNode) arrayList3.get(i18);
                                            for (Object obj2 : pKIXPolicyNode7.getExpectedPolicies()) {
                                                ArrayList arrayList4 = arrayList3;
                                                int i19 = i12;
                                                if (obj2 instanceof String) {
                                                    str4 = (String) obj2;
                                                } else if (obj2 instanceof ASN1ObjectIdentifier) {
                                                    str4 = ((ASN1ObjectIdentifier) obj2).getId();
                                                } else {
                                                    arrayList3 = arrayList4;
                                                    i12 = i19;
                                                }
                                                Iterator children = pKIXPolicyNode7.getChildren();
                                                boolean z = false;
                                                while (children.hasNext()) {
                                                    Iterator it = children;
                                                    if (str4.equals(((PKIXPolicyNode) children.next()).getValidPolicy())) {
                                                        z = true;
                                                    }
                                                    children = it;
                                                }
                                                if (z) {
                                                    i8 = i13;
                                                } else {
                                                    HashSet hashSet7 = new HashSet();
                                                    hashSet7.add(str4);
                                                    i8 = i13;
                                                    PKIXPolicyNode pKIXPolicyNode8 = new PKIXPolicyNode(new ArrayList(), i14, hashSet7, pKIXPolicyNode7, f3, str4, false);
                                                    pKIXPolicyNode7.addChild(pKIXPolicyNode8);
                                                    arrayListArr[i14].add(pKIXPolicyNode8);
                                                }
                                                arrayList3 = arrayList4;
                                                i12 = i19;
                                                i13 = i8;
                                            }
                                        }
                                        i5 = i12;
                                        i6 = i13;
                                        pKIXPolicyNode3 = pKIXPolicyNode5;
                                        while (i9 >= 0) {
                                        }
                                        criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                        if (criticalExtensionOIDs != null) {
                                        }
                                        hashSet5 = hashSet3;
                                    } catch (CertPathValidatorException e3) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e3, this.f15139m, size);
                                    }
                                }
                            }
                            i5 = i12;
                            i6 = i13;
                            hashSet3 = hashSet2;
                            pKIXPolicyNode3 = pKIXPolicyNode5;
                            while (i9 >= 0) {
                            }
                            criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                            if (criticalExtensionOIDs != null) {
                            }
                            hashSet5 = hashSet3;
                        }
                        hashSet2 = hashSet6;
                        if (i12 <= 0) {
                        }
                        objects = aSN1Sequence.getObjects();
                        while (objects.hasMoreElements()) {
                        }
                        i5 = i12;
                        i6 = i13;
                        hashSet3 = hashSet2;
                        pKIXPolicyNode3 = pKIXPolicyNode5;
                        while (i9 >= 0) {
                        }
                        criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs != null) {
                        }
                        hashSet5 = hashSet3;
                    }
                    if (aSN1Sequence == null) {
                        pKIXPolicyNode3 = null;
                    }
                    if (i3 <= 0 && pKIXPolicyNode3 == null) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noValidPolicyTree"));
                    }
                    if (i14 != this.f15144r) {
                        try {
                            ASN1Primitive c2 = CertPathValidatorUtilities.c(x509Certificate2, CertPathValidatorUtilities.f15126c);
                            if (c2 != null) {
                                ASN1Sequence aSN1Sequence2 = (ASN1Sequence) c2;
                                int i20 = 0;
                                while (i20 < aSN1Sequence2.size()) {
                                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence2.getObjectAt(i20);
                                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(1);
                                    ASN1Sequence aSN1Sequence4 = aSN1Sequence2;
                                    if (RFC3280CertPathUtilities.ANY_POLICY.equals(((ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(0)).getId())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.f15139m, size);
                                    }
                                    if (RFC3280CertPathUtilities.ANY_POLICY.equals(aSN1ObjectIdentifier.getId())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.f15139m, size);
                                    }
                                    i20++;
                                    aSN1Sequence2 = aSN1Sequence4;
                                }
                            }
                            if (c2 != null) {
                                ASN1Sequence aSN1Sequence5 = (ASN1Sequence) c2;
                                HashMap hashMap = new HashMap();
                                HashSet<String> hashSet8 = new HashSet();
                                int i21 = 0;
                                while (i21 < aSN1Sequence5.size()) {
                                    ASN1Sequence aSN1Sequence6 = (ASN1Sequence) aSN1Sequence5.getObjectAt(i21);
                                    ASN1Sequence aSN1Sequence7 = aSN1Sequence5;
                                    String id = ((ASN1ObjectIdentifier) aSN1Sequence6.getObjectAt(0)).getId();
                                    HashSet hashSet9 = hashSet5;
                                    String id2 = ((ASN1ObjectIdentifier) aSN1Sequence6.getObjectAt(1)).getId();
                                    if (hashMap.containsKey(id)) {
                                        ((Set) hashMap.get(id)).add(id2);
                                    } else {
                                        HashSet hashSet10 = new HashSet();
                                        hashSet10.add(id2);
                                        hashMap.put(id, hashSet10);
                                        hashSet8.add(id);
                                    }
                                    i21++;
                                    aSN1Sequence5 = aSN1Sequence7;
                                    hashSet5 = hashSet9;
                                }
                                hashSet = hashSet5;
                                for (String str7 : hashSet8) {
                                    if (i6 > 0) {
                                        try {
                                            CertPathValidatorUtilities.k(i14, arrayListArr, str7, hashMap, x509Certificate2);
                                            str3 = str;
                                        } catch (CertPathValidatorException e4) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e4, this.f15139m, size);
                                        } catch (AnnotatedException e5) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, str), e5, this.f15139m, size);
                                        }
                                    } else {
                                        str3 = str;
                                        if (i6 <= 0) {
                                            pKIXPolicyNode3 = CertPathValidatorUtilities.l(i14, arrayListArr, str7, pKIXPolicyNode3);
                                        }
                                    }
                                    str = str3;
                                }
                            } else {
                                hashSet = hashSet5;
                            }
                            str2 = str;
                            if (CertPathValidatorUtilities.j(x509Certificate2)) {
                                i7 = i5;
                                i13 = i6;
                            } else {
                                if (i3 != 0) {
                                    i3--;
                                }
                                i13 = i6 != 0 ? i6 - 1 : i6;
                                i7 = i5 != 0 ? i5 - 1 : i5;
                            }
                            try {
                                ASN1Sequence aSN1Sequence8 = (ASN1Sequence) CertPathValidatorUtilities.c(x509Certificate2, CertPathValidatorUtilities.f15133j);
                                if (aSN1Sequence8 != null) {
                                    Enumeration objects3 = aSN1Sequence8.getObjects();
                                    while (objects3.hasMoreElements()) {
                                        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects3.nextElement();
                                        int tagNo = aSN1TaggedObject.getTagNo();
                                        if (tagNo == 0) {
                                            int intValueExact3 = ASN1Integer.getInstance(aSN1TaggedObject, false).intValueExact();
                                            if (intValueExact3 < i3) {
                                                i3 = intValueExact3;
                                            }
                                        } else if (tagNo == 1 && (intValueExact2 = ASN1Integer.getInstance(aSN1TaggedObject, false).intValueExact()) < i13) {
                                            i13 = intValueExact2;
                                        }
                                    }
                                }
                                try {
                                    ASN1Integer aSN1Integer = (ASN1Integer) CertPathValidatorUtilities.c(x509Certificate2, CertPathValidatorUtilities.f15130g);
                                    if (aSN1Integer != null && (intValueExact = aSN1Integer.intValueExact()) < i7) {
                                        i7 = intValueExact;
                                    }
                                } catch (AnnotatedException unused) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyInhibitExtError"), this.f15139m, size);
                                }
                            } catch (AnnotatedException unused2) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.f15139m, size);
                            }
                        } catch (AnnotatedException e6) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyMapExtError"), e6, this.f15139m, size);
                        }
                    } else {
                        hashSet = hashSet5;
                        str2 = str;
                        i7 = i5;
                        i13 = i6;
                    }
                    size--;
                    x509Certificate = x509Certificate2;
                    str5 = str2;
                    hashSet5 = hashSet;
                    i10 = i15;
                    i12 = i7;
                    initialPolicies = set;
                } catch (AnnotatedException e7) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, str5), e7, this.f15139m, size);
                }
            }
            Set<String> set2 = initialPolicies;
            int i22 = i10;
            PKIXPolicyNode pKIXPolicyNode9 = pKIXPolicyNode3;
            if (!CertPathValidatorUtilities.j(x509Certificate) && i3 > 0) {
                i3--;
            }
            try {
                ASN1Sequence aSN1Sequence9 = (ASN1Sequence) CertPathValidatorUtilities.c(x509Certificate, CertPathValidatorUtilities.f15133j);
                if (aSN1Sequence9 != null) {
                    Enumeration objects4 = aSN1Sequence9.getObjects();
                    int i23 = i3;
                    while (objects4.hasMoreElements()) {
                        ASN1TaggedObject aSN1TaggedObject2 = (ASN1TaggedObject) objects4.nextElement();
                        if (aSN1TaggedObject2.getTagNo() == 0 && ASN1Integer.getInstance(aSN1TaggedObject2, false).intValueExact() == 0) {
                            i23 = 0;
                        }
                    }
                    i4 = 0;
                    i3 = i23;
                } else {
                    i4 = 0;
                }
                if (pKIXPolicyNode9 == null) {
                    if (this.f15140n.isExplicitPolicyRequired()) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.f15139m, size);
                    }
                    pKIXPolicyNode = null;
                } else if (!CertPathValidatorUtilities.i(set2)) {
                    HashSet<PKIXPolicyNode> hashSet11 = new HashSet();
                    for (int i24 = i4; i24 < i22; i24++) {
                        ArrayList arrayList5 = arrayListArr[i24];
                        for (int i25 = i4; i25 < arrayList5.size(); i25++) {
                            PKIXPolicyNode pKIXPolicyNode10 = (PKIXPolicyNode) arrayList5.get(i25);
                            if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode10.getValidPolicy())) {
                                Iterator children2 = pKIXPolicyNode10.getChildren();
                                while (children2.hasNext()) {
                                    PKIXPolicyNode pKIXPolicyNode11 = (PKIXPolicyNode) children2.next();
                                    if (!RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode11.getValidPolicy())) {
                                        hashSet11.add(pKIXPolicyNode11);
                                    }
                                }
                            }
                        }
                    }
                    pKIXPolicyNode = pKIXPolicyNode9;
                    for (PKIXPolicyNode pKIXPolicyNode12 : hashSet11) {
                        Set<String> set3 = set2;
                        if (!set3.contains(pKIXPolicyNode12.getValidPolicy())) {
                            pKIXPolicyNode = CertPathValidatorUtilities.o(pKIXPolicyNode, arrayListArr, pKIXPolicyNode12);
                        }
                        set2 = set3;
                    }
                    if (pKIXPolicyNode != null) {
                        for (int i26 = this.f15144r - 1; i26 >= 0; i26--) {
                            ArrayList arrayList6 = arrayListArr[i26];
                            for (int i27 = i4; i27 < arrayList6.size(); i27++) {
                                PKIXPolicyNode pKIXPolicyNode13 = (PKIXPolicyNode) arrayList6.get(i27);
                                if (!pKIXPolicyNode13.hasChildren()) {
                                    pKIXPolicyNode = CertPathValidatorUtilities.o(pKIXPolicyNode, arrayListArr, pKIXPolicyNode13);
                                }
                            }
                        }
                    }
                } else if (!this.f15140n.isExplicitPolicyRequired()) {
                    pKIXPolicyNode = pKIXPolicyNode9;
                } else if (hashSet5.isEmpty()) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.f15139m, size);
                } else {
                    HashSet<PKIXPolicyNode> hashSet12 = new HashSet();
                    int i28 = i4;
                    while (true) {
                        int i29 = i22;
                        if (i28 >= i29) {
                            break;
                        }
                        ArrayList arrayList7 = arrayListArr[i28];
                        for (int i30 = i4; i30 < arrayList7.size(); i30++) {
                            PKIXPolicyNode pKIXPolicyNode14 = (PKIXPolicyNode) arrayList7.get(i30);
                            if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode14.getValidPolicy())) {
                                Iterator children3 = pKIXPolicyNode14.getChildren();
                                while (children3.hasNext()) {
                                    hashSet12.add(children3.next());
                                }
                            }
                        }
                        i28++;
                        i22 = i29;
                    }
                    for (PKIXPolicyNode pKIXPolicyNode15 : hashSet12) {
                        hashSet5.contains(pKIXPolicyNode15.getValidPolicy());
                    }
                    pKIXPolicyNode = pKIXPolicyNode9;
                    for (int i31 = this.f15144r - 1; i31 >= 0; i31--) {
                        ArrayList arrayList8 = arrayListArr[i31];
                        for (int i32 = i4; i32 < arrayList8.size(); i32++) {
                            PKIXPolicyNode pKIXPolicyNode16 = (PKIXPolicyNode) arrayList8.get(i32);
                            if (!pKIXPolicyNode16.hasChildren()) {
                                pKIXPolicyNode = CertPathValidatorUtilities.o(pKIXPolicyNode, arrayListArr, pKIXPolicyNode16);
                            }
                        }
                    }
                }
                if (i3 <= 0 && pKIXPolicyNode == null) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicy"));
                }
            } catch (AnnotatedException unused3) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.f15139m, size);
            }
        } catch (CertPathReviewerException e8) {
            r(e8.getErrorMessage(), e8.getIndex());
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:(2:91|92)|(3:(15:94|95|96|(11:98|99|(2:102|100)|103|104|(2:107|105)|108|109|110|111|112)|119|99|(1:100)|103|104|(1:105)|108|109|110|111|112)|111|112)|122|95|96|(0)|119|99|(1:100)|103|104|(1:105)|108|109|110) */
    /* JADX WARN: Can't wrap try/catch for region: R(19:30|(2:137|138)(2:32|(2:130|131)(20:34|(2:38|(17:40|41|42|43|44|(18:91|92|(15:94|95|96|(11:98|99|(2:102|100)|103|104|(2:107|105)|108|109|110|111|112)|119|99|(1:100)|103|104|(1:105)|108|109|110|111|112)|122|95|96|(0)|119|99|(1:100)|103|104|(1:105)|108|109|110|111|112)(1:46)|(1:90)(1:50)|51|(7:53|(2:55|(1:57))(1:88)|58|59|(2:61|(1:63))(1:85)|64|(9:66|(1:83)|70|71|72|74|75|77|78))(1:89)|84|70|71|72|74|75|77|78))|129|41|42|43|44|(0)(0)|(1:48)|90|51|(0)(0)|84|70|71|72|74|75|77|78))|132|42|43|44|(0)(0)|(0)|90|51|(0)(0)|84|70|71|72|74|75|77|78) */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0322, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0323, code lost:
        r13 = r3;
        r15 = r4;
        r11 = r5;
        r16 = r6;
        r12 = r7;
        r18 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x03e2, code lost:
        r6 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x03e4, code lost:
        r(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.pubKeyError"), r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0254, code lost:
        r1 = new java.lang.Object[r13];
        r1[r12] = new org.bouncycastle.i18n.filter.TrustedInput(r3.getNotAfter());
        r0 = new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.certificateExpired", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0269, code lost:
        r1 = new java.lang.Object[r13];
        r1[r12] = new org.bouncycastle.i18n.filter.TrustedInput(r3.getNotBefore());
        r0 = new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.certificateNotYetValid", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x027d, code lost:
        r(r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x02af, code lost:
        r(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError"), r5);
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02cc A[LOOP:1: B:100:0x02c6->B:102:0x02cc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02f0 A[LOOP:2: B:104:0x02ea->B:106:0x02f0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0288 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02aa A[Catch: AnnotatedException -> 0x02af, TRY_LEAVE, TryCatch #2 {AnnotatedException -> 0x02af, blocks: (B:93:0x02a2, B:95:0x02aa), top: B:160:0x02a2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkSignatures() {
        TrustAnchor trustAnchor;
        TrustAnchor trustAnchor2;
        X500Principal x500Principal;
        X509Certificate x509Certificate;
        PublicKey publicKey;
        int size;
        ErrorBundle errorBundle;
        CRLDistPoint cRLDistPoint;
        AuthorityInformationAccess authorityInformationAccess;
        Iterator it;
        Iterator it2;
        X509Certificate x509Certificate2;
        int i2;
        int i3;
        PublicKey publicKey2;
        X500Principal x500Principal2;
        TrustAnchor trustAnchor3;
        ASN1Primitive c2;
        ASN1Primitive c3;
        char c4;
        char c5;
        int i4;
        char c6;
        AuthorityKeyIdentifier authorityKeyIdentifier;
        GeneralNames authorityCertIssuer;
        boolean[] keyUsage;
        char c7 = 2;
        char c8 = 0;
        int i5 = 1;
        s(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathValidDate", new Object[]{new TrustedInput(this.f15142p), new TrustedInput(this.f15141o)}));
        try {
            List list = this.f15143q;
            X509Certificate x509Certificate3 = (X509Certificate) list.get(list.size() - 1);
            Collection z = z(x509Certificate3, this.f15140n.getTrustAnchors());
            if (z.size() > 1) {
                q(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.conflictingTrustAnchors", new Object[]{Integers.valueOf(z.size()), new UntrustedInput(x509Certificate3.getIssuerX500Principal())}));
            } else if (z.isEmpty()) {
                q(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noTrustAnchorFound", new Object[]{new UntrustedInput(x509Certificate3.getIssuerX500Principal()), Integers.valueOf(this.f15140n.getTrustAnchors().size())}));
            } else {
                trustAnchor = (TrustAnchor) z.iterator().next();
                try {
                    try {
                        try {
                            CertPathValidatorUtilities.p(x509Certificate3, trustAnchor.getTrustedCert() != null ? trustAnchor.getTrustedCert().getPublicKey() : trustAnchor.getCAPublicKey(), this.f15140n.getSigProvider());
                        } catch (SignatureException unused) {
                            q(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustButInvalidCert"));
                        } catch (Exception unused2) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        q(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknown", new Object[]{new UntrustedInput(th.getMessage()), new UntrustedInput(th)}));
                        trustAnchor2 = trustAnchor;
                        char c9 = 5;
                        if (trustAnchor2 != null) {
                        }
                        if (trustAnchor2 != null) {
                        }
                        X509Certificate x509Certificate4 = x509Certificate;
                        X500Principal x500Principal3 = x500Principal;
                        PublicKey publicKey3 = publicKey;
                        size = this.f15143q.size() - 1;
                        while (size >= 0) {
                        }
                        this.u = trustAnchor2;
                        this.v = publicKey3;
                    }
                } catch (CertPathReviewerException e2) {
                    e = e2;
                    q(e.getErrorMessage());
                    trustAnchor2 = trustAnchor;
                    char c92 = 5;
                    if (trustAnchor2 != null) {
                    }
                    if (trustAnchor2 != null) {
                    }
                    X509Certificate x509Certificate42 = x509Certificate;
                    X500Principal x500Principal32 = x500Principal;
                    PublicKey publicKey32 = publicKey;
                    size = this.f15143q.size() - 1;
                    while (size >= 0) {
                    }
                    this.u = trustAnchor2;
                    this.v = publicKey32;
                }
            }
            trustAnchor = null;
        } catch (CertPathReviewerException e3) {
            e = e3;
            trustAnchor = null;
        } catch (Throwable th2) {
            th = th2;
            trustAnchor = null;
        }
        trustAnchor2 = trustAnchor;
        char c922 = 5;
        if (trustAnchor2 != null) {
            X509Certificate trustedCert = trustAnchor2.getTrustedCert();
            try {
                x500Principal = trustedCert != null ? CertPathValidatorUtilities.g(trustedCert) : new X500Principal(trustAnchor2.getCAName());
            } catch (IllegalArgumentException unused3) {
                q(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustDNInvalid", new Object[]{new UntrustedInput(trustAnchor2.getCAName())}));
                x500Principal = null;
            }
            if (trustedCert != null && (keyUsage = trustedCert.getKeyUsage()) != null && (keyUsage.length <= 5 || !keyUsage[5])) {
                s(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustKeyUsage"));
            }
        } else {
            x500Principal = null;
        }
        if (trustAnchor2 != null) {
            x509Certificate = trustAnchor2.getTrustedCert();
            publicKey = x509Certificate != null ? x509Certificate.getPublicKey() : trustAnchor2.getCAPublicKey();
            try {
                AlgorithmIdentifier a2 = CertPathValidatorUtilities.a(publicKey);
                a2.getAlgorithm();
                a2.getParameters();
            } catch (CertPathValidatorException unused4) {
                q(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustPubKeyError"));
            }
        } else {
            x509Certificate = null;
            publicKey = null;
        }
        X509Certificate x509Certificate422 = x509Certificate;
        X500Principal x500Principal322 = x500Principal;
        PublicKey publicKey322 = publicKey;
        size = this.f15143q.size() - 1;
        while (size >= 0) {
            int i6 = this.f15144r - size;
            X509Certificate x509Certificate5 = (X509Certificate) this.f15143q.get(size);
            if (publicKey322 != null) {
                try {
                    CertPathValidatorUtilities.p(x509Certificate5, publicKey322, this.f15140n.getSigProvider());
                } catch (GeneralSecurityException e4) {
                    Object[] objArr = new Object[3];
                    objArr[c8] = e4.getMessage();
                    objArr[i5] = e4;
                    objArr[c7] = e4.getClass().getName();
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", objArr);
                    r(errorBundle, size);
                    x509Certificate5.checkValidity(this.f15142p);
                    if (this.f15140n.isRevocationEnabled()) {
                    }
                    if (x500Principal2 != null) {
                    }
                    c4 = 2;
                    c5 = 0;
                    if (i2 == this.f15144r) {
                    }
                    c6 = 5;
                    x500Principal322 = x509Certificate2.getSubjectX500Principal();
                    publicKey322 = CertPathValidatorUtilities.e(this.f15143q, i3);
                    AlgorithmIdentifier a3 = CertPathValidatorUtilities.a(publicKey322);
                    a3.getAlgorithm();
                    a3.getParameters();
                    int i7 = i3 - 1;
                    c7 = c4;
                    c8 = c5;
                    c922 = c6;
                    x509Certificate422 = x509Certificate2;
                    trustAnchor2 = trustAnchor3;
                    size = i7;
                    i5 = i4;
                }
            } else if (CertPathValidatorUtilities.j(x509Certificate5)) {
                try {
                    CertPathValidatorUtilities.p(x509Certificate5, x509Certificate5.getPublicKey(), this.f15140n.getSigProvider());
                    r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.rootKeyIsValidButNotATrustAnchor"), size);
                } catch (GeneralSecurityException e5) {
                    Object[] objArr2 = new Object[3];
                    objArr2[c8] = e5.getMessage();
                    objArr2[i5] = e5;
                    objArr2[c7] = e5.getClass().getName();
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", objArr2);
                    r(errorBundle, size);
                    x509Certificate5.checkValidity(this.f15142p);
                    if (this.f15140n.isRevocationEnabled()) {
                    }
                    if (x500Principal2 != null) {
                    }
                    c4 = 2;
                    c5 = 0;
                    if (i2 == this.f15144r) {
                    }
                    c6 = 5;
                    x500Principal322 = x509Certificate2.getSubjectX500Principal();
                    publicKey322 = CertPathValidatorUtilities.e(this.f15143q, i3);
                    AlgorithmIdentifier a32 = CertPathValidatorUtilities.a(publicKey322);
                    a32.getAlgorithm();
                    a32.getParameters();
                    int i72 = i3 - 1;
                    c7 = c4;
                    c8 = c5;
                    c922 = c6;
                    x509Certificate422 = x509Certificate2;
                    trustAnchor2 = trustAnchor3;
                    size = i72;
                    i5 = i4;
                }
            } else {
                ErrorBundle errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.NoIssuerPublicKey");
                byte[] extensionValue = x509Certificate5.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                if (extensionValue != null && (authorityCertIssuer = (authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1OctetString.getInstance(extensionValue).getOctets())).getAuthorityCertIssuer()) != null) {
                    GeneralName generalName = authorityCertIssuer.getNames()[c8];
                    BigInteger authorityCertSerialNumber = authorityKeyIdentifier.getAuthorityCertSerialNumber();
                    if (authorityCertSerialNumber != null) {
                        Object[] objArr3 = new Object[7];
                        objArr3[c8] = new LocaleString(RESOURCE_NAME, "missingIssuer");
                        objArr3[i5] = " \"";
                        objArr3[2] = generalName;
                        objArr3[3] = "\" ";
                        objArr3[4] = new LocaleString(RESOURCE_NAME, "missingSerial");
                        objArr3[5] = " ";
                        objArr3[6] = authorityCertSerialNumber;
                        errorBundle2.setExtraArguments(objArr3);
                        r(errorBundle2, size);
                        x509Certificate5.checkValidity(this.f15142p);
                        if (this.f15140n.isRevocationEnabled()) {
                            try {
                                c3 = CertPathValidatorUtilities.c(x509Certificate5, CRL_DIST_POINTS);
                            } catch (AnnotatedException unused5) {
                                r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPtExtError"), size);
                            }
                            try {
                                if (c3 != null) {
                                    cRLDistPoint = CRLDistPoint.getInstance(c3);
                                    c2 = CertPathValidatorUtilities.c(x509Certificate5, AUTH_INFO_ACCESS);
                                    if (c2 != null) {
                                        authorityInformationAccess = AuthorityInformationAccess.getInstance(c2);
                                        Vector x = x(cRLDistPoint);
                                        Vector y = y(authorityInformationAccess);
                                        it = x.iterator();
                                        while (it.hasNext()) {
                                            Object[] objArr4 = new Object[i5];
                                            objArr4[c8] = new UntrustedUrlInput(it.next());
                                            t(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPoint", objArr4), size);
                                            i5 = 1;
                                        }
                                        it2 = y.iterator();
                                        while (it2.hasNext()) {
                                            Object[] objArr5 = new Object[1];
                                            objArr5[c8] = new UntrustedUrlInput(it2.next());
                                            t(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ocspLocation", objArr5), size);
                                        }
                                        x509Certificate2 = x509Certificate5;
                                        i2 = i6;
                                        i3 = size;
                                        X509Certificate x509Certificate6 = x509Certificate422;
                                        publicKey2 = publicKey322;
                                        x500Principal2 = x500Principal322;
                                        trustAnchor3 = trustAnchor2;
                                        v(this.f15140n, x509Certificate5, this.f15142p, x509Certificate6, publicKey322, x, y, i3);
                                    }
                                    authorityInformationAccess = null;
                                    Vector x2 = x(cRLDistPoint);
                                    Vector y2 = y(authorityInformationAccess);
                                    it = x2.iterator();
                                    while (it.hasNext()) {
                                    }
                                    it2 = y2.iterator();
                                    while (it2.hasNext()) {
                                    }
                                    x509Certificate2 = x509Certificate5;
                                    i2 = i6;
                                    i3 = size;
                                    X509Certificate x509Certificate62 = x509Certificate422;
                                    publicKey2 = publicKey322;
                                    x500Principal2 = x500Principal322;
                                    trustAnchor3 = trustAnchor2;
                                    v(this.f15140n, x509Certificate5, this.f15142p, x509Certificate62, publicKey322, x2, y2, i3);
                                }
                                v(this.f15140n, x509Certificate5, this.f15142p, x509Certificate62, publicKey322, x2, y2, i3);
                            } catch (CertPathReviewerException e6) {
                                e = e6;
                                r(e.getErrorMessage(), i3);
                                if (x500Principal2 != null) {
                                }
                                c4 = 2;
                                c5 = 0;
                                if (i2 == this.f15144r) {
                                }
                                c6 = 5;
                                x500Principal322 = x509Certificate2.getSubjectX500Principal();
                                publicKey322 = CertPathValidatorUtilities.e(this.f15143q, i3);
                                AlgorithmIdentifier a322 = CertPathValidatorUtilities.a(publicKey322);
                                a322.getAlgorithm();
                                a322.getParameters();
                                int i722 = i3 - 1;
                                c7 = c4;
                                c8 = c5;
                                c922 = c6;
                                x509Certificate422 = x509Certificate2;
                                trustAnchor2 = trustAnchor3;
                                size = i722;
                                i5 = i4;
                            }
                            cRLDistPoint = null;
                            c2 = CertPathValidatorUtilities.c(x509Certificate5, AUTH_INFO_ACCESS);
                            if (c2 != null) {
                            }
                            authorityInformationAccess = null;
                            Vector x22 = x(cRLDistPoint);
                            Vector y22 = y(authorityInformationAccess);
                            it = x22.iterator();
                            while (it.hasNext()) {
                            }
                            it2 = y22.iterator();
                            while (it2.hasNext()) {
                            }
                            x509Certificate2 = x509Certificate5;
                            i2 = i6;
                            i3 = size;
                            X509Certificate x509Certificate622 = x509Certificate422;
                            publicKey2 = publicKey322;
                            x500Principal2 = x500Principal322;
                            trustAnchor3 = trustAnchor2;
                        } else {
                            x509Certificate2 = x509Certificate5;
                            i2 = i6;
                            i3 = size;
                            publicKey2 = publicKey322;
                            x500Principal2 = x500Principal322;
                            trustAnchor3 = trustAnchor2;
                        }
                        if (x500Principal2 != null || x509Certificate2.getIssuerX500Principal().equals(x500Principal2)) {
                            c4 = 2;
                            c5 = 0;
                        } else {
                            c4 = 2;
                            c5 = 0;
                            r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certWrongIssuer", new Object[]{x500Principal2.getName(), x509Certificate2.getIssuerX500Principal().getName()}), i3);
                        }
                        if (i2 == this.f15144r) {
                            if (x509Certificate2 != null) {
                                i4 = 1;
                                if (x509Certificate2.getVersion() == 1) {
                                    r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i3);
                                }
                            } else {
                                i4 = 1;
                            }
                            try {
                                BasicConstraints basicConstraints = BasicConstraints.getInstance(CertPathValidatorUtilities.c(x509Certificate2, CertPathValidatorUtilities.f15125b));
                                if (basicConstraints == null) {
                                    r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBasicConstraints"), i3);
                                } else if (!basicConstraints.isCA()) {
                                    r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i3);
                                }
                            } catch (AnnotatedException unused6) {
                                r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.errorProcesingBC"), i3);
                            }
                            boolean[] keyUsage2 = x509Certificate2.getKeyUsage();
                            if (keyUsage2 != null) {
                                c6 = 5;
                                if (keyUsage2.length <= 5 || !keyUsage2[5]) {
                                    r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCertSign"), i3);
                                }
                                x500Principal322 = x509Certificate2.getSubjectX500Principal();
                                publicKey322 = CertPathValidatorUtilities.e(this.f15143q, i3);
                                AlgorithmIdentifier a3222 = CertPathValidatorUtilities.a(publicKey322);
                                a3222.getAlgorithm();
                                a3222.getParameters();
                                int i7222 = i3 - 1;
                                c7 = c4;
                                c8 = c5;
                                c922 = c6;
                                x509Certificate422 = x509Certificate2;
                                trustAnchor2 = trustAnchor3;
                                size = i7222;
                                i5 = i4;
                            }
                        } else {
                            i4 = 1;
                        }
                        c6 = 5;
                        x500Principal322 = x509Certificate2.getSubjectX500Principal();
                        publicKey322 = CertPathValidatorUtilities.e(this.f15143q, i3);
                        AlgorithmIdentifier a32222 = CertPathValidatorUtilities.a(publicKey322);
                        a32222.getAlgorithm();
                        a32222.getParameters();
                        int i72222 = i3 - 1;
                        c7 = c4;
                        c8 = c5;
                        c922 = c6;
                        x509Certificate422 = x509Certificate2;
                        trustAnchor2 = trustAnchor3;
                        size = i72222;
                        i5 = i4;
                    }
                }
                r(errorBundle2, size);
                x509Certificate5.checkValidity(this.f15142p);
                if (this.f15140n.isRevocationEnabled()) {
                }
                if (x500Principal2 != null) {
                }
                c4 = 2;
                c5 = 0;
                if (i2 == this.f15144r) {
                }
                c6 = 5;
                x500Principal322 = x509Certificate2.getSubjectX500Principal();
                publicKey322 = CertPathValidatorUtilities.e(this.f15143q, i3);
                AlgorithmIdentifier a322222 = CertPathValidatorUtilities.a(publicKey322);
                a322222.getAlgorithm();
                a322222.getParameters();
                int i722222 = i3 - 1;
                c7 = c4;
                c8 = c5;
                c922 = c6;
                x509Certificate422 = x509Certificate2;
                trustAnchor2 = trustAnchor3;
                size = i722222;
                i5 = i4;
            }
            x509Certificate5.checkValidity(this.f15142p);
            if (this.f15140n.isRevocationEnabled()) {
            }
            if (x500Principal2 != null) {
            }
            c4 = 2;
            c5 = 0;
            if (i2 == this.f15144r) {
            }
            c6 = 5;
            x500Principal322 = x509Certificate2.getSubjectX500Principal();
            publicKey322 = CertPathValidatorUtilities.e(this.f15143q, i3);
            AlgorithmIdentifier a3222222 = CertPathValidatorUtilities.a(publicKey322);
            a3222222.getAlgorithm();
            a3222222.getParameters();
            int i7222222 = i3 - 1;
            c7 = c4;
            c8 = c5;
            c922 = c6;
            x509Certificate422 = x509Certificate2;
            trustAnchor2 = trustAnchor3;
            size = i7222222;
            i5 = i4;
        }
        this.u = trustAnchor2;
        this.v = publicKey322;
    }

    private X509CRL getCRL(String str) {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals(HttpHost.DEFAULT_SCHEME_NAME) && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e2) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e2.getMessage(), e2, e2.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i2) {
        ErrorBundle errorBundle;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) CertPathValidatorUtilities.c(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i3 = 0; i3 < aSN1Sequence.size(); i3++) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i3));
                if (ETSIQCObjectIdentifiers.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) qCStatement.getStatementId())) {
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!RFC3739QCObjectIdentifiers.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) qCStatement.getStatementId())) {
                        if (ETSIQCObjectIdentifiers.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD");
                        } else if (ETSIQCObjectIdentifiers.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                            monetaryValue.getCurrency();
                            double doubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            t(monetaryValue.getCurrency().isAlphabetic() ? new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{monetaryValue.getCurrency().getAlphabetic(), new TrustedInput(new Double(doubleValue)), monetaryValue}) : new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), monetaryValue}), i2);
                        } else {
                            t(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i2);
                            z = true;
                        }
                    }
                }
                t(errorBundle, i2);
            }
            return true ^ z;
        } catch (AnnotatedException unused) {
            r(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i2);
            return false;
        }
    }

    public CertPath getCertPath() {
        return this.f15139m;
    }

    public int getCertPathSize() {
        return this.f15144r;
    }

    public List getErrors(int i2) {
        w();
        return this.f15146t[i2 + 1];
    }

    public List[] getErrors() {
        w();
        return this.f15146t;
    }

    public List getNotifications(int i2) {
        w();
        return this.f15145s[i2 + 1];
    }

    public List[] getNotifications() {
        w();
        return this.f15145s;
    }

    public PolicyNode getPolicyTree() {
        w();
        return this.w;
    }

    public PublicKey getSubjectPublicKey() {
        w();
        return this.v;
    }

    public TrustAnchor getTrustAnchor() {
        w();
        return this.u;
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        Objects.requireNonNull(certPath, "certPath was null");
        this.f15139m = certPath;
        List<? extends Certificate> certificates = certPath.getCertificates();
        this.f15143q = certificates;
        this.f15144r = certificates.size();
        if (this.f15143q.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        this.f15140n = (PKIXParameters) pKIXParameters.clone();
        Date date = new Date();
        this.f15141o = date;
        this.f15142p = CertPathValidatorUtilities.h(this.f15140n, date);
        this.f15145s = null;
        this.f15146t = null;
        this.u = null;
        this.v = null;
        this.w = null;
    }

    public boolean isValidCertPath() {
        w();
        int i2 = 0;
        while (true) {
            List[] listArr = this.f15146t;
            if (i2 >= listArr.length) {
                return true;
            }
            if (!listArr[i2].isEmpty()) {
                return false;
            }
            i2++;
        }
    }

    protected void q(ErrorBundle errorBundle) {
        this.f15146t[0].add(errorBundle);
    }

    protected void r(ErrorBundle errorBundle, int i2) {
        if (i2 < -1 || i2 >= this.f15144r) {
            throw new IndexOutOfBoundsException();
        }
        this.f15146t[i2 + 1].add(errorBundle);
    }

    protected void s(ErrorBundle errorBundle) {
        this.f15145s[0].add(errorBundle);
    }

    protected void t(ErrorBundle errorBundle, int i2) {
        if (i2 < -1 || i2 >= this.f15144r) {
            throw new IndexOutOfBoundsException();
        }
        this.f15145s[i2 + 1].add(errorBundle);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:39:0x0165
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    protected void u(java.security.cert.PKIXParameters r21, java.security.cert.X509Certificate r22, java.util.Date r23, java.security.cert.X509Certificate r24, java.security.PublicKey r25, java.util.Vector r26, int r27) {
        /*
            Method dump skipped, instructions count: 1063
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.u(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    protected void v(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i2) {
        u(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i2);
    }

    protected void w() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.f15145s != null) {
            return;
        }
        int i2 = this.f15144r;
        this.f15145s = new List[i2 + 1];
        this.f15146t = new List[i2 + 1];
        int i3 = 0;
        while (true) {
            List[] listArr = this.f15145s;
            if (i3 >= listArr.length) {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
            listArr[i3] = new ArrayList();
            this.f15146t[i3] = new ArrayList();
            i3++;
        }
    }

    protected Vector x(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i2 = 0; i2 < names.length; i2++) {
                        if (names[i2].getTagNo() == 6) {
                            vector.add(((ASN1IA5String) names[i2].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    protected Vector y(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i2 = 0; i2 < accessDescriptions.length; i2++) {
                if (accessDescriptions[i2].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i2].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((ASN1IA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    protected Collection z(X509Certificate x509Certificate, Set set) {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(CertPathValidatorUtilities.b(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                if (authorityKeyIdentifier.getAuthorityCertSerialNumber() != null) {
                    x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && CertPathValidatorUtilities.b(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }
}
